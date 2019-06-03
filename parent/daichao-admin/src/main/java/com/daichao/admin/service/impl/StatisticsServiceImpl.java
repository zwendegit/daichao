package com.daichao.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.output.StatisticsOutput;
import com.daichao.admin.service.StatisticsService;
import com.daichao.bean.channel.DcChannel;
import com.daichao.bean.channel.DcChannelStatisticsHistory;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcProductConfig;
import com.daichao.bean.product.DcThirdProductHistory;
import com.daichao.bean.user.DcRegistStatistics;
import com.daichao.dao.channel.DcChannelMapper;
import com.daichao.dao.channel.DcChannelStatisticsHistoryMapper;
import com.daichao.dao.channel.DcChannelUvLogMapper;
import com.daichao.dao.product.DcProductConfigMapper;
import com.daichao.dao.product.DcThirdProductHistoryMapper;
import com.daichao.dao.user.DcRegistStatisticsMapper;
import com.daichao.dao.user.DcUserMapper;
import com.daichao.utils.DateUtil;
@Service
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
    private DcUserMapper dcUserMapper;
	@Autowired
	private DcThirdProductHistoryMapper dcThirdProductHistoryMapper;
	@Autowired
	private DcRegistStatisticsMapper dcRegistStatisticsMapper;
	@Autowired
	private DcChannelUvLogMapper dcChannelUvLogMapper;
	@Autowired
	private DcChannelMapper dcChannelMapper;
	@Autowired
	private DcProductConfigMapper dcProductConfigMapper;
	@Autowired
	private DcChannelStatisticsHistoryMapper dcChannelStatisticsHistoryMapper;
	@Override
	public ResultOutput statistics(String code) {
		Map<String, Object> result=new HashMap<String, Object>();
		int channel_uv_count=0;//落地页uv
		int regist_count_day=0;//预注册
		int regist_login_count_day=0;//应用内注册
		BigDecimal money_sum=BigDecimal.ZERO;//综合收入
		BigDecimal zc_money=BigDecimal.ZERO;//预计支出
//		BigDecimal roi=BigDecimal.ZERO;//roi
		int regist_deduction_count=0;//实际结算
		int uv_sum_day=0;//贷款uv
		Date date=new Date();
		List<StatisticsOutput> list=new ArrayList<StatisticsOutput>();
		//查询渠道信息
		if(StringUtils.isEmpty(code)) {
			//查询所有渠道
			Map<String, Object> resultMap=new HashMap<>();
			resultMap.put("status", 1);
			List<DcChannel> channelList= dcChannelMapper.queryChannelList(resultMap);
			if(!CollectionUtils.isEmpty(channelList)) {
				for (DcChannel dcChannel : channelList) {
					StatisticsOutput output=getStatisticsByCode(dcChannel.getCode(),date);
					channel_uv_count=channel_uv_count+output.getChannelUvCount();
					regist_count_day=regist_count_day+output.getRegistCountDay();
					regist_login_count_day=regist_login_count_day+output.getRegistLoginCountDay();
					money_sum=money_sum.add(output.getMoneySum());
					zc_money=zc_money.add(output.getZcMoney());
//					roi=roi.add(output.getRoi());
					uv_sum_day=uv_sum_day+output.getUvSumDay();
					regist_deduction_count=regist_deduction_count+output.getRegistDeductionCount();
					list.add(output);
				}
			}
		}else {
			DcChannel channel= dcChannelMapper.selectByCode(code);
			if(channel==null) {
				return new ResultOutput("1","渠道不存在");
			}
			StatisticsOutput output=getStatisticsByCode(code,date);
			list.add(output);
		}
		
		
//        resultMap.put("channelUvCount", channelUvCount);//落地页uv
//        resultMap.put("registCountDay", registCountDay);//预注册
//        resultMap.put("loginCountDay", loginCountDay);//应用内uv
//        resultMap.put("registLoginCountDay", registLoginCountDay);//应用内注册
//        resultMap.put("uvSumDay", uvSumDay);//贷款uv
//        resultMap.put("moneySum", moneySum);//综合收入
//        resultMap.put("zcMoney", zcMoney);//预计支出
//        resultMap.put("jsprice", jsprice);//结算单价
//        resultMap.put("threshold",channel.getThreshold()!=null?channel.getThreshold():0);//阙值
//        resultMap.put("deductionRate",!CollectionUtils.isEmpty(configList)?configList.get(0).getDeductionRate():0);//扣量因子
//        resultMap.put("registCount",statistics.getRegistCount()!=null?statistics.getRegistCount():0);//结算数量
//        resultMap.put("registDeductionCount",statistics.getRegistDeductionCount()!=null?statistics.getRegistDeductionCount():0);//实际结算
//        resultMap.put("roi", zcMoney.compareTo(BigDecimal.ZERO)>0?moneySum.divide(zcMoney,2,BigDecimal.ROUND_DOWN):BigDecimal.ZERO);//roi
//        resultMap.put("channelPrice", channel.getPrice());//结算单价
		result.put("channel_uv_count", channel_uv_count);
		result.put("regist_count_day", regist_count_day);
		result.put("regist_login_count_day", regist_login_count_day);
		result.put("money_sum", money_sum.setScale(2, BigDecimal.ROUND_DOWN));
		result.put("zc_money", zc_money.setScale(2, BigDecimal.ROUND_DOWN));
//		roi=zc_money.compareTo(new BigDecimal("0"))>0?money_sum.divide(zc_money,2, BigDecimal.ROUND_DOWN):money_sum;
//		result.put("roi",roi.setScale(2, BigDecimal.ROUND_DOWN));
		result.put("roi",zc_money.compareTo(new BigDecimal("0"))>0?money_sum.divide(zc_money,2,BigDecimal.ROUND_DOWN):money_sum);
		result.put("regist_deduction_count", regist_deduction_count);
		result.put("uv_sum_day", uv_sum_day);
		list.sort(new Comparator<StatisticsOutput>() {
			@Override
			public int compare(StatisticsOutput o1, StatisticsOutput o2) {
				if(o1.getRegistCountDay()-o2.getRegistCountDay()>0) return -1;
				else if(o1.getRegistCountDay()-o2.getRegistCountDay()==0) return 0;
				return 1;
			}
		});
		result.put("list", list);
		return new ResultOutput(result);
	}
	
	public StatisticsOutput getStatisticsByCode(String code,Date date) {
		DcChannel channel= dcChannelMapper.selectByCode(code);
		String startTime=DateUtil.getStringDate(date, "yyyy-MM-dd")+" 00:00:00";
		String endTime=DateUtil.getStringDate(date, "yyyy-MM-dd")+" 23:59:59";
		
		Map<String, Object> param=new HashMap<>();
		//1、	预注册：h5注册人数（已注册过的老用户不计入）
        int registCountDay=0;
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("code", code);
        registCountDay=dcUserMapper.selectCountByRegistTime(param);
        //1、	应用内uv：h5注册后下载打开app的人（只要是首次下载当天激活的都计入）
        int loginCountDay=0;
        loginCountDay=dcUserMapper.selectCountByFirstLoginTime(param);
        //1、	应用内注册：只包含当天注册用户，进入app的登陆人数。
        int registLoginCountDay=0;
        registLoginCountDay=dcUserMapper.selectCountByRegistFirstLoginTime(param);
        //贷款uv：该渠道进来的用户点击米柚钱包里所有产品的uv统计之和
        int uvSumDay=0;
        List<DcThirdProductHistory> historyList =dcThirdProductHistoryMapper.selectCountByUserIdAndDay(param);
        uvSumDay=CollectionUtils.isEmpty(historyList)?uvSumDay:historyList.size();
        //1、	综合收入、钱包到收入、服务收入、非服务收入所有都并为综合收入；综合收入：该渠道进来用户点击甲方产品产生的全部收入；
        //甲方产品收入计算：每一个上线的产品都会设定一个原始uv价值，比如uv6块，
        //后台需要可以手动设置，综合收入等于每个产品uv价值*点击次数的总和。
        BigDecimal moneySum=BigDecimal.ZERO;
        if(!CollectionUtils.isEmpty(historyList)) {
        	for (DcThirdProductHistory dcThirdProductHistory : historyList) {
        		if(dcThirdProductHistory.getPriceType()==2) {//uv结算
        			moneySum=moneySum.add(dcThirdProductHistory.getPrice());
        		}else {//cpa结算
        			moneySum=moneySum.add(dcThirdProductHistory.getUv6Price());
        		}
			}
        }
        //1、	预计支出：渠道扣量后的注册*渠道价格；
        BigDecimal zcMoney=BigDecimal.ZERO;
        DcRegistStatistics statistics=dcRegistStatisticsMapper.queryCountByChannelCodeAndCreatTime(param);
        if(statistics!=null) {
        	zcMoney=statistics.getChannelPrice().multiply(new BigDecimal(statistics.getRegistDeductionCount().intValue()+""));
        }else statistics=new DcRegistStatistics();
        //结算单价：预计结算金额/落地页uv
        BigDecimal jsprice=BigDecimal.ZERO;
        int channelUvCount=0;//落地页uv
        channelUvCount=dcChannelUvLogMapper.queryCountByCodeAndCreateTime(param);
        if(channelUvCount>0) {
        	jsprice=zcMoney.divide(new BigDecimal(channelUvCount+""),2,BigDecimal.ROUND_DOWN);
        }
        //结算细节全量阙值：达到这一个数值之前不扣量，达到之后才开始按照既定规则开始扣量（后台可设置）。
        //扣量因子：后台可设置，随时可以修改，修改之后可以立即生效，也可以在下一个时段生效（比如一个小时），规则技术制定，设置扣量因子不会影响到前面的数据。
        List<DcProductConfig> configList= dcProductConfigMapper.queryByChannleId(channel.getId());
        //结算数量：预注册数量
        //实际结算：扣量后的注册数量
        
        StatisticsOutput output=new StatisticsOutput();
        output.setChannelUvCount(channelUvCount);
        output.setRegistCountDay(registCountDay);
        output.setLoginCountDay(loginCountDay);
        output.setRegistLoginCountDay(registLoginCountDay);
        output.setUvSumDay(uvSumDay);
        output.setMoneySum(moneySum);
        output.setZcMoney(zcMoney);
        output.setJsPrice(jsprice);
        output.setThreshold(channel.getThreshold()!=null?channel.getThreshold():0);
        output.setDeductionRate(!CollectionUtils.isEmpty(configList)?configList.get(0).getDeductionRate():BigDecimal.ZERO);
        output.setRegistCount(statistics.getRegistCount()!=null?statistics.getRegistCount():0);
        output.setRegistDeductionCount(statistics.getRegistDeductionCount()!=null?statistics.getRegistDeductionCount().intValue():0);
        output.setRoi(zcMoney.compareTo(BigDecimal.ZERO)>0?moneySum.divide(zcMoney,2,BigDecimal.ROUND_DOWN):moneySum);
        output.setChannelPrice(channel.getPrice());
        output.setCodeName(channel.getName());
        output.setCode(channel.getCode());
        return output;
	}

	@Override
	public ResultOutput statisticsTask() {
		Date date= DateUtil.getDateBeforeOrAfterDays(new Date(), -1);
		//查询所有渠道
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("status", 1);
		List<DcChannel> channelList= dcChannelMapper.queryChannelList(resultMap);
		if(!CollectionUtils.isEmpty(channelList)) {
			for (DcChannel dcChannel : channelList) {
				DcChannelStatisticsHistory history=new DcChannelStatisticsHistory();
				StatisticsOutput output=getStatisticsByCode(dcChannel.getCode(),date);
				BeanUtils.copyProperties(output, history);
				history.setCreateTime(DateUtil.getDate(DateUtil.getStringDate(date, "yyyy-MM-dd")+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
				dcChannelStatisticsHistoryMapper.insertSelective(history);
			}
		}
		return new ResultOutput();
	}

	@Override
	public List<DcChannelStatisticsHistory> statisticsHistory(String code,String time) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", StringUtils.isNotEmpty(code)?code:null);
		if(StringUtils.isNotEmpty(time)) {
			map.put("startTime", time+" 00:00:00");
			map.put("endTime", time+" 23:59:59");
		}
		return dcChannelStatisticsHistoryMapper.statisticsHistory(map);
	}

	@Override
	public DcChannelStatisticsHistory channelStatistics(String code, String time) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", StringUtils.isNotEmpty(code)?code:null);
		if(StringUtils.isNotEmpty(time)) {
			map.put("startTime", time+" 00:00:00");
			map.put("endTime", time+" 23:59:59");
		}
		DcChannelStatisticsHistory statistics= dcChannelStatisticsHistoryMapper.channelStatistics(map);
		if(statistics!=null) {
			statistics.setRoi(statistics.getZcMoney().compareTo(new BigDecimal("0"))>0?statistics.getMoneySum().divide(statistics.getZcMoney(),2,BigDecimal.ROUND_DOWN):statistics.getMoneySum());
		}
		return statistics;
	}

}
