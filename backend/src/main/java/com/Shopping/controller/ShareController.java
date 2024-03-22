package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Share;
import com.Shopping.mapper.ShareMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Resource
    ShareMapper shareMapper;

    @PostMapping("/add")//添加货源
    public Result insert(@RequestBody Share share) {
        shareMapper.insert(share);
        return Result.success();
    }

    @GetMapping("/find")
    public List<Share> find(@RequestParam(defaultValue = "") Integer accountId) {
        List<Share> shares = shareMapper.selectList(Wrappers.<Share>lambdaQuery()
                .eq(Share::getAccountId, accountId));
        LambdaQueryWrapper<Share> query = Wrappers.<Share>lambdaQuery().orderByDesc(Share::getShareId);
        return shares;
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        shareMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "") String productId) {
        new Page<>(pageNum, pageSize);
        Page<Share> sharePage = shareMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Share>lambdaQuery()
                .like(Share::getShareContent, search)
                .or()
                .like(Share::getShareTitle, search));
        LambdaQueryWrapper<Share> query = Wrappers.<Share>lambdaQuery().orderByDesc(Share::getShareId);
        if (StrUtil.isNotBlank(productId)) {
            query.like(Share::getProductId, productId);
        }
        return Result.success(sharePage);
    }
}
