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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 后台首页：博客文章列表 + 博客类型列表
     *
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blog")
    public String blog(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Blog blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin-blog";
    }

    /**
     * 后台博客文章查询：博客文章列表 + 博客类型列表
     *
     * @param pageable
     * @param blog     查询条件封装：博客标题 + 博客类型
     * @param model
     * @return
     */
    @PostMapping("/blog/search")
    public String search(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Blog blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin-blog :: blogList";
    }

    /**
     * 后台写新文章
     *
     * @param model
     * @return
     */
    @GetMapping("/blog/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin-input";
    }

    /**
     * 后台发布文章
     *
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blog")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTagList(tagService.listTag(blog.getTagIds()));
        if (blog.getId() == null) {
            //创建
            blog.setCreateTime(new Date());
            blog.setViews(0);
        } else {
            //修改
            blog.setCreateTime(blogService.getBlog(blog.getId()).getCreateTime());
            blog.setViews(blogService.getBlog(blog.getId()).getViews());
        }
        blog.setUpdateTime(new Date());
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blog";
    }

    /**
     * 后台编辑文章
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin-input";
    }

    /**
     * 后台删除文章
     *
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blog";
    }
}
