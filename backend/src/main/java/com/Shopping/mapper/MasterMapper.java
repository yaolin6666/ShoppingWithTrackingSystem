package com.Shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Shopping.domain.Master;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface MasterMapper extends BaseMapper<Master> {
    @Update("update master set shipping_sn = #{shippingSn} where order_sn = #{tradeNo}")
    int updateState(@Param("tradeNo") String tradeNo, @Param("shippingSn") int state);
}
