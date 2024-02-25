package com.Shopping.service.impl;

import com.Shopping.domain.Comment;
import com.Shopping.mapper.CommentMapper;
import com.Shopping.service.CommentSerivce;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 我自己
 * @since 2021-11-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentSerivce {

}
