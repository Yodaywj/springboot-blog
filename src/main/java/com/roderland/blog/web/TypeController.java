package com.roderland.blog.web;

/*
    @author: Roderland
    @create: 2020-09-04---19:57
*/

import com.roderland.blog.po.Type;
import com.roderland.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 后台类型展示
     *
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin-types";
    }

    /**
     * 后台类型添加
     *
     * @return
     */
    @GetMapping("/types/input")
    public String input() {
        return "type-input";
    }

    /**
     * 后台类型添加提交
     *
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes) {
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 后台类型编辑
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "type-input";
    }

    /**
     * 后台类型编辑提交
     *
     * @param id
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@PathVariable Long id, Type type, RedirectAttributes attributes) {
        Type t = typeService.updateType(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 后台类型删除提交
     *
     * @param id
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
