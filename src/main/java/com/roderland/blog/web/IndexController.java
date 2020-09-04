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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }

    @GetMapping("/types")
    public String types() {
        return "types";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }


}
