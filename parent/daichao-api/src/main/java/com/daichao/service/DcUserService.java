package com.daichao.service;

import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcUser;

import javax.servlet.http.HttpServletRequest;

public interface DcUserService {
  
	ResultOutput saveUser(DcUser user);

	ResultOutput sendVerificationCode(String phone, String validateCode, String device, String clientSecret);

	ResultOutput chanelSignUp(String phone, String verificationCode, String channel, String validateCode);

	ResultOutput signUp(String phone, String verificationCode, String loginChannel, HttpServletRequest request, String validateCode);

	ResultOutput signIn(String phone, String password, String loginChannel);

	ResultOutput findPassword(String phone, String password, String passwordConfirm, String verificationCode, String loginChannel, String validateCode);

	ResultOutput resetPassword(String token, String password, String newPassword, String passwordConfirm);

	ResultOutput setPassword(String token, String password, String passwordConfirm);
}
