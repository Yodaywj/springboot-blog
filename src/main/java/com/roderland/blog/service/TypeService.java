package com.roderland.blog.service;

import com.roderland.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-04---19:39
*/
public interface TypeService {

    Type saveType(Type type);
    Type getType(Long id);
    Page<Type> listType(Pageable pageable);
    List<Type> listType();
    Type updateType(Long id, Type type);
    void deleteType(Long id);
}
