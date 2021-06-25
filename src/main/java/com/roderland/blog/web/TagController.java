package com.roderland.blog.web;

import com.roderland.blog.po.Tag;
import com.roderland.blog.service.TagService;
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

/*
    @author: Roderland
    @create: 2020-09-06---8:01
*/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 后台标签展示：标签列表
     *
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin-tags";
    }

    /**
     * 后台标签添加
     *
     * @return
     */
    @GetMapping("/tags/input")
    public String input() {
        return "tag-input";
    }

    /**
     * 后台标签添加提交
     *
     * @param tag
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes) {
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 后台标签编辑
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "tag-input";
    }

    /**
     * 后天标签编辑提交
     *
     * @param id
     * @param tag
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@PathVariable Long id, Tag tag, RedirectAttributes attributes) {
        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 后台标签删除提交
     *
     * @param id
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }
}
