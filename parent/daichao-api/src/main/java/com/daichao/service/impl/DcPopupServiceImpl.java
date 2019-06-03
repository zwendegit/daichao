package com.daichao.service.impl;

import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcPopup;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.dao.product.DcPopupMapper;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.service.DcPopupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import javax.annotation.Resource;

@Service
public class DcPopupServiceImpl implements DcPopupService {

    @Resource
    private DcPopupMapper dcPopupMapper;
    @Autowired
    private DcThirdProductMapper dcThirdProductMapper;

    @Override
    public ResultOutput popupWindow() {
        DcPopup dcPopup = dcPopupMapper.selectPopup();
        if(!Objects.isNull(dcPopup)) {
        	if(dcPopup.getProductId()!=null&& dcPopup.getProductId()>0) {
//        		DcThirdProduct product= dcThirdProductMapper.selectByPrimaryKey(dcPopup.getProductId());
//        		if(!Objects.isNull(dcPopup)) {
//        			dcPopup.setProductUrl(product.getLinkUrl());
//        			dcPopup.setName(product.getName());
//        			dcPopup.setPicImg(product.getIconUrl());
//        		}
        	}
        }
        return new ResultOutput(dcPopup);
    }
}
