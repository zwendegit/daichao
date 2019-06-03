package com.daichao.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.bean.product.DcThirdProduct;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.service.DcAdvertService;
@Service
public class DcAdvertServiceImpl implements DcAdvertService{

	@Autowired
	private DcThirdProductMapper dcThirdProductMapper;
	
	@Override
	public DcThirdProduct getAdvertProduct(Integer location) {
		List<DcThirdProduct> productList=dcThirdProductMapper.getAdvertProduct(location);
		return CollectionUtils.isEmpty(productList)?null:productList.get(new Random().nextInt(productList.size()-0)+0);
	}

}
