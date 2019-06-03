package com.daichao.bean.input;

import lombok.Data;

@Data
public class PageInput {

	private String access_token;
	private Integer pageNum =1;
	private Integer pageSize =10;
	
}
