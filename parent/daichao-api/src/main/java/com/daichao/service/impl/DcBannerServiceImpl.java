package com.daichao.service.impl;

import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.dao.banner.DcBannerMapper;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.service.DcBannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class DcBannerServiceImpl implements DcBannerService {

    @Resource
    private DcBannerMapper dcBannerMapper;
    @Autowired
    private DcThirdProductMapper dcThirdProductMapper;

    @Override
    public List<DcBanner> selectIndexBanner() {
    	List<DcBanner> bannerList=dcBannerMapper.selectIndexBanner();
    	if(!CollectionUtils.isEmpty(bannerList)) {
    		for (DcBanner dcBanner : bannerList) {
				if(dcBanner.getType()==2) {
					DcThirdProduct product= dcThirdProductMapper.selectByPrimaryKey(Integer.parseInt(dcBanner.getProductIds()));
					if(!Objects.isNull(product)) {
						dcBanner.setProductName(product.getName());
						dcBanner.setLinkUrl(product.getLinkUrl());
					}
				}
			}
    	}
        return bannerList;
    }
}
