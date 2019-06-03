package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.product.TagInput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdTag;

public interface DcThirdTagService {

	List<DcThirdTag> queryThirdTagList(String name,String status);
	
	ResultOutput ThirdTagUpdate(TagInput input,Integer userId);
}
