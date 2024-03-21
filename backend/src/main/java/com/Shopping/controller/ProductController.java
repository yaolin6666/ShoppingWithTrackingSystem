package com.Shopping.controller;

import cn.hutool.core.util.StrUtil;
import com.Shopping.common.lang.Result;
import com.Shopping.domain.Account;
import com.Shopping.domain.Product;
import com.Shopping.mapper.AccountMapper;
import com.Shopping.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private AccountMapper accountMapper;

    @GetMapping("/findAll")
    public List<Product> findAll(){
        List<Product> products = productMapper.selectList(null);
        return products;
    }
    @GetMapping("/{cid}")
    public Result<?> find(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer cid){
        Page<Product> page = productMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Product>lambdaQuery().eq(Product::getTwoCategoryId, cid).or().eq(Product::getOneCategoryId,cid));
        return Result.success(page);
    }
    @GetMapping("/{cid}/{bid}")
    public Result<?> findBrand(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer cid,@PathVariable Integer bid){
        Page<Product> page = productMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Product>lambdaQuery().eq(Product::getTwoCategoryId, cid).eq(Product::getBrandId,bid).or().eq(Product::getOneCategoryId,cid).eq(Product::getBrandId,bid));
        return Result.success(page);
    }

    @GetMapping("/find/{oid}/{tid}")
    public Result<?> findId(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @PathVariable Integer oid,@PathVariable Integer tid){
        Page<Product> page = productMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Product>lambdaQuery().eq(Product::getOneCategoryId, oid).eq(Product::getThreeCategoryId,tid));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        productMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/group")
    public Result group(){
        List<Map<String, Object>> id = productMapper.selectMaps(Wrappers.<Product>query().select("one_category_id,COUNT(product_name) as num").groupBy("one_category_id"));
        return Result.success(id);
    }
    @GetMapping("/find/{id}")
    public Product find(@PathVariable("id") Integer id){
        return this.productMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Product product){
        productMapper.updateById(product);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Product product){
        productMapper.insert(product);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        productMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(@RequestParam(defaultValue = "0") Integer customerId){
        Integer count = productMapper.selectCount(Wrappers.<Product>lambdaQuery().eq(Product::getCustomerId,customerId));
        return Result.success(count);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") Integer customerId) {
        new Page<>(pageNum, pageSize);
        Page<Product> infoPage;
        if(customerId==0){
            List<Integer> accountIds=accountMapper.selectList(Wrappers.<Account>lambdaQuery().eq(Account::getRole,3))
                    .stream()
                    .map(e->e.getAccountId())
                    .collect(Collectors.toList());
            infoPage = productMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Product>lambdaQuery()
                    .like(Product::getProductName, search).in(Product::getCustomerId,accountIds));
        }else{
            infoPage = productMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Product>lambdaQuery()
                    .like(Product::getProductName, search)
                    .eq(Product::getCustomerId,customerId));
        }
        LambdaQueryWrapper<Product> query = Wrappers.<Product>lambdaQuery().orderByDesc(Product::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Product::getProductName, search);
        }
        return Result.success(infoPage);
    }


}

