package com.Shopping;


import com.Shopping.domain.Refund;
import com.Shopping.mapper.RefundMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringApplicationTests {

    @Autowired
    RefundMapper refundMapper;

    @Test
    void contextLoads() {
        List<Refund> refunds = refundMapper.selectList(null);
        System.out.println(refunds);

    }

}
