package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Refund;
import com.Shopping.mapper.RefundMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/refund")
public class RefundController {

    @Resource
    private RefundMapper refundMapper;

    @GetMapping("/findAll")
    public List<Refund> findAll() {
        List<Refund> refunds = refundMapper.selectList(null);
        return refunds;
    }

    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId) {
        Page<Refund> page = refundMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Refund>lambdaQuery().eq(Refund::getCustomerId, customerId));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        refundMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Refund find(@PathVariable("id") Integer id) {
        return this.refundMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Refund refund) {
        refundMapper.updateById(refund);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Refund refund) {
        refundMapper.insert(refund);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        refundMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/count")
    public Result Count(@RequestParam(defaultValue = "0") Integer shopId) {
        Integer count = refundMapper.selectCount(Wrappers.<Refund>lambdaQuery()
                .eq(Refund::getShopCustomerId, shopId));
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") Integer shopId) {
        new Page<>(pageNum, pageSize);
        Page<Refund> refundPage = refundMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Refund>lambdaQuery()
                .like(Refund::getProductName, search)
                .eq(Refund::getShopCustomerId, shopId));
        LambdaQueryWrapper<Refund> query = Wrappers.<Refund>lambdaQuery().orderByDesc(Refund::getRefundId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Refund::getProductName, search);
        }
        return Result.success(refundPage);
    }

}

