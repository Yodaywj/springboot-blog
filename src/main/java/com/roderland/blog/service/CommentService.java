package com.roderland.blog.service;

import com.roderland.blog.po.Comment;

import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-07---14:23
*/
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
