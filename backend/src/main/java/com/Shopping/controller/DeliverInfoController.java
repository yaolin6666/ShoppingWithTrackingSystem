package com.Shopping.controller;

import com.Shopping.mapper.DeliverinfoOriginMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/deliverInfo")
public class DeliverInfoController {
    @Resource
    DeliverinfoOriginMapper deliverinfoOriginMapper;
}
