package com.daichao.bean.output;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResultOutput implements Serializable{

	private static final long serialVersionUID = 1L;
	private String code="000000";
	private Object result;
	public ResultOutput() {}
	public ResultOutput(String code, Object result) {
		super();
		this.code = code;
		this.result = result;
	}
	public ResultOutput(Object result) {
		super();
		this.result = result;
	}
	
	

}
