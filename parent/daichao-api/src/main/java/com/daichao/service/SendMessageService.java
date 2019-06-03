package com.daichao.service;

import com.daichao.bean.output.ResultOutput;

public interface SendMessageService {

	/**
     * 发送短信
     * @param uid 业务类型
     * @param mobile 手机号
     * @param content 内容
     * @param code 验证码
     * @param templateId 模板id
     * @return
     * @throws YaolingException 
     */
	ResultOutput sendMessage(String mobile,String content,String code,Integer templateId,String thirdChannel);
}
