package com.roderland.blog.web;

/*
    @author: Roderland
    @create: 2020-09-04---18:54
*/

import com.roderland.blog.po.Blog;
import com.roderland.blog.service.BlogService;
import com.roderland.blog.service.TagService;
import com.roderland.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blog")
    public String blog(@PageableDefault(size = 2, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin-blog";
    }

    @PostMapping("/blog/search")
    public String search(@PageableDefault(size = 2, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin-blog :: blogList";
    }

    @GetMapping("/blog/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin-input";


    }
}
