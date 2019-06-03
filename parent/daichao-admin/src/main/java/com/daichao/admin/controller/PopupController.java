package com.daichao.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.popup.PopupInput;
import com.daichao.admin.input.popup.PopupSaveInput;
import com.daichao.admin.service.PopupService;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcPopup;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="PopupController",description="弹框管理")
@RequestMapping("/popup")
@RestController
public class PopupController {

	@Autowired
	private PopupService popupService;
	@ApiOperation(value = "弹框列表", notes = "分页")
	@RequestMapping(value="/popupPage",method = RequestMethod.POST)
	public ResultOutput popupPage(PopupInput input){
		 Page<DcPopup> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
		 List<DcPopup> list=popupService.queryPopupList(input);
		 PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
	     pageout.setData(list);
		 return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "弹框管理", notes = "编辑")
	@RequestMapping(value="/popupSave",method = RequestMethod.POST)
	public ResultOutput popupSave(PopupSaveInput input){
		 return popupService.popupSave(input);
	}
}
