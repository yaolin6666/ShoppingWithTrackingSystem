package com.Shopping.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Shopping.domain.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 我自己
 * @since 2021-10-27
 */
@Mapper
public interface CustomerInfoMapper extends BaseMapper<CustomerInfo> {

}
