package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.Shopping.domain.Master;
import com.Shopping.mapper.MasterMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.Shopping.common.lang.Result;
import com.Shopping.domain.Assess;
import com.Shopping.mapper.AssessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/assess")
public class AssessController {

    @Autowired
    private AssessMapper assessMapper;
    @Autowired
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
        Page<Master> page = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery()
                .eq(Master::getCustomerId, customerId)
                .ge(Master::getStatus, 300)
                .le(Master::getStatus, 500));
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
        master.setStatus(300);
        masterMapper.updateById(master);
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
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Assess> assessPage = assessMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Assess>lambdaQuery().like(Assess::getProductName, search));
        LambdaQueryWrapper<Assess> query = Wrappers.<Assess>lambdaQuery().orderByDesc(Assess::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Assess::getProductName, search);
        }
        return Result.success(assessPage);
    }

}

