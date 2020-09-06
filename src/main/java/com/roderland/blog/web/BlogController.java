package com.roderland.blog.web;

/*
    @author: Roderland
    @create: 2020-09-04---18:54
*/

import com.roderland.blog.po.Blog;
import com.roderland.blog.po.User;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/blog")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTagList(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b==null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blog";
    }
}
