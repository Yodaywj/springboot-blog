package com.roderland.blog.web;

/*
    @author: Roderland
    @create: 2020-09-04---18:54
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/admin-blog")
    public String list() {
        return "admin-blog";
    }
}
