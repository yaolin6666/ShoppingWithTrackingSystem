package com.Shopping.controller;

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
    public Map<String, Object> createOriginInfo(@RequestBody OriginInfo originInfo) throws Exception {
        Map<String, Object> result = Maps.newConcurrentMap();
        byte[] bytes = contract.submitTransaction("createOriginInfo", JSON.toJSONString(originInfo));
        result.put("payload", StringUtils.newStringUtf8(bytes));
        result.put("status", "ok");
        return result;
    }

//    @PutMapping("/async")
//    public Map<String, Object> createCatAsync(@RequestBody CatDTO cat) throws Exception {
//        Map<String, Object> result = Maps.newConcurrentMap();
//
//        contract.newProposal("createCat")
//                .addArguments(cat.getKey(), cat.getName(), String.valueOf(cat.getAge()), cat.getColor(), cat.getBreed())
//                .build()
//                .endorse()
//                .submitAsync();
//
//        result.put("status", "ok");
//
//        return result;
//    }
//
//    @DeleteMapping("/{key}")
//    public Map<String, Object> deleteCatByKey(@PathVariable String key) throws Exception {
//
//        Map<String, Object> result = Maps.newConcurrentMap();
//
//        byte[] cat = contract.submitTransaction("deleteCat" , key);
//
//        result.put("payload", StringUtils.newStringUtf8(cat));
//        result.put("status", "ok");
//
//        return result;
//    }
//
//    @GetMapping("/private/{collection}/{key}")
//    public Map<String, Object> queryPrivateCatByKey(@PathVariable String collection , @PathVariable String key) throws Exception {
//
//        Map<String, Object> result = Maps.newConcurrentMap();
//        byte[] cat = contract.evaluateTransaction("queryPrivateCat", collection , key);
//
//        result.put("payload", StringUtils.newStringUtf8(cat));
//        result.put("status", "ok");
//
//        return result;
//    }
//
//    @GetMapping("/private/hash/{collection}/{key}")
//    public Map<String, Object> queryPrivateCatHashByKey(@PathVariable String collection , @PathVariable String key) throws Exception {
//
//        Map<String, Object> result = Maps.newConcurrentMap();
//        byte[] hash = contract.evaluateTransaction("queryPrivateCatHash", collection , key);
//
//        result.put("payload", StringUtils.newStringUtf8(hash));
//        result.put("status", "ok");
//
//        return result;
//    }
//
//    @PutMapping("private/")
//    public Map<String, Object> createPrivateCat(@RequestBody PrivateCatDTO privateCat) throws Exception {
//
//        Map<String, Object> result = Maps.newConcurrentMap();
//
//        CatDTO cat = privateCat.getCat();
//
//        byte[] bytes = contract.submitTransaction("createPrivateCat" , privateCat.getCollection() , cat.getKey(), cat.getName(), String.valueOf(cat.getAge()), cat.getColor(), cat.getBreed());
//
//        result.put("payload", StringUtils.newStringUtf8(bytes));
//        result.put("status", "ok");
//        return result;
//    }
//
//    @PostMapping("private/")
//    public Map<String, Object> updatePrivateCat(@RequestBody PrivateCatDTO privateCat) throws Exception {
//
//        Map<String, Object> result = Maps.newConcurrentMap();
//
//        CatDTO cat = privateCat.getCat();
//        byte[] bytes = contract.submitTransaction("updatePrivateCat" ,privateCat.getCollection() , cat.getKey(), cat.getName(), String.valueOf(cat.getAge()), cat.getColor(), cat.getBreed());
//
//        result.put("payload", StringUtils.newStringUtf8(bytes));
//        result.put("status", "ok");
//
//        return result;
//    }
//
//    @DeleteMapping("/private/{collection}/{key}")
//    public Map<String, Object> deletePrivateCatByKey(@PathVariable String collection , @PathVariable String key) throws Exception {
//
//        Map<String, Object> result = Maps.newConcurrentMap();
//        byte[] cat = contract.evaluateTransaction("deletePrivateCat", collection , key);
//        contract.submitTransaction("deletePrivateCat" ,collection , key);
//
//        result.put("payload", StringUtils.newStringUtf8(cat));
//        result.put("status", "ok");
//
//        return result;
//    }

}