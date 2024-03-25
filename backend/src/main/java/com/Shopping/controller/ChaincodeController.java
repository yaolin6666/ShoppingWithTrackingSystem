package com.Shopping.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.StrUtil;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.*;
import com.Shopping.mapper.ArginfoMapper;
import com.Shopping.mapper.ArginfoOriginMapper;
import com.Shopping.mapper.DeliverinfoOriginMapper;
import com.Shopping.mapper.OrderOriginMapper;
import com.Shopping.service.ChaincodeService;
import com.Shopping.vo.ArginfoOriginVo;
import com.Shopping.vo.OriginInfo;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
public class ChaincodeController {

    @Autowired
    OrderOriginMapper orderOriginMapper;
    @Resource
    DeliverinfoOriginMapper deliverinfoOriginMapper;
    @Autowired
    ArginfoMapper arginfoMapper;
    @Autowired
    ArginfoOriginMapper arginfoOriginMapper;
    @Autowired
    ChaincodeService chaincodeService;
    final Gateway gateway;
    final Contract contract;
    @GetMapping("/chaincode/{id}")
    public String testQueryChainCode(@PathVariable String id) throws GatewayException {
        byte[] output = contract.evaluateTransaction("queryOriginInfo", id);
        return StringUtils.newStringUtf8(output);
    }
    @DeleteMapping("/chaincode/{id}")
    public Result deleteByKey(@PathVariable String id) throws Exception {
        Map<String, Object> result = Maps.newConcurrentMap();
        byte[] output = contract.submitTransaction("deleteOriginInfo" , id);
        result.put("payload", StringUtils.newStringUtf8(output));
        result.put("status", "ok");
        return Result.success();
    }

    private OriginInfo queryOriginInfoById(String Id) throws GatewayException{
        byte[] output = contract.evaluateTransaction("queryOriginInfo", Id);
        OriginInfo originInfo=new OriginInfo();
        originInfo.setOriginInfoId(Id);
        originInfo.setOriginInfo(StringUtils.newStringUtf8(output));
        return originInfo;
    }
    private List<OriginInfo> queryOriginInfoByIdList(List<String> IdList) throws GatewayException{
        List<OriginInfo> originInfoList=new ArrayList<>();
        for (int i =0;i< IdList.size();i++){
            byte[] output = contract.evaluateTransaction("queryOriginInfo", IdList.get(i));
            OriginInfo originInfo=new OriginInfo();
            originInfo.setOriginInfoId(IdList.get(i));
            originInfo.setOriginInfo(StringUtils.newStringUtf8(output));
            originInfoList.add(originInfo);
        }
        return originInfoList;
    }
    private void createOriginInfo(OriginInfo originInfo) throws Exception{
        contract.submitTransaction("createOriginInfo", originInfo.getOriginInfoId(),JSON.toJSONString(originInfo));
    }


    @GetMapping("/deliverInfo/queryDeliverInfo")
    public ArginfoOrigin queryDeliverOrigin(@RequestParam Integer shippingSn){
        List<String> deliverInfoOriginIdList=deliverinfoOriginMapper.selectList(Wrappers.<DeliverinfoOrigin>lambdaQuery()
                        .eq(DeliverinfoOrigin::getShippingSn,shippingSn))
                .stream().map(e->e.getDeliverInfoOriginId()).collect(Collectors.toList());
        /**
         * 区块链
         * */
        return null;
    }


    @PostMapping("/deliverInfo/addDeliverInfoOrigin")//添加物流详细
    public Result insertOrigin(@RequestParam String shippingSn, @RequestParam String content) {
        DeliverinfoOrigin deliverinfoOrigin = new DeliverinfoOrigin();
        String deliverInfoOriginKey = new SnowflakeGenerator().next().toString();
        deliverinfoOrigin.setDeliverInfoOriginId(deliverInfoOriginKey);
        deliverinfoOrigin.setShippingSn(shippingSn);
        deliverinfoOriginMapper.insert(deliverinfoOrigin);
        /**
         * 区块链
         * */
        return Result.success();
    }


    @GetMapping("/argInfo/findAll")
    public List<Arginfo> findAll(@RequestParam(defaultValue = "") String productId) {
        List<Arginfo> argInfos = arginfoMapper.selectList(Wrappers.<Arginfo>lambdaQuery()
                .eq(Arginfo::getProductId, productId)
                .gt(Arginfo::getCount,0)
                .eq(Arginfo::getStatus,1));
        LambdaQueryWrapper<Arginfo> query = Wrappers.<Arginfo>lambdaQuery().orderByDesc(Arginfo::getArginfoId);
        if (StrUtil.isNotBlank(productId)) {
            query.like(Arginfo::getShopId, productId);
        }
        return argInfos;
    }

