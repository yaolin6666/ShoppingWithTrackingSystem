package com.Shopping.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Shopping.domain.CustomerInfo;
import com.Shopping.mapper.CustomerInfoMapper;
import com.Shopping.service.CustomerInfoSerivce;
import org.springframework.stereotype.Service;


@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoSerivce {

}
