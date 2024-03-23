package com.Shopping.controller;

import com.Shopping.common.lang.Result;
import com.Shopping.domain.Master;
import com.Shopping.domain.Team;
import com.Shopping.domain.Template;
import com.Shopping.mapper.AccountMapper;
import com.Shopping.mapper.MasterMapper;
import com.Shopping.mapper.TeamMapper;
import com.Shopping.mapper.TemplateMapper;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teamInfo")
public class TeamInfoController {
    @Resource
    MasterMapper masterMapper;
    @Resource
    AccountMapper accountMapper;
    @Resource
    TeamMapper teamMapper;
    @Resource
    TemplateMapper templateMapper;
    @GetMapping("/findAll")
    public List<Team> findAll(@RequestParam Integer productId) {
        List<Team> teams = teamMapper.selectList(Wrappers.<Team>lambdaQuery()
                .eq(Team::getProductId,productId).eq(Team::getStatus,1));
        return teams;
    }
    @GetMapping("/find/{id}")
    public Team findById(@PathVariable("id") Integer id) {
        return this.teamMapper.selectById(id);
    }
    @PostMapping("/add")
    public Result insert(@RequestParam Integer templateId,@RequestParam Integer productId,@RequestParam Integer userId) {
        Team team=new Team();
        List<Integer> accountList=new ArrayList<>();
        List<String> accountNameList=new ArrayList<>();
        Template template=templateMapper.selectById(templateId);
        team.setTeamNeed(template.getTeamNeed());
        team.setDiscount(template.getDiscount());
        team.setStatus(1);
        team.setTeamNow(1);
        team.setProductId(productId);
        team.setExpireTime(LocalDateTime.now().plusDays(1).toDateTime());
        accountList.add(userId);
        team.setTeamAttendId(JSON.toJSONString(accountList));
        accountNameList.add(accountMapper.selectById(userId).getCustomerName());
        team.setTeamAttendName(JSON.toJSONString(accountNameList));
        teamMapper.insert(team);
        return Result.success();
    }
    @PutMapping("/update")
    public Result update(@RequestParam Integer teamId,@RequestParam Integer userId){
        //分布式锁 限流
        Team team=teamMapper.selectById(teamId);
        List<Integer> accountList= JSONArray.parseArray(team.getTeamAttendId(),Integer.TYPE);
        List<String> accountNameList=JSONArray.parseArray(team.getTeamAttendName(),String.class);
        accountList.add(userId);
        accountNameList.add(accountMapper.selectById(userId).getCustomerName());
        team.setTeamAttendId(JSON.toJSONString(accountList));
        team.setTeamAttendName(JSON.toJSONString(accountNameList));
        team.setTeamNow((team.getTeamNow()+1));
        if(team.getTeamNow()>=team.getTeamNeed()){
            team.setStatus(0);
            List<Integer> masterIdList=masterMapper.selectList(Wrappers.<Master>lambdaQuery()
                    .eq(Master::getStatus,180)
                    .eq(Master::getProductId,team.getProductId())
                    .in(Master::getCustomerId,accountList))
                    .stream().map(e->e.getOrderId()).collect(Collectors.toList());
                    masterMapper.update(null,Wrappers.<Master>lambdaUpdate().in(Master::getOrderId,masterIdList).set(Master::getStatus,100));
        }
        return Result.success();
    }
}
