package com.daichao.service;

import com.daichao.bean.product.DcThirdProduct;

public interface DcAdvertService {

	/**
	 * 获取首页广告位产品
	 * @param location
	 * @return
	 */
	public DcThirdProduct getAdvertProduct(Integer location);
}
