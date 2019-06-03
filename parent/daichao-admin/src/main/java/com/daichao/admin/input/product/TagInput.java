package com.daichao.admin.input.product;

import java.util.List;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class TagInput extends BaseInput{

	private Integer id;

    private String tagName;

    private String iconUrl;

    private String remark;

    private Integer sort;
    @ApiParam("1上架 0下架")
    private Integer status;
//    @ApiParam("产品id添加集合")
//    private List<Integer> productAddIds;
//    @ApiParam("产品id删除集合")
//    private List<Integer> productDelIds;
}
