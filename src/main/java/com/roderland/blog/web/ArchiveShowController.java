package com.roderland.blog.web;

import com.roderland.blog.po.Blog;
import com.roderland.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
    @author: Roderland
    @create: 2020-09-08---9:05
*/
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archivesBlog());
        model.addAttribute("blogCount", blogService.blogCount());
        return "archives";
    }
}
