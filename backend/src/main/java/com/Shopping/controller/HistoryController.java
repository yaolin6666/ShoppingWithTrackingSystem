package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.Shopping.domain.History;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Shopping.common.lang.Result;
import com.Shopping.mapper.HistoryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/img")
public class HistoryController {

    @Resource
    private HistoryMapper historyMapper;

    @GetMapping("/findAll")
    public List<History> findAll() {
        List<History> history = historyMapper.selectList(null);
        return history;
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        historyMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public History find(@PathVariable("id") Integer id) {
        return this.historyMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody History history) {
        historyMapper.updateById(history);
        return Result.success();
    }

    @PostMapping("/add")
    public Result insert(@RequestBody History history) {
        historyMapper.insert(history);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        historyMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId) {
        Page<History> page = historyMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<History>lambdaQuery().eq(History::getCustomerId, customerId).orderByDesc(History::getCreateTime));
        return Result.success(page);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<History> imgPage = historyMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<History>lambdaQuery().like(History::getProductName, search).orderByDesc(History::getCreateTime));
        LambdaQueryWrapper<History> query = Wrappers.<History>lambdaQuery().orderByDesc(History::getCreateTime);
        if (StrUtil.isNotBlank(search)) {
            query.like(History::getProductName, search);
        }
        return Result.success(imgPage);
    }

}

