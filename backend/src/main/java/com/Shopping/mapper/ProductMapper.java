package com.Shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Shopping.domain.Product;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
