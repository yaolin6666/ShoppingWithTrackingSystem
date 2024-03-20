package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.Shopping.domain.Master;
import com.Shopping.mapper.MasterMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.Shopping.common.lang.Result;
import com.Shopping.domain.Receipt;
import com.Shopping.mapper.ReceiptMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    @Resource
    private MasterMapper masterMapper;

    @GetMapping("/findAll")
    public List<Master> findAll() {
        List<Master> masters = masterMapper.selectList(null);
        return masters;
    }

    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId) {
        Page<Master> page = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery().eq(Master::getCustomerId, customerId));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        masterMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Master find(@PathVariable("id") Integer id) {
        return this.masterMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Master master) {
        masterMapper.updateById(master);
        return Result.success();
    }

    @PostMapping("/add")
    public Result insert(@RequestBody Master master) {
        masterMapper.insert(master);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        masterMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/count")
    public Result Count() {
        Integer count = masterMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") Integer shopId) {
        new Page<>(pageNum, pageSize);
        Page<Master> receiptPage = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery()
                .like(Master::getProductName, search)
                .eq(Master::getShopCustomerId, shopId)
                .ge(Master::getStatus, 200));
        LambdaQueryWrapper<Master> query = Wrappers.<Master>lambdaQuery().orderByDesc(Master::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Master::getProductName, search);
        }
        return Result.success(receiptPage);
    }

}

