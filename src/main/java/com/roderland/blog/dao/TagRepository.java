package com.roderland.blog.dao;

import com.roderland.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-06---8:05
*/
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);


}
