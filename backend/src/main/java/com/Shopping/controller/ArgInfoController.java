package com.Shopping.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.StrUtil;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Arginfo;
import com.Shopping.domain.ArginfoOrigin;
import com.Shopping.mapper.ArginfoMapper;
import com.Shopping.mapper.ArginfoOriginMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/argInfo")
public class ArgInfoController {
    @Autowired
    ArginfoMapper arginfoMapper;
    @Autowired
    ArginfoOriginMapper arginfoOriginMapper;
    @GetMapping("/findAll")
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

    @GetMapping("/queryArgOrigin")
    public ArginfoOrigin queryArgOrigin(@RequestParam Integer argInfoId){
        List<String> argInfoOriginIdList=arginfoOriginMapper.selectList(Wrappers.<ArginfoOrigin>lambdaQuery()
                .eq(ArginfoOrigin::getArginfoId,argInfoId))
                .stream().map(e->e.getArginfoOriginId()).collect(Collectors.toList());
        /**
         * 区块链
         * */
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        arginfoMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        arginfoMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @PostMapping("/add")//添加货源
    public Result insert(@RequestBody Arginfo arginfo) {
        arginfo.setStatus(1);
        arginfo.setCount(-1);
        arginfoMapper.insert(arginfo);
        return Result.success();
    }
    @PutMapping("/update")
    public Result update(@RequestBody Arginfo arginfo){
        arginfoMapper.updateById(arginfo);
        return Result.success();
    }

    @PostMapping("/addArgOrigin")//添加货源详细
    public Result insertOrigin(@RequestParam Integer argInfoId, @RequestParam String content) {
        ArginfoOrigin arginfoOrigin = new ArginfoOrigin();
        String argInfoOriginKey = new SnowflakeGenerator().next().toString();
        arginfoOrigin.setArginfoOriginId(argInfoOriginKey);
        arginfoOrigin.setArginfoId(argInfoId);
        arginfoOriginMapper.insert(arginfoOrigin);
        /**
         * 区块链
         * */
        return Result.success();
    }
    @GetMapping("/page")
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
}
