package com.Shopping.controller;

import com.Shopping.mapper.MasterMapper;
import com.Shopping.mapper.TeamMapper;
import com.Shopping.mapper.TemplateMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teamInfo")
public class TeamInfoController {
    @Resource
    MasterMapper masterMapper;
    @Resource
    TeamMapper teamMapper;
    @Resource
    TemplateMapper templateMapper;
}