    @GetMapping("/argInfo/queryArgOrigin")
    public List<ArginfoOriginVo> queryArgOrigin(@RequestParam Integer argInfoId) throws GatewayException {
        List<String> argInfoOriginIdList=arginfoOriginMapper.selectList(Wrappers.<ArginfoOrigin>lambdaQuery()
                        .eq(ArginfoOrigin::getArginfoId,argInfoId))
                .stream().map(e->e.getArginfoOriginId()).collect(Collectors.toList());
        /**
         * 区块链
         * */
        List<OriginInfo> originInfoList=this.queryOriginInfoByIdList(argInfoOriginIdList);
        return this.chaincodeService.convertOriginToArgList(originInfoList);
    }
    @PostMapping("/argInfo/addArgOrigin")//添加货源详细
    public Result insertOrigin(@RequestBody ArginfoOriginVo arginfoOriginVo) throws Exception {
        ArginfoOrigin arginfoOrigin = new ArginfoOrigin();
        String argInfoOriginKey = new SnowflakeGenerator().next().toString();
        arginfoOriginVo.setId(argInfoOriginKey);
        arginfoOriginVo.setCreateTime(LocalDateTime.now());
        arginfoOriginVo.setUpdateTime(arginfoOriginVo.getCreateTime());
        arginfoOrigin.setArginfoOriginId(argInfoOriginKey);
        arginfoOrigin.setArginfoId(Integer.valueOf(arginfoOriginVo.getArginfoId()));
        arginfoOriginMapper.insert(arginfoOrigin);
        /**
         * 区块链
         * */
        this.createOriginInfo(chaincodeService.convertArgInfoOrigin(arginfoOriginVo));
        return Result.success();
    }

    @DeleteMapping("/argInfo/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        arginfoMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/argInfo/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        arginfoMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @PostMapping("/argInfo/add")//添加货源
    public Result insert(@RequestBody Arginfo arginfo) {
        arginfo.setStatus(1);
        arginfo.setCount(-1);
        arginfoMapper.insert(arginfo);
        return Result.success();
    }
    @PutMapping("/argInfo/update")
    public Result update(@RequestBody Arginfo arginfo){
        arginfoMapper.updateById(arginfo);
        return Result.success();
    }

    @GetMapping("/argInfo/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "-1") Integer productId,
                              @RequestParam(defaultValue = "") Integer shopId) {
        new Page<>(pageNum, pageSize);
        Page<Arginfo> arginfoPage = arginfoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Arginfo>lambdaQuery().eq(Arginfo::getShopId, shopId));
        LambdaQueryWrapper<Arginfo> query = Wrappers.<Arginfo>lambdaQuery().orderByDesc(Arginfo::getArginfoId);
        if (productId>0) {
            query.eq(Arginfo::getProductId, productId);
        }
        return Result.success(arginfoPage);
    }

    @GetMapping("/argInfo/pageUser")
    public Result<?> findPageUser(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "-1") Integer productId
                              ) {
        new Page<>(pageNum, pageSize);
        Page<Arginfo> arginfoPage = arginfoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Arginfo>lambdaQuery().eq(Arginfo::getProductId, productId).eq(Arginfo::getStatus,2));
        LambdaQueryWrapper<Arginfo> query = Wrappers.<Arginfo>lambdaQuery().orderByDesc(Arginfo::getArginfoId);
        return Result.success(arginfoPage);
    }

    @GetMapping("/argInfo/findAllUser")
    public Result<?> findAllUser(@RequestParam(defaultValue = "-1") Integer productId) {
        List<Arginfo> arginfoList = arginfoMapper.selectList( Wrappers.<Arginfo>lambdaQuery().eq(Arginfo::getProductId, productId).eq(Arginfo::getStatus,2));
        LambdaQueryWrapper<Arginfo> query = Wrappers.<Arginfo>lambdaQuery().orderByDesc(Arginfo::getArginfoId);
        return Result.success(arginfoList);
    }


    @GetMapping("/OrderOrigin/queryOrderOrigin")
    public List<Integer> queryOrder(@RequestParam Integer orderId){
        List<String> originIdList=orderOriginMapper.selectList(Wrappers.<OrderOrigin>lambdaQuery()
                        .eq(OrderOrigin::getOrderId,orderId))
                .stream().map(e->e.getOrderOriginId()).collect(Collectors.toList());
        /**
         * 区块链
         * */
        return null;
    }
    @PostMapping("/OrderOrigin/add")
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
    @PostMapping("/OrderOrigin/confirm")
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
    @PostMapping("/OrderOrigin/refund")
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
    @PutMapping("/OrderOrigin/refund")
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
    @PostMapping("/OrderOrigin/assess")
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
    @PostMapping("/OrderOrigin/expire")
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