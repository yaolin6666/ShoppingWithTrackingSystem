package com.Shopping.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Info;
import com.Shopping.domain.Master;
import com.Shopping.mapper.InfoMapper;
import com.Shopping.mapper.MasterMapper;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/master")
public class MasterController {

    @Resource
    private MasterMapper masterMapper;
    @Resource
    private InfoMapper infoMapper;

    @GetMapping("/findAll")
    public List<Master> findAll(){
        List<Master> masters = masterMapper.selectList(null);
        masters=masters.stream().filter(e->e.getStatus()<200).collect(Collectors.toList());
        return masters;
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Master> page = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery()
                .eq(Master::getCustomerId, customerId)
                .lt(Master::getStatus,200));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        masterMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Master find(@PathVariable("id") Integer id){
        return this.masterMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Master master){
        masterMapper.updateById(master);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Master master){
        masterMapper.insert(master);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        masterMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(@RequestParam Integer shopId){
        LocalDateTime today=LocalDateTime.now();
        LocalDateTime yesterday=today.minusDays(1);
        Integer count = masterMapper.selectCount(Wrappers.<Master>lambdaQuery()
                .eq(Master::getShopCustomerId,shopId)
                .ge(Master::getCreateTime,yesterday)
                .lt(Master::getCreateTime,today)
        );
        return Result.success(count);
    }
    @GetMapping("/shopCount")
    public List<Master> shopCount(@RequestParam Integer shopId){
        List<Master> masters = masterMapper.selectList(Wrappers.<Master>lambdaQuery()
                .eq(Master::getShopCustomerId,shopId));
        return masters;
    }
    @GetMapping("/buy/{productId}")
    public Result buy(@PathVariable Integer productId){
        Info info = infoMapper.selectById(productId);
        String orderSn = IdUtil.getSnowflake().nextIdStr();
        String payUrl = "http://localhost:8888/alipay/pay?subject=" + info.getProductName() + "&traceNo=" + orderSn + "&totalAmount=" + info.getProductPrice();
        return Result.success(payUrl);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") Integer shopId) {
        new Page<>(pageNum, pageSize);
        Page<Master> masterPage = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery()
                .like(Master::getProductName, search)
                .lt(Master::getStatus,200)
                .eq(Master::getShopCustomerId,shopId));
        LambdaQueryWrapper<Master> query = Wrappers.<Master>lambdaQuery()
                .orderByDesc(Master::getOrderId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Master::getProductName, search).lt(Master::getStatus,200);
        }
        return Result.success(masterPage);
    }

}

