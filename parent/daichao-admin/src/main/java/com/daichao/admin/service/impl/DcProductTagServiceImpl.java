package com.daichao.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.product.ThirdTagInput;
import com.daichao.admin.service.DcProductTagService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.product.DcThirdProductTagMapper;
@Service
public class DcProductTagServiceImpl implements DcProductTagService{

	@Autowired
	private DcThirdProductTagMapper dcThirdProductTagMapper;
	@Override
	public ResultOutput queryProductTagList(ThirdTagInput input) {
		Map<String, Object> map=new HashMap<>();
		map.put("status", input.getStatus());
		map.put("productId", input.getProductId());
		map.put("tagId", input.getTagId());
		return new ResultOutput(dcThirdProductTagMapper.queryProductTagList(map));
	}

}
