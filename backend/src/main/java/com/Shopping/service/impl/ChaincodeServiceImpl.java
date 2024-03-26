package com.Shopping.service.impl;

import com.Shopping.service.ChaincodeService;
import com.Shopping.vo.ArginfoOriginVo;
import com.Shopping.vo.DeliverinfoOriginVo;
import com.Shopping.vo.OrderOriginVo;
import com.Shopping.vo.OriginInfo;
import com.alibaba.fastjson2.JSON;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChaincodeServiceImpl implements ChaincodeService {
    @Override
    public OriginInfo convertArgInfoOrigin(ArginfoOriginVo arginfoOriginVo) {
        OriginInfo originInfo = new OriginInfo();
        originInfo.setOriginInfoId(arginfoOriginVo.getId());
        originInfo.setOriginInfo(JSON.toJSONString(arginfoOriginVo));
        return originInfo;
    }

    @Override
    public OriginInfo convertDeliverInfo(DeliverinfoOriginVo deliverinfoOriginVo) {
        OriginInfo originInfo = new OriginInfo();
        originInfo.setOriginInfoId(deliverinfoOriginVo.getId());
        originInfo.setOriginInfo(JSON.toJSONString(deliverinfoOriginVo));
        return originInfo;
    }

    @Override
    public OriginInfo convertOrderInfo(OrderOriginVo orderOriginVo) {
        OriginInfo originInfo = new OriginInfo();
        originInfo.setOriginInfoId(orderOriginVo.getId());
        originInfo.setOriginInfo(JSON.toJSONString(orderOriginVo));
        return originInfo;
    }

    @Override
    public ArginfoOriginVo convertOriginToArg(OriginInfo originInfo) {
        ArginfoOriginVo arginfoOriginVo = JSON.parseObject(JSON.parseObject(originInfo.getOriginInfo()).get("originInfo").toString(), ArginfoOriginVo.class);
        return arginfoOriginVo;
    }

    @Override
    public DeliverinfoOriginVo convertOriginToDel(OriginInfo originInfo) {
        DeliverinfoOriginVo deliverinfoOriginVo = JSON.parseObject(JSON.parseObject(originInfo.getOriginInfo()).get("originInfo").toString(), DeliverinfoOriginVo.class);
        return deliverinfoOriginVo;
    }

    @Override
    public OrderOriginVo convertOriginToOrd(OriginInfo originInfo) {
        OrderOriginVo orderOriginVo = JSON.parseObject(JSON.parseObject(originInfo.getOriginInfo()).get("originInfo").toString(), OrderOriginVo.class);
        return orderOriginVo;
    }

    @Override
    public List<ArginfoOriginVo> convertOriginToArgList(List<OriginInfo> originInfos) {
        List<ArginfoOriginVo> arginfoOriginVoList=new ArrayList<>();
        for(int i=0;i<originInfos.size();i++){
            ArginfoOriginVo arginfoOriginVo = JSON.parseObject(JSON.parseObject(originInfos.get(i).getOriginInfo()).get("originInfo").toString(), ArginfoOriginVo.class);
            arginfoOriginVoList.add(arginfoOriginVo);
        }
        return arginfoOriginVoList;
    }

    @Override
    public List<DeliverinfoOriginVo> convertOriginToDelList(List<OriginInfo> originInfos) {
        List<DeliverinfoOriginVo> deliverinfoOriginVoList=new ArrayList<>();
        for(int i=0;i<originInfos.size();i++){
            DeliverinfoOriginVo deliverinfoOriginVo = JSON.parseObject(JSON.parseObject(originInfos.get(i).getOriginInfo()).get("originInfo").toString(), DeliverinfoOriginVo.class);
            deliverinfoOriginVoList.add(deliverinfoOriginVo);
        }
        return deliverinfoOriginVoList;
    }

    @Override
    public List<OrderOriginVo> convertOriginToOrdList(List<OriginInfo> originInfos) {
        List<OrderOriginVo> originVoList=new ArrayList<>();
        for(int i=0;i<originInfos.size();i++){
            OrderOriginVo orderOriginVo = JSON.parseObject(JSON.parseObject(originInfos.get(i).getOriginInfo()).get("originInfo").toString(), OrderOriginVo.class);
            originVoList.add(orderOriginVo);
        }
        return originVoList;
    }
}
