package com.roderland.blog.web;

import com.roderland.blog.po.Blog;
import com.roderland.blog.po.Tag;
import com.roderland.blog.service.BlogService;
import com.roderland.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*
    @author: Roderland
    @create: 2020-09-07---21:43
*/
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model, @PathVariable Long id) {
        List<Tag> tags = tagService.listTag(10000);
        if (id==-1) {
            id=tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(pageable, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }

    @GetMapping("/tags")
    public String tags() {
        return "redirect:/tags/-1";
    }
}
