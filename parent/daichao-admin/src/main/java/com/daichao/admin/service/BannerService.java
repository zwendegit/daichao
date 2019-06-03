package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.banner.BannerInput;
import com.daichao.admin.input.banner.BannerPageInput;
import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.output.ResultOutput;

public interface BannerService {

	List<DcBanner> queryBannerList(BannerPageInput input);
	
	ResultOutput bannerSave(BannerInput input,Integer userId);
}
