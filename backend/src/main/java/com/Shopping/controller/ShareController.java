package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Share;
import com.Shopping.mapper.ShareMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Resource
    ShareMapper shareMapper;

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "") String productId) {
        new Page<>(pageNum, pageSize);
        Page<Share> sharePage=shareMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Share>lambdaQuery()
                .like(Share::getShareContent,search)
                .or()
                .like(Share::getShareTitle,search));
        LambdaQueryWrapper<Share> query = Wrappers.<Share>lambdaQuery().orderByDesc(Share::getShareId);
        if (StrUtil.isNotBlank(productId)) {
            query.like(Share::getProductId, productId);
        }
        return Result.success(sharePage);
    }
}
