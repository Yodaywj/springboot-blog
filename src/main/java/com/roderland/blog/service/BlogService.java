package com.roderland.blog.service;

import com.roderland.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
    @author: Roderland
    @create: 2020-09-06---8:59
*/
public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> listBlog(Pageable pageable, Blog blog);
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id, Blog blog);
    void deleteBlog(Long id);
}
