package com.daichao.service.impl;

import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcPopup;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.bean.product.DcThirdProductHistory;
import com.daichao.bean.product.DcThirdProductStatistics;
import com.daichao.bean.product.DcThirdProductTag;
import com.daichao.bean.user.DcUser;
import com.daichao.constant.ErrMsg;
import com.daichao.dao.banner.DcBannerMapper;
import com.daichao.dao.product.DcPopupMapper;
import com.daichao.dao.product.DcThirdProductHistoryMapper;
import com.daichao.dao.product.DcThirdProductMapper;
import com.daichao.dao.product.DcThirdProductStatisticsMapper;
import com.daichao.dao.product.DcThirdProductTagMapper;
import com.daichao.dao.user.DcUserMapper;
import com.daichao.service.DcThirdProductService;
import com.daichao.utils.AesUtils;
import com.daichao.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DcThirdProductServiceImpl implements DcThirdProductService {

    @Resource
    private DcThirdProductMapper dcThirdProductMapper;
    @Resource
    private DcUserMapper dcUserMapper;
    @Resource
    private DcThirdProductHistoryMapper dcThirdProductHistoryMapper;
    @Resource
    private DcThirdProductStatisticsMapper dcThirdProductStatisticsMapper;
    @Autowired
    private DcBannerMapper dcBannerMapper;
    @Autowired
    private DcPopupMapper dcPopupMapper;
    @Autowired
    private DcThirdProductTagMapper dcThirdProductTagMapper;
    @Override
    public List<DcThirdProduct> selectThirdProduct() {
        return dcThirdProductMapper.selectThirdProduct();
    }

    @Override
    public ResultOutput clickStatistics(String token, Integer productId) {
        DcUser dcUser = dcUserMapper.selectByToken(token);
        if(dcUser==null){
            return new ResultOutput(ErrMsg.ERR_NOT_SIGN_IN_CODE, ErrMsg.ERR_NOT_SIGN_IN_MSG);
        }
        //查出产品
        DcThirdProduct dcThirdProduct = dcThirdProductMapper.selectByPrimaryKey(productId);
        if(dcThirdProduct==null || dcThirdProduct.getStatus()!=1)
            return new ResultOutput(ErrMsg.ERR_PRODUCT_SOLD_OUT_CODE, ErrMsg.ERR_PRODUCT_SOLD_OUT_MSG);
        //查看当前用户当前产品是否已经点击
        int count = dcThirdProductHistoryMapper.selectByUserIdAndProductId(dcUser.getId(), productId);
        //贷超产品点击流水表插入记录
        Date date = new Date();
        if(count == 0){
            //贷超产品点击流水表统一用户一天一个产品只有一条记录
            DcThirdProductHistory dcThirdProductHistory = new DcThirdProductHistory();
            dcThirdProductHistory.setUserId(dcUser.getId());
            dcThirdProductHistory.setThirdProductId(productId);
            dcThirdProductHistory.setPhone(dcUser.getPhone());
            dcThirdProductHistory.setCreateTime(date);
            dcThirdProductHistoryMapper.insertSelective(dcThirdProductHistory);
        }
        DcThirdProductStatistics dcThirdProductStatistics = dcThirdProductStatisticsMapper.selectRecordToday(productId);
        if(dcThirdProductStatistics == null){
            //没记录则新增一条
            dcThirdProductStatistics = new DcThirdProductStatistics();
            dcThirdProductStatistics.setDailyClicksCount(1);
            dcThirdProductStatistics.setDistinctClicksCount(1);
            dcThirdProductStatistics.setThirdProductId(productId);
            dcThirdProductStatistics.setCreateTime(date);
            dcThirdProductStatisticsMapper.insertSelective(dcThirdProductStatistics);
            if(dcThirdProduct.getPriceType()==2) {
        		dcThirdProduct.setAmount(dcThirdProduct.getAmount().subtract(dcThirdProduct.getPrice()));
        		//若该产品可用余额小于点击价格则修改该产品状态为禁用
        		if(dcThirdProduct.getAmount().compareTo(dcThirdProduct.getPrice())<0) {
        			dcThirdProduct.setStatus(0);
        			productDown(dcThirdProduct.getStatus(), dcThirdProduct.getId(), 0);
        		} 
        		dcThirdProductMapper.updateByPrimaryKeySelective(dcThirdProduct);
        	}
        }else{
            //有记录但当前用户未重复点击该产品则当日去重点击次数加1并减去该产品可用余额
            if(count==0){
            	if(dcThirdProduct.getPriceType()==2) {
            		dcThirdProduct.setAmount(dcThirdProduct.getAmount().subtract(dcThirdProduct.getPrice()));
            		//若该产品可用余额小于点击价格则修改该产品状态为禁用
            		if(dcThirdProduct.getAmount().compareTo(dcThirdProduct.getPrice())<0) {
            			productDown(dcThirdProduct.getStatus(), dcThirdProduct.getId(), 0);
            			dcThirdProduct.setStatus(0);
            		} 
            		dcThirdProductMapper.updateByPrimaryKeySelective(dcThirdProduct);
            	}
                dcThirdProductStatistics.setDistinctClicksCount(dcThirdProductStatistics.getDistinctClicksCount()+1);
            }
            dcThirdProductStatistics.setDailyClicksCount(dcThirdProductStatistics.getDailyClicksCount()+1);
            dcThirdProductStatisticsMapper.updateByPrimaryKeySelective(dcThirdProductStatistics);
        }
        try {
			dcThirdProduct.setLinkUrl(AesUtils.Encrypt(dcThirdProduct.getLinkUrl(), null));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ResultOutput(dcThirdProduct);
    }

    @Override
    public List<DcThirdProduct> queryProductList(Integer status, String productIds) {
        Map<String, Object> map=new HashMap<>();
        if(status!=null) {
            map.put("status", status);
        }
        if(!StringUtil.isBlank(productIds)) {
            map.put("productIds", productIds);
        }
        List<DcThirdProduct> productList=dcThirdProductMapper.queryProductList(map);
        if(!CollectionUtils.isEmpty(productList)) {
        	for (DcThirdProduct dcThirdProduct : productList) {
        		dcThirdProduct.setLinkUrl(null);
			}
        }
        return productList;
    }

    /**
	 * 产品下架
	 */
	public ResultOutput productDown(Integer status,Integer productId,Integer userId) {
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
		}
		return new ResultOutput();
	}
}
