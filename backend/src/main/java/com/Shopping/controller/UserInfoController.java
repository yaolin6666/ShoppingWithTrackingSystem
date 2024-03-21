package com.Shopping.controller;


import cn.hutool.core.util.StrUtil;

import com.Shopping.domain.Account;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.Shopping.common.lang.Result;
import com.Shopping.mapper.AccountMapper;
import com.Shopping.service.AccountSerivce;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/account")
public class UserInfoController {

    @Resource
    AccountMapper accountMapper;
    @Resource
    AccountSerivce accountSerivce;

    @PostMapping
    public Result<?> save(@Validated @RequestBody Account account){
        Account res = accountSerivce.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getUsername, account.getUsername()));
        if (res != null){
            return Result.fail("用户名已存在");
        }
        accountSerivce.save(account);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@Validated @RequestBody Account account){
        accountSerivce.updateById(account);
        return Result.success();
    }
    @DeleteMapping("/{customerId}")
    public Result<?> delete(@PathVariable Integer customerId){
        accountSerivce.removeById(customerId);
        return Result.success();
    }
    @GetMapping("/find/{id}")
    public Account find(@PathVariable("id") Integer id){
        return this.accountMapper.selectById(id);
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
         accountSerivce.removeByIds(ids);
         return Result.success();
    }

    @GetMapping("/count")
    public Result Count(){
        Integer count = accountMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(pageNum,pageSize);
        Page<Account> customerInfoPage = accountMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Account>lambdaQuery().like(Account::getUsername, search).or().like(Account::getCustomerName,search));
        LambdaQueryWrapper<Account> query = Wrappers.<Account>lambdaQuery().orderByDesc(Account::getAccountId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Account::getUsername, search).or().like(Account::getCustomerName,search);
        }
        return Result.success(customerInfoPage);
    }
    @GetMapping("/pageShop")
    public Result<?> findPageShop(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(pageNum,pageSize);
        Page<Account> customerInfoPage = accountMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Account>lambdaQuery()
                .ge(Account::getRole,2).and(accountLambdaQueryWrapper ->{
                    accountLambdaQueryWrapper.like(Account::getUsername, search).or().like(Account::getCustomerName,search);
                })
        );
        LambdaQueryWrapper<Account> query = Wrappers.<Account>lambdaQuery().orderByDesc(Account::getAccountId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Account::getUsername, search).or().like(Account::getCustomerName,search);
        }
        return Result.success(customerInfoPage);
    }
}

