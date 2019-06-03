package com.daichao.controller.product.controller;

import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.controller.CommonController;
import com.daichao.input.product.ProductInput;
import com.daichao.service.DcThirdProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hook on 2017/9/1.
 * <p>
 */
@RestController
@RequestMapping("/product")
@Api(tags = "4.productManage", description = "产品管理")
public class ProductController extends CommonController {

    @Resource
    private DcThirdProductService dcThirdProductService;

    @RequestMapping(value="/product",method = RequestMethod.GET)
    @ApiOperation(value = "产品列表", notes = "产品列表")
    public ResultOutput product(ProductInput input) {
        Page<DcThirdProduct> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcThirdProduct> list=dcThirdProductService.queryProductList(input.getStatus(),input.getProductIds());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
        return new ResultOutput(pageout);
    }

    @RequestMapping(value="/clickStatistics",method = RequestMethod.POST)
    @ApiOperation(value = "产品点击统计", notes = "产品点击统计")
    ResultOutput clickStatistics(
            @ApiParam(value = "token", required = true) @RequestParam String token,
            @ApiParam(value = "产品id", required = true) @RequestParam Integer productId) {
        ResultOutput resultOutput = dcThirdProductService.clickStatistics(token, productId);
        return resultOutput;
    }
}