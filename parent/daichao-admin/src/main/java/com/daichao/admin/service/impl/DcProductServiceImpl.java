package com.daichao.admin.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.common.SimpleRedisCache;
import com.daichao.admin.input.product.ProductEditInput;
import com.daichao.admin.service.DcProductService;
import com.daichao.bean.advert.DcAdvert;
import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcPopup;
import com.daichao.bean.product.DcProductRechargeLog;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.bean.product.DcThirdProductTag;
import com.daichao.bean.product.DcThirdTag;
import com.daichao.dao.advert.DcAdvertMapper;
import com.daichao.dao.banner.DcBannerMapper;
import com.daichao.dao.product.DcPopupMapper;
import com.daichao.dao.product.DcProductRechargeLogMapper;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.dao.product.DcThirdProductTagMapper;
import com.daichao.dao.product.DcThirdTagMapper;
import com.daichao.utils.BeanUtil;
@Service
public class DcProductServiceImpl implements DcProductService{

	@Autowired
	private DcThirdProductMapper dcThirdProductMapper;
	@Autowired
	private DcThirdProductTagMapper dcThirdProductTagMapper;
	@Autowired
	private DcProductRechargeLogMapper dcProductRechargeLogMapper;
	@Autowired
	private DcThirdTagMapper dcThirdTagMapper;
	@Autowired
	private DcBannerMapper dcBannerMapper;
	@Autowired
	private DcPopupMapper dcPopupMapper;
	@Autowired
	private DcAdvertMapper dcAdvertMapper;
	@Autowired
	private SimpleRedisCache redis;
	@Override
	public List<DcThirdProduct> queryProductList(String name, Integer status) {
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		if(status!=null) {
			map.put("status", status);
		}
		return dcThirdProductMapper.queryProductList(map);
	}
	@Override
	public ResultOutput productEdit(ProductEditInput input,Integer userId) {
		DcThirdProduct product=new DcThirdProduct();
		try {
			BeanUtil.beanAttributeValueTrim(input);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultOutput("1", "参数异常");
		}
		BeanUtils.copyProperties(input, product);
		List<DcThirdProduct> proList=dcThirdProductMapper.queryProductListBySort(input.getSort());
		if(proList.size()>0) {
			if(product.getId()!=null&&product.getId()>0) {
				for (DcThirdProduct dcThirdProduct : proList) {
					if(dcThirdProduct.getId()!=input.getId()) {
						return new ResultOutput("1","权重重复");
					}
				}
			}else return new ResultOutput("1","权重重复");
		}
		product.setEffectTime(product.getCreateTime());
		if(product.getId()!=null&&product.getId()>0) {//编辑
			DcThirdProduct productOld=dcThirdProductMapper.selectByPrimaryKey(input.getId());
			if(input.getStatus()!=1) {
				if(productOld.getStatus()!=input.getStatus()) {
					product.setUpdateTime(new Date());
					product.setUpdateId(userId);
					productDown(input.getStatus(), input.getId(), userId);
				}
			}
			
			dcThirdProductMapper.updateByPrimaryKeySelective(product);
		} 
		else {
			product.setCreateTime(new Date());
			product.setCreateId(userId);
			product.setAmount(BigDecimal.ZERO);
			product.setRechargeAmount(BigDecimal.ZERO);
			dcThirdProductMapper.insertSelective(product);
		} 
		if(!StringUtils.isEmpty(input.getTagAddIds())) {
			String tagids[]=input.getTagAddIds().split(",");
			if(tagids!=null&&tagids.length>0) {
				for (String str : tagids) {
					try {
						DcThirdProductTag tag=new DcThirdProductTag();
						tag.setProductId(product.getId());
						tag.setTagId(Integer.parseInt(str));
						tag.setCreateTime(new Date());
						dcThirdProductTagMapper.insertSelective(tag);
					} catch (Exception e) {
						continue;
					}
				}
			}
			
		}
		if(!StringUtils.isEmpty(input.getTagDelIds())) {
			String tagids[]=input.getTagDelIds().split(",");
			if(tagids!=null&&tagids.length>0) {
				for (String str : tagids) {
					try {
						Map<String, Object> param=new HashMap<String, Object>();
						param.put("productId", product.getId());
						param.put("tagId", Integer.parseInt(str));
						dcThirdProductTagMapper.updateStatusByTagIdAndProductId(param);
					} catch (Exception e) {
						continue;
					}
				}
			}
			
		}
		return new ResultOutput();
	}
	@Override
	public ResultOutput productStatusUpdate(Integer status,Integer productId,Integer userId) {
		if(null==productId) {
			return new ResultOutput("1", "参数不合法");
		}
		DcThirdProduct product=dcThirdProductMapper.selectByPrimaryKey(productId);
		if(null==product) {
			return new ResultOutput("1", "产品不存在");
		}
		if(status.equals(product.getStatus())) {
			return new ResultOutput();
		}
		return productDown(status, productId, userId);
	}
	@Override
	public ResultOutput productSortUpdate(Integer sort,Integer productId,Integer userId) {
		List<DcThirdProduct> proList=dcThirdProductMapper.queryProductListBySort(sort);
		if(proList.size()>0) {
			for (DcThirdProduct dcThirdProduct : proList) {
				if(dcThirdProduct.getId()!=productId) {
					return new ResultOutput("1","权重重复");
				}
			}
		}
		DcThirdProduct product=new DcThirdProduct();
		product.setSort(sort);
		product.setId(productId);
		product.setUpdateId(userId);
		dcThirdProductMapper.updateByPrimaryKeySelective(product);
		return new ResultOutput();
	}
	@Transactional
	@Override
	public ResultOutput productUpdateBatch(Integer status, String productIds,Integer userId) {
		if(StringUtils.isEmpty(productIds)) {
			return new ResultOutput("1","参数异常");
		}
		try {
			String ids[]=productIds.split(",");
			if(ids!=null&&ids.length>0) {
				for (String string : ids) {
//					DcThirdProduct product=new DcThirdProduct();
//					product.setStatus(status);
//					product.setId(Integer.parseInt(string));
//					product.setUpdateId(userId);
//					dcThirdProductMapper.updateByPrimaryKeySelective(product);
					productDown(status, Integer.parseInt(string), userId);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return new ResultOutput();
	}
	@Override
	public ResultOutput productRecharge(BigDecimal amount, Integer productId,Integer userId) {
		DcThirdProduct product=dcThirdProductMapper.selectByPrimaryKey(productId);
		if(product!=null) {
			Map<String, Object> map=new HashMap<>();
			map.put("amount", amount);
			map.put("productId", productId);
			dcThirdProductMapper.updateProductAmountAndRechargeAmount(map);
			DcProductRechargeLog log=new DcProductRechargeLog();
			log.setProductId(productId);
			log.setRechargeAmount(amount);
			log.setTotalAmount(Objects.isNull(product.getRechargeAmount())?new BigDecimal(BigInteger.ZERO):product.getRechargeAmount().add(amount));
			log.setRemark("充值"+amount+"元");
			log.setCreateId(userId);
			log.setCreateTime(new Date());
			dcProductRechargeLogMapper.insertSelective(log);
		}
		return new ResultOutput();
	}
	@Override
	public List<DcProductRechargeLog> productRechargeLog(String productName) {
		return dcProductRechargeLogMapper.queryProductRechargeLogList(productName);
	}
	@Override
	public ResultOutput productDetail(Integer productId) {
		Map<String, Object> map=new HashMap<String, Object>();
		DcThirdProduct product=dcThirdProductMapper.selectByPrimaryKey(productId);
		List<DcThirdTag> tagList= dcThirdTagMapper.selectTagByProductId(productId);
		map.put("product", product);
		map.put("tagList", tagList);
		return new ResultOutput(map);
	}

	/**
	 * 产品下架
	 */
	public ResultOutput productDown(Integer status,Integer productId,Integer userId) {
		DcThirdProduct product=new DcThirdProduct();
		product.setStatus(status);
		product.setId(productId);
		product.setUpdateId(userId);
		dcThirdProductMapper.updateByPrimaryKeySelective(product);
		if(status!=1) {//产品下架
			//修改banner关联关系
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("status", 1);
			map.put("type", 2);
			List<DcBanner> bannerList=dcBannerMapper.queryBannerList(map);
			if(!CollectionUtils.isEmpty(bannerList)) {
				for (DcBanner dcBanner : bannerList) {
					if(dcBanner.getProductIds().equals(productId+"")) {
						dcBanner.setStatus(0);
						dcBanner.setUpdateTime(new Date());
						dcBanner.setUpdateId(userId);
						dcBannerMapper.updateByPrimaryKeySelective(dcBanner);
					}
				}
			}
			//修改弹框关联关系
			List<DcPopup> poputList=dcPopupMapper.queryPopupList(map);
			if(!CollectionUtils.isEmpty(poputList)) {
				for (DcPopup dcPopup : poputList) {
					if(dcPopup.getProductId().equals(productId)) {
						dcPopup.setStatus(0);
						dcPopupMapper.updateByPrimaryKeySelective(dcPopup);
					}
				}
			}
			//修改tag关联关系
			List<DcThirdProductTag> tagList=dcThirdProductTagMapper.queryProductTagListByProductId(productId);
			if(!CollectionUtils.isEmpty(tagList)) {
				for (DcThirdProductTag dcThirdProductTag : tagList) {
					if(dcThirdProductTag.getProductId().equals(productId)) {
						dcThirdProductTag.setStatus(0);
						dcThirdProductTag.setUpdateTime(new Date());
						dcThirdProductTagMapper.updateByPrimaryKeySelective(dcThirdProductTag);
					}
				}
			}
			//修改广告位下架
			List<DcAdvert> advertList=dcAdvertMapper.queryAdvertByProductId(productId);
			if(!CollectionUtils.isEmpty(advertList)) {
				for (DcAdvert dcAdvert : advertList) {
					if(dcAdvert.getProductId().equals(productId)) {
						dcAdvert.setStatus(0);
						dcAdvert.setUpdateId(userId);
						dcAdvert.setUpdateTime(new Date());
						dcAdvertMapper.updateByPrimaryKeySelective(dcAdvert);
					}
				}
			}
			//
			if(!Objects.isNull(redis.get("indexProduct"))) {
				DcThirdProduct productRedis= (DcThirdProduct) redis.get("indexProduct");
				if(productRedis.getId().equals(productId)) {
					redis.remove("indexProduct");
				}
			}
			
		}
		return new ResultOutput();
	}
}
