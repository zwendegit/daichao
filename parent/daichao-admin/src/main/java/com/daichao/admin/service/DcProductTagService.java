package com.daichao.admin.service;

import com.daichao.admin.input.product.ThirdTagInput;
import com.daichao.bean.output.ResultOutput;

public interface DcProductTagService {

	/**
	 * 查询产品标签列表
	 * @param input
	 * @return
	 */
	ResultOutput queryProductTagList(ThirdTagInput input);
}
