package com.daichao.admin.input.admin;

import com.daichao.bean.input.BaseInput;

import lombok.Data;
@Data
public class AdminUserInput extends BaseInput{

	private String username;
	private String password;
}
