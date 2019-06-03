package com.daichao.service.impl;

import com.daichao.bean.channel.DcChannel;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcProductConfig;
import com.daichao.bean.system.DcSystemConfig;
import com.daichao.bean.user.DcRegistStatistics;
import com.daichao.bean.user.DcUser;
import com.daichao.common.SimpleRedisCache;
import com.daichao.constant.ErrMsg;
import com.daichao.constant.Global;
import com.daichao.dao.system.DcSystemConfigMapper;
import com.daichao.dao.user.DcRegistStatisticsMapper;
import com.daichao.dao.user.DcUserMapper;
import com.daichao.service.*;
import com.daichao.utils.MD5;
import com.daichao.utils.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DcUserServiceImpl implements DcUserService{
	private static final String KEY_VERIFY_EXPIRE = "verify_expire_";

	@Resource
	private DcUserMapper dcUserMapper;
	@Resource
	private SimpleRedisCache cache;
	@Resource
	private DcChannelService dcChannelService;
	@Resource
	private DcProductConfigService dcProductConfigService;
	@Resource
	private DcRegistStatisticsService dcRegistStatisticsService;
	@Resource
	private SendMessageService sendMessageService;
	@Autowired
	private DcRegistStatisticsMapper dcRegistStatisticsMapper;
	@Autowired
	private DcSystemConfigMapper dcSystemConfigMapper;
	@Override
	public ResultOutput saveUser(DcUser user) {
//		dcUserMapper.insert(user);
		dcUserMapper.selectByPrimaryKey(1);
		return new ResultOutput();
	}

	@Override
	public ResultOutput sendVerificationCode(String phone, String graphValidateCode, String device, String clientSecret) {
		//判断手机号码是否正确
		if(!checkPhone(phone)){
			return new ResultOutput(ErrMsg.ERR_SECRET_CODE, ErrMsg.ERR_SECRET_MSG);
		}
		//查看签名是否正确(h5不验签)
		if((device.equals("ios")||device.equals("android"))&&!checkClientSecret(clientSecret)){
			return new ResultOutput(ErrMsg.ERR_SECRET_CODE, ErrMsg.ERR_SECRET_MSG);
		}
		//查看渠道用户是否已经注册
		if(device.equals("h5") && getUserByAccount(phone)!=null){
			return new ResultOutput(ErrMsg.ERR_BAD_HAD_REGIST_CODE, ErrMsg.ERR_BAD_HAD_REGIST_MSG);
		}
		// 看缓存是否还在
		if (cache.get(KEY_VERIFY_EXPIRE + phone) != null) {
			return new ResultOutput(ErrMsg.ERR_VERIFY_IN_VALID_TIME_CODE, ErrMsg.ERR_VERIFY_IN_VALID_TIME_MSG);
		}
		//图形验证码是否正确
		if ((!StringUtil.isBlank(graphValidateCode)) && !cache.get(Global.VALIDATE_DATE + phone).equals(graphValidateCode.toLowerCase())) {
			return new ResultOutput(ErrMsg.ERR_VALIDATE_CODE_CODE, ErrMsg.ERR_VALIDATE_CODE_MSG);
		}

		// 发送短信
		String code = StringUtil.getRandomNumber(Global.VERIFICATION_CODE_LEN);
//		String code = "111111";
		sendMessageService.sendMessage(phone,"您的短信验证码为:"+code, code, 1,  "1");

		// 存入redis缓存
        //noinspection unchecked
		cache.put(phone, code, Global.VERIFICATION_CODE_VALID_PERIOD);
        //noinspection unchecked
		cache.put(KEY_VERIFY_EXPIRE + phone, 1, Global.VERIFICATION_CODE_EXPIRE);
		return new ResultOutput();
	}

	@Override
	public ResultOutput chanelSignUp(String phone, String msgVerificationCode, String channel, String graphValidateCode) {
		DcUser dcUser = getUserByAccount(phone);
		//验证
		ResultOutput resultOutput = check(phone, msgVerificationCode, graphValidateCode);
		if(!resultOutput.getCode().equals("000000")){
			return resultOutput;
		}
		//若为渠道页直接注册
		if(dcUser == null) {
			//获取渠道信息
			if(StringUtils.isNotEmpty(channel)) {
				Pattern p = Pattern.compile("[^0-9a-zA-Z\\u4e00-\\u9fa5.，,。？“”]+");
		        Matcher m = p.matcher(channel);
		        channel = m.replaceAll("");
			} 
			else channel=Global.DEF_CHANNEL;
			DcChannel dcChannel = dcChannelService.selectByCode(channel);
			if(dcChannel!=null&&dcChannel.getUrlStatus().equals(0)) {
				return new ResultOutput("11", "网络异常，请稍后再试");
			}
			channel = dcChannel==null?Global.OTHER_CHANNEL:channel;
			dcUser = new DcUser();
			dcUser.setUsername(phone);
			dcUser.setPhone(phone);
			dcUser.setRegistTime(new Date());
			dcUser.setRegistChannel(channel);
			dcUserMapper.insertSelective(dcUser);
			//统计数据
			dataStatistics(channel);
			return new ResultOutput();
		}
		//渠道页进来但已注册
		return new ResultOutput(ErrMsg.ERR_BAD_HAD_REGIST_CODE, ErrMsg.ERR_BAD_HAD_REGIST_MSG);
	}

	@Override
	public ResultOutput signUp(String phone, String msgVerificationCode, String loginChannel, HttpServletRequest request, String graphValidateCode) {
		//验证
		ResultOutput resultOutput = check(phone, msgVerificationCode, graphValidateCode);
		if(!resultOutput.getCode().equals("000000")){
			return resultOutput;
		}
		
		DcUser dcUser = getUserByAccount(phone);
		//非渠道直接注册登陆
		Date date = new Date();
		if(dcUser == null){
			//先注册
			dcUser = new DcUser();
			dcUser.setUsername(phone);
			dcUser.setPhone(phone);
			dcUser.setRegistTime(date);
			dcUser.setRegistChannel(Global.DEF_CHANNEL);
			dcUser.setFirstLoginTime(date);
			dcUser.setFirstLoginChannel(loginChannel);
			dcUserMapper.insertSelective(dcUser);
			//统计数据
			dataStatistics(Global.DEF_CHANNEL);
		}
		if(dcUser.getStatus()==2) {
			return new ResultOutput("11", "网络异常，请稍后再试");
		}
		DcChannel dcChannel = dcChannelService.selectByCode(dcUser.getRegistChannel());
		if(dcChannel!=null&&dcChannel.getUrlStatus().equals(0)) {
			return new ResultOutput("11", "网络异常，请稍后再试");
		}
		//登陆
		if(StringUtil.isBlank(dcUser.getFirstLoginChannel())){
			//首次登陆记录时间和登陆渠道
			dcUser.setFirstLoginTime(date);
			dcUser.setFirstLoginChannel(loginChannel);
		}
		//存token
		String token = MD5.MD5Token(phone+date.getTime());
		//更新
		dcUser.setAccessToken(token);
		dcUserMapper.updateByPrimaryKeySelective(dcUser);
		if(!StringUtil.isBlank(dcUser.getPassword()))
			dcUser.setSetPassword(true);
		return new ResultOutput(hidePhone(dcUser));
	}

	@Override
	public ResultOutput signIn(String phone, String password, String loginChannel) {
		DcUser dcUser = getUserByAccount(phone);
		if(dcUser == null) {
			return new ResultOutput(ErrMsg.ERR_USER_NOT_EXIST_CODE, ErrMsg.ERR_USER_NOT_EXIST_MSG);
		}
		if(!passwordMatch(password)){
			return new ResultOutput(ErrMsg.ERR_PASSWORD_MATCH_CODE, ErrMsg.ERR_PASSWORD_MATCH_MSG);
		}
		if(!MD5.MD5Encode(password).equals(dcUser.getPassword())){
			return new ResultOutput(ErrMsg.ERR_PASSWORD_CODE, ErrMsg.ERR_PASSWORD_MSG);
		}
		if(dcUser.getStatus()==2) {
			return new ResultOutput("11", "网络异常，请稍后再试");
		}
		DcChannel dcChannel = dcChannelService.selectByCode(dcUser.getRegistChannel());
		if(dcChannel!=null&&dcChannel.getUrlStatus().equals(0)) {
			return new ResultOutput("11", "网络异常，请稍后再试");
		}
		//存token
		String token = MD5.MD5Token(phone+new Date().getTime());
		//更新
		dcUser.setAccessToken(token);
		if(StringUtil.isBlank(dcUser.getFirstLoginChannel())){
			//首次登陆记录时间和登陆渠道
			dcUser.setFirstLoginTime(new Date());
			dcUser.setFirstLoginChannel(loginChannel);
		}
		dcUserMapper.updateByPrimaryKeySelective(dcUser);
		dcUser.setSetPassword(true);
		return new ResultOutput(hidePhone(dcUser));
	}

	@Override
	public ResultOutput findPassword(String phone, String password, String passwordConfirm, String msgVerificationCode, String loginChannel, String graphValidateCode) {
		//验证
		ResultOutput resultOutput = check(phone, msgVerificationCode, graphValidateCode);
		if(!resultOutput.getCode().equals("000000")){
			return resultOutput;
		}
		resultOutput = checkPassword(password, passwordConfirm);
		if(!resultOutput.getCode().equals("000000")){
			return resultOutput;
		}
		DcUser dcUser = getUserByAccount(phone);
		if(dcUser == null) {
			Date date = new Date();
			//手机号不存在则注册
			dcUser = new DcUser();
			dcUser.setUsername(phone);
			dcUser.setPhone(phone);
			dcUser.setPassword(MD5.MD5Encode(password));
			dcUser.setRegistTime(date);
			dcUser.setRegistChannel(Global.DEF_CHANNEL);
			dcUser.setFirstLoginTime(date);
			dcUser.setFirstLoginChannel(loginChannel);
			dcUserMapper.insertSelective(dcUser);
			//统计数据
			dataStatistics(Global.DEF_CHANNEL);
		}else{
			dcUser.setPassword(MD5.MD5Encode(password));
			dcUserMapper.updateByPrimaryKeySelective(dcUser);
		}
		return new ResultOutput();
	}

	@Override
	public ResultOutput resetPassword(String token, String password, String newPassword, String passwordConfirm) {
		DcUser dcUser = dcUserMapper.selectByToken(token);
		if(dcUser==null){
			return new ResultOutput(ErrMsg.ERR_NOT_SIGN_IN_CODE, ErrMsg.ERR_NOT_SIGN_IN_MSG);
		}
		if(!MD5.MD5Encode(password).equals(dcUser.getPassword())){
			return new ResultOutput(ErrMsg.ERR_PASSWORD_CODE, ErrMsg.ERR_PASSWORD_MSG);
		}
		ResultOutput resultOutput = checkPassword(newPassword, passwordConfirm);
		if(!resultOutput.getCode().equals("000000")){
			return resultOutput;
		}
		dcUser.setPassword(MD5.MD5Encode(newPassword));
		dcUserMapper.updateByPrimaryKeySelective(dcUser);
		return new ResultOutput();
	}

	@Override
	public ResultOutput setPassword(String token, String password, String passwordConfirm) {
		ResultOutput resultOutput = checkPassword(password, passwordConfirm);
		if(!resultOutput.getCode().equals("000000")){
			return resultOutput;
		}
		DcUser dcUser = dcUserMapper.selectByToken(token);
		if(dcUser==null){
			return new ResultOutput(ErrMsg.ERR_NOT_SIGN_IN_CODE, ErrMsg.ERR_NOT_SIGN_IN_MSG);
		}
		dcUser.setPassword(MD5.MD5Encode(password));
		dcUserMapper.updateByPrimaryKeySelective(dcUser);
		return new ResultOutput();
	}

	private DcUser getUserByAccount(String phone) {
		return dcUserMapper.selectByPhone(phone);
	}

	private ResultOutput check(String phone, String msgVerificationCode, String graphValidateCode){
		// 短信验证码是否正确
		if (!msgVerificationCode.equals(String.valueOf(cache.get(phone)))) {
			return new ResultOutput(ErrMsg.ERR_BAD_VERIFICATION_CODE, ErrMsg.ERR_BAD_VERIFICATION_MSG);
		}
		//图形验证码是否正确
		if ((!StringUtil.isBlank(graphValidateCode)) && !cache.get(Global.VALIDATE_DATE + phone).equals(graphValidateCode.toLowerCase())) {
			return new ResultOutput(ErrMsg.ERR_VALIDATE_CODE_CODE, ErrMsg.ERR_VALIDATE_CODE_MSG);
		}
		return new ResultOutput();
	}

	private boolean passwordMatch(String password){
		String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		return password.matches(reg);
	}

	private ResultOutput checkPassword(String password, String passwordConfirm){
		if(!passwordMatch(password)){
			return new ResultOutput(ErrMsg.ERR_PASSWORD_MATCH_CODE, ErrMsg.ERR_PASSWORD_MATCH_MSG);
		}
		if(!password.equals(passwordConfirm)){
			return new ResultOutput(ErrMsg.ERR_PASSWORD_UNLIKE_CODE, ErrMsg.ERR_PASSWORD_UNLIKE_MSG);
		}
		return new ResultOutput();
	}

	private void dataStatistics(String channel){
		//更新注册统计表
		//获取渠道信息
		DcChannel dcChannel = dcChannelService.selectByCode(channel);
		//查询当前渠道当天的注册人数
		int registCount = dcUserMapper.selectChannelRegistToday(channel);
		DcProductConfig dcProductConfig=new DcProductConfig();
		if(registCount>dcChannel.getThreshold()){
			//获取扣量规则
			dcProductConfig = dcProductConfigService.selectByChannelId(dcChannel.getId());
		}
		//查看当天该渠道是否有计量记录，没有则新增一条，有加1
		DcRegistStatistics dcRegistStatistics = dcRegistStatisticsService.selectRecord(dcChannel.getCode());
		if(dcRegistStatistics == null){
			//新增一条
			dcRegistStatistics = new DcRegistStatistics();
			dcRegistStatistics.setChannelId(dcChannel.getId());
			dcRegistStatistics.setChannelCode(dcChannel.getCode());
			dcRegistStatistics.setChannelName(dcChannel.getName());
			dcRegistStatistics.setRegistCount(1);
			dcRegistStatistics.setRegistDeductionCount(dcProductConfig.getDeductionRate()!=null?dcProductConfig.getDeductionRate().doubleValue():1d);
			dcRegistStatistics.setCreateTime(new Date());
			dcRegistStatisticsService.insertSelective(dcRegistStatistics);
		}else{
			//原先记录上加
//			dcRegistStatistics.setRegistCount(dcRegistStatistics.getRegistCount()+1);
//			dcRegistStatistics.setRegistDeductionCount(dcRegistStatistics.getRegistDeductionCount()+(dcProductConfig.getDeductionRate()!=null?dcProductConfig.getDeductionRate().doubleValue():1d));
//			dcRegistStatisticsService.updateByPrimaryKeySelective(dcRegistStatistics);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("registDeductionCount", dcProductConfig.getDeductionRate()!=null?dcProductConfig.getDeductionRate().doubleValue():1d);
			map.put("id", dcRegistStatistics.getId());
			dcRegistStatisticsMapper.updateRegistStatisticsRegistCount(map);
		}
	}
	private DcUser hidePhone(DcUser dcUser){
		dcUser.setPhone(dcUser.getPhone().substring(0,3)+"****"+dcUser.getPhone().substring(7));
		dcUser.setUsername(dcUser.getUsername().substring(0,3)+"****"+dcUser.getUsername().substring(7));
		return dcUser;
	}

	private boolean checkClientSecret(String clientSecret){
		if(StringUtil.isBlank(clientSecret)){
			return false;
		}
		String[] arr = clientSecret.split(",");
		if(arr.length!=20){
			return false;
		}
		String secretRule="a,c,D,G,I,z,K,H,r,0,5,9,3,-,V,q,Z,j,S,#";
		for (String secret:arr) {
			if(!secretRule.contains(secret)){
				return false;
			}
		}
		return true;
	}

	private boolean checkPhone(String phone){
		String reg = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		return Pattern.matches(reg, phone);
	}
}
