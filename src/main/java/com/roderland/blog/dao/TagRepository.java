package com.roderland.blog.dao;

import com.roderland.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author: Roderland
    @create: 2020-09-06---8:05
*/
public interface TagRepository extends JpaRepository<Tag, Long> {


}
