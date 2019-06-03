package com.daichao.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.input.channel.ChannelPageInput;
import com.daichao.admin.input.channel.ProductConfigInput;
import com.daichao.admin.service.ChannelServie;
import com.daichao.bean.channel.DcChannel;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcProductConfig;
import com.daichao.dao.channel.DcChannelMapper;
import com.daichao.dao.product.DcProductConfigMapper;
import com.daichao.utils.BeanUtil;
@Service
public class ChannelServiceImpl implements ChannelServie{

	@Autowired
	private DcChannelMapper dcChannelMapper;
	@Autowired
	private DcProductConfigMapper dcProductConfigMapper;
	@Override
	public List<DcChannel> queryChannelList(ChannelPageInput input) {
		Map<String, Object> map=new HashMap<>();
		map.put("name", StringUtils.isNotEmpty(input.getName())?input.getName():null);
		map.put("code", StringUtils.isNotEmpty(input.getCode())?input.getCode():null);
		map.put("type", input.getType()!=null&&input.getType()>0?input.getType():null);
		map.put("status", input.getStatus()!=null?input.getStatus():null);
		return dcChannelMapper.queryChannelList(map);
	}
	@Override
	public ResultOutput channelEdit(DcChannel channel) {
		try {
			BeanUtil.beanAttributeValueTrim(channel);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultOutput("1", "参数异常");
		}
		if(StringUtils.isEmpty(channel.getCode()) ) return new ResultOutput("1", "渠道编号不能为空");
		else {
			DcChannel channelOld=dcChannelMapper.selectByCode(channel.getCode());
			if(channel.getId()==null) {
				if(!Objects.isNull(channelOld)) {
					return new ResultOutput("1", "当前渠道已存在");
				}
				dcChannelMapper.insertSelective(channel);
			} 
			else {
				if(!Objects.isNull(channelOld)&&channelOld.getId()!=channel.getId()) {
					return new ResultOutput("1", "当前渠道已存在");
				}
				dcChannelMapper.updateByPrimaryKeySelective(channel);
			} 
		}	
		
		return new ResultOutput(channel);
	}
	@Override
	public ResultOutput channelConfigDetail(Integer channelId) {
		List<DcProductConfig> configList=dcProductConfigMapper.queryByChannleId(channelId);
		return new ResultOutput(!CollectionUtils.isEmpty(configList)?configList.get(0):null);
	}
	@Override
	public ResultOutput channelConfigSave(ProductConfigInput config,Integer userId) {
		try {
			DcProductConfig conf=new DcProductConfig();
			BeanUtils.copyProperties(config, conf);
//			conf.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(config.getStartTime()));
//			conf.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(config.getEndTime()));
			conf.setStartTime(new Date());
			conf.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2100-10-01 23:59:59"));
			if(config.getId()!=null&&config.getId()>0) {
				conf.setUpdateId(userId);
				conf.setUpdateTime(new Date());
				dcProductConfigMapper.updateByPrimaryKeySelective(conf);
			} 
			else dcProductConfigMapper.insertSelective(conf);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultOutput("1","保存失败");
		} 
		return new ResultOutput();
	}
	@Override
	public List<String> queryChannelCodeList(Integer status) {
		return dcChannelMapper.queryChannelCodeList(status);
	}
	@Override
	public ResultOutput channelUrlStatusUpdate(Integer status, Integer id) {
		DcChannel channel= dcChannelMapper.selectByPrimaryKey(id);
		if(channel==null) {
			return new ResultOutput("1", "当前渠道不存在");
		}
		if(status==null) {
			return new ResultOutput("1", "参数不合法");
		}
		channel.setUrlStatus(status);
		dcChannelMapper.updateByPrimaryKeySelective(channel);
		return new ResultOutput();
	}

}
