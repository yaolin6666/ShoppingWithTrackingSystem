package com.Shopping.controller;

import com.Shopping.common.lang.Result;
import com.Shopping.vo.OriginInfo;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chaincode")
@Slf4j
@AllArgsConstructor
public class ChaincodeController {
    final Gateway gateway;
    final Contract contract;

    @GetMapping("/{key}")
    public Map<String, Object> queryOriginInfoByKey(@PathVariable String key) throws GatewayException {
        Map<String, Object> result = Maps.newConcurrentMap();
        byte[] output = contract.evaluateTransaction("queryOriginInfo", key);
        result.put("payload", StringUtils.newStringUtf8(output));
        result.put("status", "ok");
        return result;
    }

    @PostMapping ("/create")
    public Result createOriginInfo(@RequestBody OriginInfo originInfo) throws Exception {
        Map<String, Object> result = Maps.newConcurrentMap();
        byte[] bytes = contract.submitTransaction("createOriginInfo", JSON.toJSONString(originInfo));
        result.put("payload", StringUtils.newStringUtf8(bytes));
        result.put("status", "ok");
        return Result.success();
    }

    @DeleteMapping("/{key}")
    public Result deleteCatByKey(@PathVariable String key) throws Exception {
        Map<String, Object> result = Maps.newConcurrentMap();
        byte[] cat = contract.submitTransaction("deleteCat" , key);
        result.put("payload", StringUtils.newStringUtf8(cat));
        result.put("status", "ok");
        return Result.success();
    }
}