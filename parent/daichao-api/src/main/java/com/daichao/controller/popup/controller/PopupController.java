package com.daichao.controller.popup.controller;

import com.daichao.bean.output.ResultOutput;
import com.daichao.controller.CommonController;
import com.daichao.service.DcPopupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hook on 2017/9/1.
 * <p>
 */
@RestController
@RequestMapping("/popup")
@Api(tags = "6.popupManage", description = "弹窗管理")
public class PopupController extends CommonController {

    @Resource
    private DcPopupService dcPopupService;

    @RequestMapping(value="/popupWindow",method = RequestMethod.GET)
    @ApiOperation(value = "是否需要弹窗", notes = "是否需要弹窗")
    ResultOutput popupWindow() {
        return dcPopupService.popupWindow();
    }
}