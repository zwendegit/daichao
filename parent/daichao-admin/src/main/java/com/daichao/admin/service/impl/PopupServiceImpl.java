package com.daichao.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.popup.PopupInput;
import com.daichao.admin.input.popup.PopupSaveInput;
import com.daichao.admin.service.PopupService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcPopup;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.dao.product.DcPopupMapper;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.utils.BeanUtil;
@Service
public class PopupServiceImpl implements PopupService{

	@Autowired
	private DcPopupMapper dcPopupMapper;
	@Autowired
	private DcThirdProductMapper dcThirdProductMapper;
	@Override
	public List<DcPopup> queryPopupList(PopupInput input) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", StringUtils.isNotEmpty(input.getName())?input.getName():null );
		map.put("status", StringUtils.isNotEmpty(input.getStatus())?input.getStatus():null );
		map.put("productUrl", StringUtils.isNotEmpty(input.getUrl())?input.getUrl():null);
		return dcPopupMapper.queryPopupList(map);
	}
	@Override
	public ResultOutput popupSave(PopupSaveInput input) {
		try {
			BeanUtil.beanAttributeValueTrim(input);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultOutput("1", "参数异常");
		}
		try {
			DcPopup popup=new DcPopup();
			BeanUtils.copyProperties(input, popup);
			popup.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input.getStartTime()));
			popup.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input.getEndTime()));
			DcThirdProduct product= dcThirdProductMapper.selectByPrimaryKey(input.getProductId());
			if(input.getId()!=null&&input.getId()>0) {
				DcPopup popupOld=dcPopupMapper.selectByPrimaryKey(input.getId());
				if(input.getStatus()!=0) {//上架
					if(popupOld.getStatus()!=input.getStatus()) {
						if(!Objects.isNull(product)) {
							if(product.getStatus()!=1) return new ResultOutput("1", "产品已下架，请从新选择产品");
						}else return new ResultOutput("1", "产品不存在，请从新选择产品");
					}
				}
				dcPopupMapper.updateByPrimaryKeySelective(popup);
			} 
			else {
				if(!Objects.isNull(product)) {
					if(product.getStatus()!=1) return new ResultOutput("1", "产品已下架，请从新选择产品");
				}else return new ResultOutput("1", "产品不存在，请从新选择产品");
				dcPopupMapper.insertSelective(popup);
			} 
		} catch (Exception e) {
			return new ResultOutput("1","编辑失败");
		} 
		return new ResultOutput();
	}

}
