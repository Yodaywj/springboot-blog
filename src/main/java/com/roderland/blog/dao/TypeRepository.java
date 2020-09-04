package com.roderland.blog.dao;

import com.roderland.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author: Roderland
    @create: 2020-09-04---19:44
*/
public interface TypeRepository extends JpaRepository<Type, Long> {
}
