package com.Shopping.service.impl;


import com.Shopping.domain.Account;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Shopping.mapper.AccountMapper;
import com.Shopping.service.AccountSerivce;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountSerivce {

}
