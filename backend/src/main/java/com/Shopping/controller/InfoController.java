package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.Shopping.common.lang.Result;
import com.Shopping.domain.Info;
import com.Shopping.mapper.InfoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/info")
public class InfoController {

    @Resource
    private InfoMapper infoMapper;

    @GetMapping("/findAll")
    public List<Info> findAll(){
        List<Info> infos = infoMapper.selectList(null);
        return infos;
    }
    @GetMapping("/{cid}")
    public Result<?> find(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer cid){
        Page<Info> page = infoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Info>lambdaQuery().eq(Info::getTwoCategoryId, cid).or().eq(Info::getOneCategoryId,cid));
        return Result.success(page);
    }
    @GetMapping("/{cid}/{bid}")
    public Result<?> findBrand(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer cid,@PathVariable Integer bid){
        Page<Info> page = infoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Info>lambdaQuery().eq(Info::getTwoCategoryId, cid).eq(Info::getBrandId,bid).or().eq(Info::getOneCategoryId,cid).eq(Info::getBrandId,bid));
        return Result.success(page);
    }

    @GetMapping("/find/{oid}/{tid}")
    public Result<?> findId(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer oid,@PathVariable Integer tid){
        Page<Info> page = infoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Info>lambdaQuery().eq(Info::getOneCategoryId, oid).eq(Info::getThreeCategoryId,tid));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        infoMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/group")
    public Result group(){
        List<Map<String, Object>> id = infoMapper.selectMaps(Wrappers.<Info>query().select("one_category_id,COUNT(product_name) as num").groupBy("one_category_id"));
        return Result.success(id);
    }
    @GetMapping("/find/{id}")
    public Info find(@PathVariable("id") Integer id){
        return this.infoMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Info info){
        infoMapper.updateById(info);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Info info){
        infoMapper.insert(info);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        infoMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(){
        Integer count = infoMapper.selectCount(null);
        return Result.success(count);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") Integer customerId) {
        new Page<>(pageNum, pageSize);
        Page<Info> infoPage;
        if(customerId==0){
        infoPage = infoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Info>lambdaQuery().like(Info::getProductName, search));
        }else{
            infoPage = infoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Info>lambdaQuery()
                    .like(Info::getProductName, search)
                    .eq(Info::getCustomerId,customerId));
        }
        LambdaQueryWrapper<Info> query = Wrappers.<Info>lambdaQuery().orderByDesc(Info::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Info::getProductName, search);
        }
        return Result.success(infoPage);
    }


}

