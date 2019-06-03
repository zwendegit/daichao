package com.daichao.controller.extension.controller;

import com.daichao.bean.output.ResultOutput;
import com.daichao.controller.CommonController;
import com.daichao.service.DcAppVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hook on 2017/9/1.
 * <p>
 */
@RestController
@RequestMapping("/getVersion")
@Api(tags = "5.versionManage", description = "版本管理")
public class VersionController extends CommonController {

    @Resource
    private DcAppVersionService dcAppVersionService;

    @RequestMapping(value="/getVersion",method = RequestMethod.GET)
    @ApiOperation(value = "获取版本消息", notes = "获取版本消息")
    ResultOutput getVersion(
            @ApiParam("包类型") @RequestParam(required = false) String packageType,
            @ApiParam(value = "设备类型", required = true) @RequestParam String deviceType) {
        return dcAppVersionService.getVersion(deviceType,packageType);
    }
    
    @RequestMapping(value="/getVersionStatus",method = RequestMethod.GET)
    @ApiOperation(value = "获取版本按钮开关", notes = "获取版本按钮开关")
    ResultOutput getVersionStatus(
            @ApiParam("包类型") @RequestParam(required = true) String packageType,
            @ApiParam(value = "设备类型", required = true) @RequestParam String deviceType,
            @ApiParam(value = "版本号", required = true) @RequestParam String version) {
        return dcAppVersionService.getVersionStatus(deviceType,packageType,version);
    }
}