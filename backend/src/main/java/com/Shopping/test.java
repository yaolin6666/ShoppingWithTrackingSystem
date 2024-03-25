package com.Shopping;

import com.alibaba.fastjson2.JSON;

public class test {
    public static void main(String[] args) {
        String a="{\"originInfo\":\"{\\\"argInfo\\\":\\\"测试数据\\\",\\\"arginfoId\\\":\\\"2\\\",\\\"createTime\\\":\\\"2024-03-25 15:56:35.335347200\\\",\\\"id\\\":\\\"1772170701923418112\\\",\\\"mediaInfo\\\":\\\"https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/data/c36eb056-1f6f-4b5e-bc98-849e129188ce.jpg\\\",\\\"updateTime\\\":\\\"2024-03-25 15:56:35.335347200\\\"}\",\"originInfoId\":\"1772170701923418112\"}";
        String b=JSON.parseObject(a).get("originInfo").toString();
        System.out.println(b);

    }
}
