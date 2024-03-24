package com.Shopping.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Master;
import com.Shopping.domain.OrderOrigin;
import com.Shopping.domain.Refund;
import com.Shopping.mapper.OrderOriginMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/OrderOrigin")
public class OrderOriginController {
    @Autowired
    OrderOriginMapper orderOriginMapper;

    @GetMapping("/queryOrderOrigin")
    public List<Integer> queryOrder(@RequestParam Integer orderId){
        List<String> originIdList=orderOriginMapper.selectList(Wrappers.<OrderOrigin>lambdaQuery()
                .eq(OrderOrigin::getOrderId,orderId))
                .stream().map(e->e.getOrderOriginId()).collect(Collectors.toList());
        /**
         * 区块链
         * */
        return null;
    }
    @PostMapping("/add")
    public Result<?> insert(@RequestBody Master master){
        OrderOrigin orderOrigin=new OrderOrigin();
        orderOrigin.setOrderOriginId(new SnowflakeGenerator().next().toString());
        orderOrigin.setOrderId(master.getOrderId());
        /**
         * 区块链
         * */
        orderOriginMapper.insert(orderOrigin);
        return Result.success();
    }
    @PostMapping("/confirm")
    public Result<?> confirm(@RequestBody Master master){
        OrderOrigin orderOrigin=new OrderOrigin();
        orderOrigin.setOrderOriginId(new SnowflakeGenerator().next().toString());
        orderOrigin.setOrderId(master.getOrderId());
        /**
         * 区块链
         * */
        orderOriginMapper.insert(orderOrigin);
        return Result.success();
    }
    @PostMapping("/refund")
    public Result<?> refund(@RequestBody Master master){
        OrderOrigin orderOrigin=new OrderOrigin();
        orderOrigin.setOrderOriginId(new SnowflakeGenerator().next().toString());
        orderOrigin.setOrderId(master.getOrderId());
        /**
         * 区块链
         * */
        orderOriginMapper.insert(orderOrigin);
        return Result.success();
    }
    @PutMapping("/refund")
    public Result<?> refund(@RequestBody Refund refund){
        OrderOrigin orderOrigin=new OrderOrigin();
        orderOrigin.setOrderOriginId(new SnowflakeGenerator().next().toString());
        orderOrigin.setOrderId(refund.getRefundId());
        /**
         * 区块链
         * */
        orderOriginMapper.insert(orderOrigin);
        return Result.success();
    }
    @PostMapping("/assess")
    public Result<?> assess(@RequestBody Master master){
        OrderOrigin orderOrigin=new OrderOrigin();
        orderOrigin.setOrderOriginId(new SnowflakeGenerator().next().toString());
        orderOrigin.setOrderId(master.getOrderId());
        /**
         * 区块链
         * */
        orderOriginMapper.insert(orderOrigin);
        return Result.success();
    }
    @PostMapping("/expire")
    public Result<?> expire(@RequestBody Master master){
        OrderOrigin orderOrigin=new OrderOrigin();
        orderOrigin.setOrderOriginId(new SnowflakeGenerator().next().toString());
        orderOrigin.setOrderId(master.getOrderId());
        /**
         * 区块链
         * */
        orderOriginMapper.insert(orderOrigin);
        return Result.success();
    }
}
