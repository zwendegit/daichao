package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.product.TagInput;
import com.daichao.admin.input.product.ThirdTagInput;
import com.daichao.admin.service.DcProductTagService;
import com.daichao.admin.service.DcThirdTagService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="ProductTagController",description="标签管理")
@RequestMapping("/productTag")
@RestController
public class ProductTagController extends BaseController{

	@Autowired
	private DcThirdTagService dcThirdTagService;
	@Autowired
	private DcProductTagService dcProductTagService;
	
	@ApiOperation(value = "产品标签列表", notes = "列表")
	@RequestMapping(value="/productTagList",method = RequestMethod.POST)
	public ResultOutput productTagList(ThirdTagInput input){
        return dcProductTagService.queryProductTagList(input);
	}
	
	@ApiOperation(value = "标签列表", notes = "列表")
	@RequestMapping(value="/tagList",method = RequestMethod.POST)
	public ResultOutput tagList(ThirdTagInput input){
        List<DcThirdTag> list=dcThirdTagService.queryThirdTagList(input.getName(), input.getStatus());
		return new ResultOutput(list);
	}
	
	@ApiOperation(value = "标签管理", notes = "编辑")
	@RequestMapping(value="/tagSave",method = RequestMethod.POST)
	public ResultOutput tagEdit(TagInput input,HttpServletRequest request){
		return dcThirdTagService.ThirdTagUpdate(input,getUser(request).getId());
	}
}
