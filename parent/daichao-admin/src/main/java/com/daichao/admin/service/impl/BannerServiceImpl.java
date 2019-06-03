package com.daichao.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.banner.BannerInput;
import com.daichao.admin.input.banner.BannerPageInput;
import com.daichao.admin.service.BannerService;
import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.dao.banner.DcBannerMapper;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.utils.BeanUtil;
@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	private DcBannerMapper dcBannerMapper;
	@Autowired
	private DcThirdProductMapper dcThirdProductMapper;
	@Override
	public List<DcBanner> queryBannerList(BannerPageInput input) {
		Map<String, Object> map=new HashMap<>();
		map.put("name", StringUtils.isNotEmpty(input.getName())?input.getName():null );
		map.put("linkUrl", StringUtils.isNotEmpty(input.getLinkUrl())?input.getLinkUrl():null);
		map.put("status", input.getStatus()!=null?input.getStatus():null);
		return dcBannerMapper.queryBannerList(map);
	}
	@Override
	public ResultOutput bannerSave(BannerInput input,Integer userId) {
		try {
			BeanUtil.beanAttributeValueTrim(input);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultOutput("1", "参数异常");
		}
		try {
			DcBanner banner=new DcBanner();
			BeanUtils.copyProperties(input, banner);
			banner.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input.getStartTime()));
			banner.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input.getEndTime()));
			String ids=input.getProductIds();
			if(StringUtils.isEmpty(ids)) {
				return new ResultOutput("1", "关联产品不能为空");
			}
			if(input.getType()!=null&&input.getType()==2) {//单产品
				String id[]=ids.split(",");
				if(id.length>1) {
					return new ResultOutput("1", "关联产品不能大于1");
				}
				Integer productId=Integer.parseInt(id[0]);
				DcThirdProduct product= dcThirdProductMapper.selectByPrimaryKey(productId);
				if(product==null) {
					return new ResultOutput("1", "关联产品不存在");
				}
				if(product.getStatus()!=1) {
					return new ResultOutput("1", "关联产品已下架");
				}
//				banner.setProductName(product.getName());
//				banner.setLinkUrl(product.getLinkUrl());
			}
			if(input.getId()==null) {
				banner.setCreateTime(new Date());
				banner.setCreateId(userId);
				dcBannerMapper.insertSelective(banner);
			}else {
				banner.setUpdateTime(new Date());
				banner.setUpdateId(userId);
				dcBannerMapper.updateByPrimaryKeySelective(banner);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultOutput("1","编辑失败");
		} 
		
		return new ResultOutput();
	}

}
