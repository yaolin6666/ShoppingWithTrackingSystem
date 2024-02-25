package com.Shopping.service;

import com.Shopping.domain.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Shopping.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 * 分类信息表 服务类
 * </p>
 *
 * @author 我自己
 * @since 2021-11-15
 */
public interface ProductCategorySerivce extends IService<ProductCategory> {

    List<ProductCategoryVO> getAllProductCategoryVO();

}
