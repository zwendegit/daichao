package com.daichao.controller.ucenter.controller;

import com.daichao.bean.output.ResultOutput;
import com.daichao.controller.CommonController;
import com.daichao.service.DcUserService;
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
@RequestMapping("/User")
@Api(tags = "1.UserCenten", description = "用户中心")
public class UserController extends CommonController {
    @Resource
    private DcUserService userService;

    @RequestMapping(value="/sendVerificationCode",method = RequestMethod.POST)
    @ApiOperation(value = "发送验证码", notes = "注册，找回密码等等")
    ResultOutput sendVerificationCode(
            @ApiParam(value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(value = "渠道标示(h5,ios,android)", required = true) @RequestParam String device,
            @ApiParam("签名") @RequestParam(required = false) String clientSecret,
            @ApiParam("图形验证码") @RequestParam(required = false) String validateCode) {
        return userService.sendVerificationCode(phone, validateCode, device, clientSecret);
    }

    @RequestMapping(value="/chanelSignUp",method = RequestMethod.POST)
    @ApiOperation(value = "渠道注册", notes = "渠道注册")
    ResultOutput chanelSignUp(
            @ApiParam(value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(value = "验证码", required = true) @RequestParam String verificationCode,
            @ApiParam("注册渠道标示") @RequestParam(required = false) String channel,
            @ApiParam("图形验证码") @RequestParam(required = false) String validateCode) {
        return userService.chanelSignUp(phone, verificationCode, channel, validateCode);
    }

    @RequestMapping(value="/signUp",method = RequestMethod.POST)
    @ApiOperation(value = "注册或登陆", notes = "用户注册或者登陆")
    ResultOutput signUp(
            @ApiParam(value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(value = "验证码", required = true) @RequestParam String verificationCode,
            @ApiParam("登陆渠道标示") @RequestParam(required = false) String loginChannel,
            @ApiParam("图形验证码") @RequestParam(required = false) String validateCode) {
        return userService.signUp(phone, verificationCode, loginChannel, request, validateCode);
    }

    @RequestMapping(value="/signIn",method = RequestMethod.POST)
    @ApiOperation(value = "密码登录", notes = "密码登录")
    ResultOutput signIn(
            @ApiParam(value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(value = "登陆渠道标示", required = true) @RequestParam String loginChannel,
            @ApiParam(value = "登录密码", required = true) @RequestParam String password) {
        return userService.signIn(phone, password, loginChannel);
    }

    @RequestMapping(value="/setPassword",method = RequestMethod.POST)
    @ApiOperation(value = "设置密码", notes = "设置密码")
    ResultOutput setPassword(
            @ApiParam(value = "token", required = true) @RequestParam String token,
            @ApiParam(value = "登录密码", required = true) @RequestParam String password,
            @ApiParam(value = "确认登录密码", required = true) @RequestParam String passwordConfirm) {
        return userService.setPassword(token, password, passwordConfirm);
    }

    @RequestMapping(value="/findPassword",method = RequestMethod.POST)
    @ApiOperation(value = "找回密码", notes = "利用手机验证码找回密码")
    ResultOutput findPassword(
            @ApiParam(value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(value = "登录密码", required = true) @RequestParam String password,
            @ApiParam(value = "确认登录密码", required = true) @RequestParam String passwordConfirm,
            @ApiParam(value = "验证码", required = true) @RequestParam String verificationCode,
            @ApiParam("登陆渠道标示") @RequestParam(required = false) String loginChannel,
            @ApiParam("图形验证码") @RequestParam(required = false) String validateCode
            ) {
        return userService.findPassword(phone, password, passwordConfirm, verificationCode, loginChannel, validateCode);
    }

    @RequestMapping(value="/resetPassword",method = RequestMethod.POST)
    @ApiOperation(value = "修改密码", notes = "利用旧密码修改密码，需要登录")
    ResultOutput resetPassword(
            @ApiParam(value = "token", required = true) @RequestParam String token,
            @ApiParam(value = "当前密码", required = true)  @RequestParam String password,
            @ApiParam(value = "新密码", required = true) @RequestParam String newPassword,
            @ApiParam(value = "确认新密码", required = true) @RequestParam String passwordConfirm) {
        return userService.resetPassword(token, password, newPassword, passwordConfirm);
    }

    @RequestMapping(value="/needValidateCode",method = RequestMethod.GET)
    @ApiOperation(value = "是否需要图形验证码", notes = "是否需要图形验证码")
    ResultOutput needValidateCode() {
    	//返回code 999代表弹框 
    	//return new ResultOutput("999","");
        return new ResultOutput();
    }
}