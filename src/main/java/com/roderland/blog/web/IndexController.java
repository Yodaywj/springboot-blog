package com.roderland.blog.web;

import com.roderland.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
    @author: Roderland
    @create: 2020-08-30---18:02
*/
@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name) {
        //int x = 1/0;
//        String name = null;
//        if (name==null) {
//            throw new NotFoundException("博客不存在");
//        }
        System.out.println("======= index ========");
        return "index";
    }
}
