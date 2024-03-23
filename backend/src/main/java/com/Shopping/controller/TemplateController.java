package com.Shopping.controller;

import com.Shopping.common.lang.Result;
import com.Shopping.domain.Template;
import com.Shopping.mapper.TeamMapper;
import com.Shopping.mapper.TemplateMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController {
    @Resource
    TeamMapper teamMapper;
    @Resource
    TemplateMapper templateMapper;
    @GetMapping("/findAll")
    public List<Template> findAll(@RequestParam Integer shopId) {
        List<Template> templates = templateMapper.selectList( Wrappers.<Template>lambdaQuery().eq(Template::getShopId,shopId));
        return templates;
    }
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        templateMapper.deleteById(id);
        return Result.success();
    }
    @PutMapping("/update")
    public Result<?> update(@RequestBody Template template) {
        templateMapper.updateById(template);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Template template) {
        templateMapper.insert(template);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") Integer shopId,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Template> templatePage = templateMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Template>lambdaQuery().eq(Template::getShopId, shopId));
        LambdaQueryWrapper<Template> query = Wrappers.<Template>lambdaQuery().orderByDesc(Template::getTemplateId);
        return Result.success(templatePage);
    }
}
