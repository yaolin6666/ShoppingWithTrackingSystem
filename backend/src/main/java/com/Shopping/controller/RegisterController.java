package com.Shopping.controller;

import com.Shopping.common.lang.Result;
import com.Shopping.domain.Register;
import com.Shopping.domain.Shopping;
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

    @GetMapping("/findAll")
    public List<Register> findAll() {
        List<Register> registers = registerMapper.selectList(null);
        return registers;
    }

    @GetMapping("/finds/{customerId}")
    public Register findCustomerId(@PathVariable Integer customerId) {
        List<Register> registerList = registerMapper.selectList( Wrappers.<Register>lambdaQuery().eq(Register::getRegisterId, customerId));
        return registerList.get(0);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        registerMapper.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Register register) {
        registerMapper.updateById(register);
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
