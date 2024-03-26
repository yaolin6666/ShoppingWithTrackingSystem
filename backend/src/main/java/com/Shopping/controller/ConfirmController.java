package com.Shopping.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.StrUtil;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.DeliverinfoOrigin;
import com.Shopping.domain.Master;
import com.Shopping.mapper.DeliverinfoOriginMapper;
import com.Shopping.mapper.MasterMapper;
import com.Shopping.service.ChaincodeService;
import com.Shopping.vo.DeliverinfoOriginVo;
import com.Shopping.vo.OriginInfo;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping("/confirm")
public class ConfirmController {
    final Gateway gateway;
    final Contract contract;
    @Resource
    private MasterMapper masterMapper;
    @Resource
    DeliverinfoOriginMapper deliverinfoOriginMapper;
    @Resource
    ChaincodeService chaincodeService;

    @GetMapping("/findAll")
    public List<Master> findAll() {
        List<Master> masters = masterMapper.selectList(null);
        masters = masters.stream().filter(e -> (e.getStatus() >= 200 && e.getStatus() < 300)).collect(Collectors.toList());
        return masters;
    }


    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId) {
        Page<Master> page = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery()
                .eq(Master::getCustomerId, customerId)
                .ge(Master::getStatus, 200)
                .lt(Master::getStatus, 300));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        masterMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Master find(@PathVariable("id") Integer id) {
        return this.masterMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Master master) {
        masterMapper.updateById(master);
        return Result.success();
    }

    private void createOriginInfo(OriginInfo originInfo) throws Exception{
        contract.submitTransaction("createOriginInfo", originInfo.getOriginInfoId(), JSON.toJSONString(originInfo));
    }
    @PostMapping("/add")
    public Result insert(@RequestBody Master master) throws Exception {
        master.setStatus(200);
        master.setShippingTime(LocalDateTime.now().toDate());
        DeliverinfoOrigin deliverinfoOrigin=new DeliverinfoOrigin();
        deliverinfoOrigin.setDeliverInfoOriginId(new SnowflakeGenerator().next().toString());
        deliverinfoOrigin.setShippingSn(master.getShippingSn());
        deliverinfoOriginMapper.insert(deliverinfoOrigin);
        masterMapper.updateById(master);
        DeliverinfoOriginVo deliverinfoOriginVo=new DeliverinfoOriginVo();
        deliverinfoOriginVo.setId(deliverinfoOrigin.getDeliverInfoOriginId());
        String content=master.getOrderId().toString()+"发货 物流编号"+master.getShippingSn()+"使用快递名称"+master.getShippingCompName();
        deliverinfoOriginVo.setDeliverInfo(content);
        deliverinfoOriginVo.setCreateTime(java.time.LocalDateTime.now());
        deliverinfoOriginVo.setUpdateTime(java.time.LocalDateTime.now());
        this.createOriginInfo(chaincodeService.convertDeliverInfo(deliverinfoOriginVo));
        return Result.success(deliverinfoOrigin);
    }

    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        masterMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/count")
    public Result Count() {
        Integer count = masterMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Master> masterPage = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery().like(Master::getProductName, search));
        LambdaQueryWrapper<Master> query = Wrappers.<Master>lambdaQuery().orderByDesc(Master::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Master::getProductName, search);
        }
        return Result.success(masterPage);
    }

}

