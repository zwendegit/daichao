package com.daichao.bean.output;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageOutput implements Serializable{
	private static final long serialVersionUID = -7466323707338602510L;
	private Long total;
	private Integer nowPage;
	private Object data;
	
	public PageOutput() {}
	public PageOutput(Long total, Integer nowPage) {
		super();
		this.total = total;
		this.nowPage = nowPage;
	}
	public PageOutput(Long total, Integer nowPage, Object data) {
		super();
		this.total = total;
		this.nowPage = nowPage;
		this.data = data;
	} 

	
}
