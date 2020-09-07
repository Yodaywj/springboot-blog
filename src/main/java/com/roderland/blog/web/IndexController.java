package com.roderland.blog.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
    @author: Roderland
    @create: 2020-08-30---18:02
*/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listType(6));
        model.addAttribute("tags", tagService.listTag(10));
        model.addAttribute("recommends", blogService.listBlog(8));
        return "index";
    }

    @GetMapping("/search")
    public String search() {
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model,
                         @RequestParam String query) {
        model.addAttribute("page", blogService.listBlog(pageable, "%" + query + "%"));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        model.addAttribute("types", typeService.listType(6));
        model.addAttribute("tags", tagService.listTag(10));
        model.addAttribute("recommends", blogService.listBlog(8));
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
