package com.daichao.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.picUpload.PicUploadInput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.utils.SFtpUtil;

@Service
public class PicUploadServiceImpl {

	@Value("${imgFileUrl}")
	private String file_path;
	@Value("${domain.name}")
	private String domainName;
	@Value("${userPic}")
	private String userPic;
	@Value("${sftp.ip}")
	private String ip;
	@Value("${sftp.username}")
	private String username;
	@Value("${sftp.password}")
	private String password;
	@Value("${sftp.port}")
	private String port;
	
	public ResultOutput picUpload(PicUploadInput input) {
		try {
			String fileName = input.getFile().getOriginalFilename();
			String suffix=fileName.substring(fileName.lastIndexOf("."));
			if(StringUtils.isNotEmpty(suffix)) {
				if(!suffix.equals("jpg")&&!"JPG".equals(suffix)&&!"png".equals(suffix)&&!"PNG".equals(suffix)&&!"jpeg".equals(suffix)) {
					suffix=".jpg";
				}
			}
			SFtpUtil sftp = new SFtpUtil(ip, username, password);

			//图片服务器路径
			String realPath = file_path + input.getType() + "/";
			String savePath = domainName +userPic+ input.getType()+ "/";
			String imageName =new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+(int)((Math.random()*9+1)*100000)+suffix;

			byte[] bytes = input.getFile().getBytes();
			if(bytes.length>5242880){
			    return new ResultOutput("1","图片不能大于5M");
			}
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			System.out.println(in.available());
			sftp.connectServer();
			//先删除原来图片
//        sftp.delete(realPath, null,userId+"_");
			//保存新的图片
			sftp.put(in, realPath, imageName);
			sftp.closeServer();
			return new ResultOutput(savePath+imageName);
		}  catch (Exception e) {
			e.printStackTrace();
			return new ResultOutput("1","图片上传失败");
		}
	}
}
