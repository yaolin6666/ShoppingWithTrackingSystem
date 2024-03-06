package com.Shopping.service.impl;

import com.Shopping.domain.Comment;
import com.Shopping.mapper.CommentMapper;
import com.Shopping.service.CommentSerivce;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentSerivce {

}
