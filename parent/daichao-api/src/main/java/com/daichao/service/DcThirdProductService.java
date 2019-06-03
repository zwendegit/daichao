package com.daichao.service;

import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProduct;

import java.util.List;

public interface DcThirdProductService {

    List<DcThirdProduct> selectThirdProduct();

    List<DcThirdProduct> queryProductList(Integer status, String productIds);

    ResultOutput clickStatistics(String token, Integer productId);
}
