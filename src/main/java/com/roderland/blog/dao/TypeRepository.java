package com.roderland.blog.dao;

import com.roderland.blog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-04---19:44
*/
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
