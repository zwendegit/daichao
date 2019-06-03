package com.daichao.controller.index.controller;

import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.bean.product.DcThirdTag;
import com.daichao.common.SimpleRedisCache;
import com.daichao.controller.CommonController;
import com.daichao.service.DcAdvertService;
import com.daichao.service.DcBannerService;
import com.daichao.service.DcChannelUvLogService;
import com.daichao.service.DcThirdProductService;
import com.daichao.service.DcThirdTagService;
import com.daichao.utils.AesUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Created by hook on 2017/9/1.
 * <p>
 */
@RestController
@RequestMapping("/index")
@Api(tags = "3.indexManage", description = "主页接口")
public class IndexController extends CommonController {

    @Resource
    private DcBannerService dcBannerService;
    @Resource
    private DcThirdTagService dcThirdTagService;
    @Resource
    private DcThirdProductService dcThirdProductService;
    @Resource
    private DcChannelUvLogService dcChannelUvLogService;
    @Resource
    private SimpleRedisCache redis;
    @Resource
    private DcAdvertService dcAdvertService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    public ResultOutput index() {
        Map<String, Object> map = new HashMap<>();
        //获取banner
        List<DcBanner> bannerList = dcBannerService.selectIndexBanner();
        //获取Tag
        List<DcThirdTag> tagList = dcThirdTagService.indexTagList();
        //获取首页广告位产品
        DcThirdProduct product=null;
        if(Objects.isNull(redis.get("indexProduct"))) {
        	product= dcAdvertService.getAdvertProduct(1);
        	if(product!=null) {
        		product.setLinkUrl(null);
        		redis.put("indexProduct", product, 60*5);
        	}
        }else {
        	product= (DcThirdProduct) redis.get("indexProduct");
        }
        //获取product
        List<DcThirdProduct> thirdProductList = dcThirdProductService.selectThirdProduct();
        if(product==null) {
        	if(!CollectionUtils.isEmpty(thirdProductList)) {
        		product=thirdProductList.get(0);
        		product.setLinkUrl(null);
        		redis.put("indexProduct", product, 60*5);
        	}
        }
       
        map.put("indexProduct",product);
        map.put("banner",bannerList);
        map.put("tag",tagList);
        if(!CollectionUtils.isEmpty(thirdProductList)) {
        	for (DcThirdProduct dcThirdProduct : thirdProductList) {
        		dcThirdProduct.setLinkUrl(null);
			}
        }
        map.put("product",thirdProductList);
        return new ResultOutput(map);
    }

    @RequestMapping(value="/userCenterProduct",method = RequestMethod.GET)
    @ApiOperation(value = "个人中心产品列表", notes = "个人中心")
    public ResultOutput userCenterProduct() {
    	 //获取product
    	Map<String, Object> map=new HashMap<String, Object>();
        List<DcThirdProduct> thirdProductList = dcThirdProductService.selectThirdProduct();
        if(!CollectionUtils.isEmpty(thirdProductList)) {
        	for (DcThirdProduct dcThirdProduct : thirdProductList) {
        		dcThirdProduct.setLinkUrl(null);
			}
        }
        map.put("product", thirdProductList);
        return new ResultOutput(map);
    }
    
    @RequestMapping(value="/channelUvLog",method = RequestMethod.POST)
    @ApiOperation(value = "落地页uv统计", notes = "落地页uv统计")
    public ResultOutput channelUvLog(
            @ApiParam(value = "渠道编号", required = true) @RequestParam String channelCode,
            @ApiParam(value = "落地页唯一id", required = true) @RequestParam String deviceId
    ) {
       return dcChannelUvLogService.StatisticsChannel(channelCode, deviceId);
    }

}