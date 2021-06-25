package com.roderland.blog.web;

import com.roderland.blog.po.User;
import com.roderland.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/*
    @author: Roderland
    @create: 2020-09-04---16:23
*/
@Controller
@RequestMapping("/admin")
public class loginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    /**
     * 后台登录
     *
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:/admin/blog";
        } else {
            attributes.addFlashAttribute("message", "用户不存在或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
