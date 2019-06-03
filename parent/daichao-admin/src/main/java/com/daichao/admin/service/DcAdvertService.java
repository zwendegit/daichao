package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.advert.AdvertInput;
import com.daichao.bean.advert.DcAdvert;
import com.daichao.bean.output.ResultOutput;

public interface DcAdvertService {

	public List<DcAdvert> queryAdvertList(Integer location);
	
	ResultOutput advertSave(AdvertInput input,Integer userId);
	
	ResultOutput advertUupate(Integer id,Integer userId);
	
	ResultOutput advertProductList(Integer location);
}
