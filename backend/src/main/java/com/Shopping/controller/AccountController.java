package com.Shopping.controller;

import cn.hutool.core.map.MapUtil;
import com.Shopping.domain.Account;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.Shopping.common.dto.LoginDto;
import com.Shopping.common.lang.Result;
import com.Shopping.service.AccountSerivce;
import com.Shopping.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {
    @Resource
    AccountSerivce accountSerivce;

    @Resource
    JwtUtils jwtUtils;

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        Account res = accountSerivce.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getUsername, account.getUsername()));
        if (res != null) {
            return Result.fail(400, "用户名已存在");
        }
        accountSerivce.save(account);
        return Result.success();
    }

    //后台登录
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        Account user = accountSerivce.getOne(new QueryWrapper<Account>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(loginDto.getPassword())) {
            return Result.fail("密码不正确");
        } else if (user.getRole().equals(0)) {
            return Result.fail("您不是管理员或者商家");
        }
        String jwt = jwtUtils.generateToken(user.getAccountId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return Result.success(
                MapUtil.builder()
                        .put("id", user.getAccountId())
                        .put("username", user.getUsername())
                        .put("customerName", user.getCustomerName())
                        .put("avatar", user.getAvatar())
                        .put("role", user.getRole())
                        .map()
        );
    }

    //前台登录
    @PostMapping("/logins")
    public Result logins(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        Account user = accountSerivce.getOne(new QueryWrapper<Account>().eq("username", loginDto.getUsername()));

        if (user == null) {
            return Result.fail("用户不存在");
        }
        if (!user.getPassword().equals(loginDto.getPassword())) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getAccountId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getAccountId())
                .put("username", user.getUsername())
                .put("customerName", user.getCustomerName())
                .put("avatar", user.getAvatar())
                .put("role", user.getRole())
                .map()
        );
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success();
    }

}
