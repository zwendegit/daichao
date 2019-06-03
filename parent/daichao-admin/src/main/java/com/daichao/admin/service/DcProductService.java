package com.daichao.admin.service;

import java.math.BigDecimal;
import java.util.List;

import com.daichao.admin.input.product.ProductEditInput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcProductRechargeLog;
import com.daichao.bean.product.DcThirdProduct;

public interface DcProductService {

	List<DcThirdProduct> queryProductList(String name,Integer status);
	
	ResultOutput productDetail(Integer productId);
	
	ResultOutput productEdit(ProductEditInput input,Integer userId); 
	
	ResultOutput productStatusUpdate(Integer status,Integer productId,Integer userId);
	
	ResultOutput productSortUpdate(Integer sort,Integer productId,Integer userId);
	
	ResultOutput productUpdateBatch(Integer status,String productId,Integer userId);
	
	ResultOutput productRecharge(BigDecimal amount,Integer productId,Integer userId);
	
	List<DcProductRechargeLog> productRechargeLog(String productName);
}
