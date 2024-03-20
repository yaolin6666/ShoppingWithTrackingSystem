package com.Shopping.controller;


import cn.hutool.core.util.StrUtil;
import com.Shopping.domain.Product;
import com.Shopping.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Comment;
import com.Shopping.mapper.CommentMapper;
import com.Shopping.service.CommentSerivce;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentSerivce commentSerivce;

    @Resource
    CommentMapper commentMapper;

    @Resource
    ProductMapper productMapper;

    @PostMapping("/add")
    public Result<?> save(@Validated @RequestBody Comment comment) {
        commentSerivce.save(comment);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@Validated @RequestBody Comment comment) {
        commentSerivce.updateById(comment);
        return Result.success();
    }

    @DeleteMapping("/{commentId}")
    public Result<?> delete(@PathVariable Integer commentId) {
        commentSerivce.removeById(commentId);
        return Result.success();

    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        commentSerivce.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/findAll")
    public Result findAll(@RequestParam(defaultValue = "0") Integer shopId) {
        List<Integer> productList = productMapper.selectList(Wrappers.<Product>lambdaQuery().eq(Product::getCustomerId, shopId)).stream().map(e -> e.getProductId()).collect(Collectors.toList());
        List<Comment> comments;
        if (productList.size() != 0) {
            comments = commentMapper.selectList(Wrappers.<Comment>lambdaQuery().in(Comment::getProductId, productList));
        } else {
            comments = commentMapper.selectList(Wrappers.<Comment>lambdaQuery().in(Comment::getProductId, -1));
        }
        return Result.success(comments);
    }

    @GetMapping("/count")
    public Result Count() {
        Integer count = commentMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/find/{productId}")
    public Result<?> findProductId(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                   @PathVariable Integer productId) {
        Page<Comment> page = commentMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Comment>lambdaQuery().eq(Comment::getProductId, productId));
        return Result.success(page);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") Integer shopId) {
        new Page<>(pageNum, pageSize);
        List<Integer> productList = productMapper.selectList(Wrappers.<Product>lambdaQuery().eq(Product::getCustomerId, shopId)).stream().map(e -> e.getProductId()).collect(Collectors.toList());
        Page<Comment> customerInfoPage;
        if (productList.size() != 0) {
            customerInfoPage = commentMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Comment>lambdaQuery().like(Comment::getCustomerName, search).in(Comment::getProductId, productList));
        } else {
            customerInfoPage = commentMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Comment>lambdaQuery().eq(Comment::getCommentId, -1));
        }
        LambdaQueryWrapper<Comment> query = Wrappers.<Comment>lambdaQuery().orderByDesc(Comment::getCommentId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Comment::getCustomerName, search);
        }
        return Result.success(customerInfoPage);
    }

}

