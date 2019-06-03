package com.daichao.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.product.TagInput;
import com.daichao.admin.service.DcThirdTagService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdTag;
import com.daichao.dao.product.DcThirdTagMapper;
import com.daichao.utils.BeanUtil;
@Service
public class DcThirdTagServiceImpl implements DcThirdTagService{

	@Autowired
	private DcThirdTagMapper dcThirdTagMapper;
	@Override
	public List<DcThirdTag> queryThirdTagList(String name, String status) {
		Map<String, Object> map=new HashMap<>();
		map.put("name", StringUtils.isEmpty(name)?null:name);
		map.put("status", StringUtils.isEmpty(status)?null:status);
		return dcThirdTagMapper.queryDcThirdTagList(map);
	}
	@Override
	public ResultOutput ThirdTagUpdate(TagInput input,Integer userId) {
		try {
			BeanUtil.beanAttributeValueTrim(input);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultOutput("1", "参数异常");
		}
		DcThirdTag tag=new DcThirdTag();
		tag.setTagName(input.getTagName());
		tag.setIconUrl(input.getIconUrl());
		tag.setRemark(input.getRemark());
		tag.setSort(input.getSort());
		tag.setStatus(input.getStatus());
		if(input.getId()!=null&&input.getId()>0) {
			tag.setId(input.getId());
			tag.setUpdateTime(new Date());
			tag.setUpdateId(userId);
			dcThirdTagMapper.updateByPrimaryKeySelective(tag);
		}else {
			tag.setCreateTime(new Date());
			tag.setCreateId(userId);
			dcThirdTagMapper.insertSelective(tag);
		} 
//		if(!CollectionUtils.isEmpty(input.getProductAddIds())) {
//			for (Integer integer : input.getProductAddIds()) {
//				DcThirdProductTag productTag=new DcThirdProductTag();
//				productTag.setProductId(integer);
//				productTag.setTagId(input.getId());
//				dcThirdProductTagMapper.insert(productTag);
//			}
//		}
//		if(!CollectionUtils.isEmpty(input.getProductDelIds())) {
//			for (Integer integer : input.getProductDelIds()) {
//				dcThirdProductTagMapper.deleteByPrimaryKey(integer);
//			}
//		}
		return new ResultOutput();
	}

}
