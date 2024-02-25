package com.Shopping.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Shopping.domain.CustomerInfo;
import com.Shopping.mapper.CustomerInfoMapper;
import com.Shopping.service.CustomerInfoSerivce;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 我自己
 * @since 2021-10-27
 */
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoSerivce {

}
