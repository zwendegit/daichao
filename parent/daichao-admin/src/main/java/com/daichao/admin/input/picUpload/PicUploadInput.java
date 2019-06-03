package com.daichao.admin.input.picUpload;

import org.springframework.web.multipart.MultipartFile;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class PicUploadInput extends BaseInput{

	@ApiParam("图片类型 product：产品 ,tag: tag标签,banner:banner,popup：弹框; default:default")
	private String type;
	@ApiParam("图片流")
	private MultipartFile file;
}
