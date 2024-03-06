package com.Shopping.service.impl;

import com.Shopping.domain.Brand;
import com.Shopping.mapper.BrandMapper;
import com.Shopping.service.BrandSerivce;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandSerivce {

}
