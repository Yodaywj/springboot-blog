package com.roderland.blog.web;

import com.roderland.blog.po.Blog;
import com.roderland.blog.po.Type;
import com.roderland.blog.service.BlogService;
import com.roderland.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 类型博客文章展示
     *
     * @param pageable
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model, @PathVariable Long id) {
        List<Type> types = typeService.listType(10000);
        if (types.size() > 0 && id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        Blog blog = new Blog();
        if (typeService.getType(id) != null) blog.setType(typeService.getType(id));
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("activeTypeId", id);
        return "types";
    }

    /**
     * 类型展示
     *
     * @return
     */
    @GetMapping("/types")
    public String types() {
        return "redirect:/types/-1";
    }
}
