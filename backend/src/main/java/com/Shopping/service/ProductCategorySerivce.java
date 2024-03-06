package com.Shopping.service;

import com.Shopping.domain.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Shopping.vo.ProductCategoryVO;

import java.util.List;


public interface ProductCategorySerivce extends IService<ProductCategory> {

    List<ProductCategoryVO> getAllProductCategoryVO();

}
