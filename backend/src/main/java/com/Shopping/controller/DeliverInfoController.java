package com.Shopping.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.ArginfoOrigin;
import com.Shopping.domain.DeliverinfoOrigin;
import com.Shopping.mapper.DeliverinfoOriginMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliverInfo")
public class DeliverInfoController {
    @Resource
    DeliverinfoOriginMapper deliverinfoOriginMapper;

    @GetMapping("/queryDeliverInfo")
    public ArginfoOrigin queryArgOrigin(@RequestParam Integer shippingSn){
        List<String> deliverInfoOriginIdList=deliverinfoOriginMapper.selectList(Wrappers.<DeliverinfoOrigin>lambdaQuery()
                        .eq(DeliverinfoOrigin::getShippingSn,shippingSn))
                .stream().map(e->e.getDeliverInfoOriginId()).collect(Collectors.toList());
        /**
         * 区块链
         * */
        return null;
    }


    @PostMapping("/addDeliverInfoOrigin")//添加货源详细
    public Result insertOrigin(@RequestParam String shippingSn, @RequestParam String content) {
        DeliverinfoOrigin deliverinfoOrigin = new DeliverinfoOrigin();
        String deliverInfoOriginKey = new SnowflakeGenerator().next().toString();
        deliverinfoOrigin.setDeliverInfoOriginId(deliverInfoOriginKey);
        deliverinfoOrigin.setShippingSn(shippingSn);
        deliverinfoOriginMapper.insert(deliverinfoOrigin);
        /**
         * 区块链
         * */
        return Result.success();
    }
}
