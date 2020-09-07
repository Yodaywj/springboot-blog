package com.roderland.blog.dao;

import com.roderland.blog.po.Blog;
import com.roderland.blog.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-07---14:26
*/
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Blog> {

//    @Query("select c from Comment c ORDER BY c.createTime DESC ")
//    List<Comment> findByBlogId(Long blogId);

    @Query("select c from Comment c where c.parentComment=null ORDER BY c.createTime DESC ")
    List<Comment> findByBlogId(Long blogId);
}
