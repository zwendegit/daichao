package com.daichao.service.impl;

import com.daichao.bean.message.DcMessageLog;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.message.DcMessageLogMapper;
import com.daichao.service.SendMessageService;
import com.daichao.utils.DateUtil;
import com.daichao.utils.HttpHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class SendMessageServiceImpl implements SendMessageService{

	@Autowired
	private DcMessageLogMapper dcMessageLogMapper;
	@Override
	public ResultOutput sendMessage(String mobile, String content, String code, Integer templateId,
			String thirdChannel) {
		if("1".equals(thirdChannel)){//季聘
			content="【私房钱】"+content;
//            String pw = MD5.MD5Encode(JPmessageEnum.getEnumPasswordBycode("7917")+DateUtil.getStringDate(new Date(),"yyyyMMddHHmmss")).toLowerCase();
            String pw = "132763";
            String uid = "7917";
            String urlString = new StringBuilder().append("http://47.98.50.99:18002/send.do").append("?uid=")
                    .append(uid).append("&pw=")
                    .append(pw).append("&mb=").append(mobile).append("&ms=").append(content).append("&tm=")
                    .append(DateUtil.getStringDate(new Date(),"yyyyMMddHHmmss")).toString();
            String result = HttpHelper.textGet(urlString);
            DcMessageLog message=new DcMessageLog();
            message.setMobile(mobile);
            message.setContent(content);
            message.setVerifyCode(code);
            if(StringUtils.isEmpty(result)||!"0".equals(result.split(",")[0])) {
            	message.setStatus(2);
            }else {
            	message.setStatus(1);
            }
            message.setErrorMessage(result.split(",")[1]);
            dcMessageLogMapper.insert(message);
        }
		 return new ResultOutput();
	}

}
