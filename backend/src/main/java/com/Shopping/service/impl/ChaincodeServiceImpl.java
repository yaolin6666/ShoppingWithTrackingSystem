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
        ArginfoOriginVo arginfoOriginVo = JSON.parseObject(originInfo.getOriginInfo(), ArginfoOriginVo.class);
        return arginfoOriginVo;
    }

    @Override
    public DeliverinfoOriginVo convertOriginToDel(OriginInfo originInfo) {
        DeliverinfoOriginVo deliverinfoOriginVo = JSON.parseObject(originInfo.getOriginInfo(), DeliverinfoOriginVo.class);
        return deliverinfoOriginVo;
    }

    @Override
    public OrderOriginVo convertOriginToOrd(OriginInfo originInfo) {
        OrderOriginVo orderOriginVo = JSON.parseObject(originInfo.getOriginInfo(), OrderOriginVo.class);
        return orderOriginVo;
    }

    @Override
    public List<ArginfoOriginVo> convertOriginToArgList(List<OriginInfo> originInfos) {
        List<ArginfoOriginVo> arginfoOriginVoList=new ArrayList<>();
        for(int i=0;i<originInfos.size();i++){
            ArginfoOriginVo arginfoOriginVo = JSON.parseObject(originInfos.get(i).getOriginInfo(), ArginfoOriginVo.class);
            arginfoOriginVoList.add(arginfoOriginVo);
        }
        return arginfoOriginVoList;
    }

    @Override
    public List<DeliverinfoOriginVo> convertOriginToDelList(List<OriginInfo> originInfos) {
        List<DeliverinfoOriginVo> deliverinfoOriginVoList=new ArrayList<>();
        for(int i=0;i<originInfos.size();i++){
            DeliverinfoOriginVo deliverinfoOriginVo = JSON.parseObject(originInfos.get(i).getOriginInfo(), DeliverinfoOriginVo.class);
            deliverinfoOriginVoList.add(deliverinfoOriginVo);
        }
        return deliverinfoOriginVoList;
    }

    @Override
    public List<OrderOriginVo> convertOriginToOrdList(List<OriginInfo> originInfos) {
        List<OrderOriginVo> originVoList=new ArrayList<>();
        for(int i=0;i<originInfos.size();i++){
            OrderOriginVo orderOriginVo = JSON.parseObject(originInfos.get(i).getOriginInfo(), OrderOriginVo.class);
            originVoList.add(orderOriginVo);
        }
        return originVoList;
    }
}
