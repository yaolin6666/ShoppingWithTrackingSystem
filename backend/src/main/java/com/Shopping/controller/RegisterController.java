package com.Shopping.controller;

import com.Shopping.common.lang.Result;
import com.Shopping.domain.Account;
import com.Shopping.domain.Register;
import com.Shopping.mapper.AccountMapper;
import com.Shopping.mapper.RegisterMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private AccountMapper accountMapper;

    @GetMapping("/findAll")
    public List<Register> findAll() {
        List<Register> registers = registerMapper.selectList(null);
        return registers;
    }

    @GetMapping("/find/{customerId}")
    public Register findCustomerId(@PathVariable Integer customerId) {
        List<Register> registerList = registerMapper.selectList( Wrappers.<Register>lambdaQuery().eq(Register::getAccountId, customerId));
        return registerList.size()>0?registerList.get(0):null;
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        registerMapper.deleteById(id);
        return Result.success();
    }

    @PutMapping("/agreeAccount")
    public Result<?> update(@RequestBody Register register) {
        Account account=accountMapper.selectById(register.getAccountId());
        account.setRole(3);
        accountMapper.updateById(account);
        registerMapper.deleteById(register.getRegisterId());
        return Result.success();
    }
    @PutMapping("/disAgreeAccount")
    public Result<?> update(@RequestBody Account account) {
        account.setRole(2);
        accountMapper.updateById(account);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Register register) {
        registerMapper.insert(register);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        new Page<>(pageNum, pageSize);
        Page<Register> registerPage = registerMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Register>lambdaQuery());
        LambdaQueryWrapper<Register> query = Wrappers.<Register>lambdaQuery().orderByDesc(Register::getRegisterId);
        return Result.success(registerPage);
    }
}
