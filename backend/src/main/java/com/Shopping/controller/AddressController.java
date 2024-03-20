package com.Shopping.controller;


import cn.hutool.core.util.StrUtil;
import com.Shopping.domain.Address;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Shopping.common.lang.Result;
import com.Shopping.mapper.AddressMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressMapper addressMapper;


    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        addressMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/findAll")
    public List<Address> findAll() {
        List<Address> addresses = addressMapper.selectList(null);
        return addresses;
    }

    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "1000") Integer pageSize,
                                    @PathVariable Integer customerId) {
        Page<Address> page = addressMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Address>lambdaQuery().eq(Address::getCustomerId, customerId));
        return Result.success(page);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Address address) {
        addressMapper.updateById(address);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Address address) {
        addressMapper.insert(address);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        addressMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Address find(@PathVariable("id") Integer id) {
        return this.addressMapper.selectById(id);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Address> goodPage = addressMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Address>lambdaQuery().like(Address::getAddressDescribe, search));
        LambdaQueryWrapper<Address> query = Wrappers.<Address>lambdaQuery().orderByDesc(Address::getAddressId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Address::getAddressDescribe, search);
        }
        return Result.success(goodPage);
    }
}

