package com.daichao.admin.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.product.ProductEditInput;
import com.daichao.admin.input.product.ProductInput;
import com.daichao.admin.input.product.ProductRechargeLogInput;
import com.daichao.admin.service.DcProductService;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcProductRechargeLog;
import com.daichao.bean.product.DcThirdProduct;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(value="ProductController",description="贷超产品")
@RequestMapping("/product")
@RestController
public class ProductController extends BaseController{

	@Autowired
	private DcProductService dcProductService;
	
	@ApiOperation(value = "产品列表", notes = "分页")
	@RequestMapping(value="/productPage",method = RequestMethod.POST)
	public ResultOutput productPage(ProductInput input){
        Page<DcThirdProduct> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcThirdProduct> list=dcProductService.queryProductList(input.getName(), input.getStatus());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	@ApiOperation(value = "产品列表", notes = "列表")
	@RequestMapping(value="/productList",method = RequestMethod.POST)
	public ResultOutput productList(ProductInput input){
        List<DcThirdProduct> list=dcProductService.queryProductList(input.getName(), input.getStatus());
		return new ResultOutput(list);
	}
	@ApiOperation(value = "产品详情", notes = "详情")
	@RequestMapping(value="/productDetail",method = RequestMethod.POST)
	public ResultOutput productDetail(@RequestParam Integer productId){
		return dcProductService.productDetail(productId);
	}
	
	@ApiOperation(value = "产品编辑", notes = "新增")
	@RequestMapping(value="/productSave",method = RequestMethod.POST)
	public ResultOutput productSave(ProductEditInput input,HttpServletRequest request){
		return dcProductService.productEdit(input,getUser(request).getId());
	}
	
	@ApiOperation(value = "产品上下架管理", notes = "上下架")
	@RequestMapping(value="/productStatusUpdate",method = RequestMethod.POST)
	public ResultOutput productSave(@ApiParam("1 上架 2下架") @RequestParam Integer status,@ApiParam("产品ID")@RequestParam Integer productId,HttpServletRequest request){
		return dcProductService.productStatusUpdate(status,productId,getUser(request).getId());
	}
	@ApiOperation(value = "产品上权重管理", notes = "权重")
	@RequestMapping(value="/productSortUpdate",method = RequestMethod.POST)
	public ResultOutput productSortUpdate(@ApiParam("排序") @RequestParam Integer sort,@ApiParam("产品ID")@RequestParam Integer productId,HttpServletRequest request){
		return dcProductService.productSortUpdate(sort,productId,getUser(request).getId());
	}
	@ApiOperation(value = "产品批量上下架管理", notes = "批量上下架")
	@RequestMapping(value="/productUpdateBatch",method = RequestMethod.POST)
	public ResultOutput productUpdateBatch(@ApiParam("1 上架 2下架") @RequestParam Integer status,@ApiParam("产品ID") String productIds,HttpServletRequest request){
		return dcProductService.productUpdateBatch(status,productIds,getUser(request).getId());
	}
	@ApiOperation(value = "产品充值管理", notes = "充值")
	@RequestMapping(value="/productRecharge",method = RequestMethod.POST)
	public ResultOutput productRecharge(@ApiParam("充值金额") @RequestParam BigDecimal amount,@ApiParam("产品ID")@RequestParam Integer productId,HttpServletRequest request){
		return dcProductService.productRecharge(amount,productId,getUser(request).getId());
	}
	@ApiOperation(value = "产品充值-列表", notes = "充值日志")
	@RequestMapping(value="/productRechargeLog",method = RequestMethod.POST)
	public ResultOutput productRechargeLog(ProductRechargeLogInput input){
		Page<DcProductRechargeLog> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcProductRechargeLog> list=dcProductService.productRechargeLog(input.getName());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
}
