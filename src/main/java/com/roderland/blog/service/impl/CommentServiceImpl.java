package com.roderland.blog.service.impl;

import com.roderland.blog.dao.CommentRepository;
import com.roderland.blog.po.Comment;
import com.roderland.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-07---14:25
*/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentRepository.findByBlogId(blogId);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId!=-1) {
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并所有子评论
        combineChildren(commentsView);
        return commentsView;
    }

    private List<Comment> tempReplies = new ArrayList<>();

    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replyComments = comment.getReplyComments();
            for (Comment replyComment : replyComments) {
                recursively(replyComment);
            }
            comment.setReplyComments(tempReplies);
            tempReplies = new ArrayList<>();
        }
    }

    private void recursively(Comment comment) {
        tempReplies.add(comment);
        if (comment.getReplyComments().size()>0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                recursively(reply);
            }
        }
    }
}
