package com.Shopping.common.schedule;

import com.Shopping.domain.Master;
import com.Shopping.domain.Shopping;
import com.Shopping.domain.Team;
import com.Shopping.mapper.MasterMapper;
import com.Shopping.mapper.ShoppingMapper;
import com.Shopping.mapper.TeamMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AnnotationScheduleTask {
    @Autowired
    private MasterMapper masterMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    @Async
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void expireOrder(){
        log.info("自动任务开始 清除已过期团购");
        log.info("开始时间:{}",LocalDateTime.now());
        LocalDateTime now=LocalDateTime.now();
        List<Integer> expireTeamIdList=teamMapper.selectList(Wrappers.<Team>lambdaQuery()
                .ge(Team::getExpireTime,now)
                .eq(Team::getStatus,1))
                .stream().map(e->e.getTeamId())
                .collect(Collectors.toList());
        masterMapper.update(null,Wrappers.<Master>lambdaUpdate()
                .in(Master::getTeamId,expireTeamIdList)
                .set(Master::getStatus,1000));
        teamMapper.update(null,Wrappers.<Team>lambdaUpdate()
                .in(Team::getTeamId)
                .set(Team::getStatus,0));
        log.info("结束时间:{}",LocalDateTime.now());
        log.info("任务结束 已清除过期团购 数量为{}",expireTeamIdList.size());
    }

    @Async
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void expireShopping(){
        log.info("自动任务开始 清除超时购物车");
        log.info("开始时间:{}",LocalDateTime.now());
        LocalDateTime now=LocalDateTime.now().minusDays(1);
        List<Integer> expireShopping=shoppingMapper.selectList(Wrappers.<Shopping>lambdaQuery()
                        .le(Shopping::getCreateTime,now))
                .stream().map(e->e.getOrderDetailId())
                .collect(Collectors.toList());
        shoppingMapper.deleteBatchIds(expireShopping);
        log.info("结束时间:{}",LocalDateTime.now());
        log.info("任务结束 已清除时购物车 数量为{}",expireShopping.size());
    }
}
