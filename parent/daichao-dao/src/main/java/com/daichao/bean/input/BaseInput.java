package com.daichao.bean.input;

import java.io.Serializable;

import lombok.Data;
@Data
public class BaseInput implements Serializable{

	private static final long serialVersionUID = -1448589084043793023L;

	private String access_token;
}
