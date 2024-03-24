package com.Shopping.service;

import com.Shopping.vo.ArginfoOriginVo;
import com.Shopping.vo.DeliverinfoOriginVo;
import com.Shopping.vo.OrderOriginVo;
import com.Shopping.vo.OriginInfo;

import java.util.List;

public interface ChaincodeService {
    public OriginInfo convertArgInfoOrigin(ArginfoOriginVo arginfoOriginVo);
    public OriginInfo convertDeliverInfo(DeliverinfoOriginVo deliverinfoOriginVo);
    public OriginInfo convertOrderInfo(OrderOriginVo orderOriginVo);
    public ArginfoOriginVo convertOriginToArg(OriginInfo originInfo);
    public DeliverinfoOriginVo convertOriginToDel(OriginInfo originInfo);
    public OrderOriginVo convertOriginToOrd(OriginInfo originInfo);
    public List<ArginfoOriginVo> convertOriginToArgList(List<OriginInfo> originInfos);
    public List<DeliverinfoOriginVo> convertOriginToDelList(List<OriginInfo> originInfos);
    public List<OrderOriginVo> convertOriginToOrdList(List<OriginInfo> originInfos);
}
