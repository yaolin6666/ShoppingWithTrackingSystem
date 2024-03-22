package com.Shopping;


import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.Shopping.domain.ArginfoOrigin;
import com.Shopping.domain.Refund;
import com.Shopping.mapper.ArginfoOriginMapper;
import com.Shopping.mapper.RefundMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringApplicationTests {

    @Autowired
    RefundMapper refundMapper;
    @Autowired
    ArginfoOriginMapper arginfoOriginMapper;

    @Test
    void contextLoads() {
        List<Refund> refunds = refundMapper.selectList(null);
        System.out.println(refunds);

    }
    @Test
    void testIdInput(){
        ArginfoOrigin arginfoOrigin=new ArginfoOrigin();
        arginfoOrigin.setArginfoId(1);
        arginfoOrigin.setArginfoOriginId(new SnowflakeGenerator().next().toString());
        System.out.println(arginfoOrigin);
        arginfoOriginMapper.insert(arginfoOrigin);
    }

}
