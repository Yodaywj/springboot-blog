package com.roderland.blog.service;

import com.roderland.blog.po.User;

/*
    @author: Roderland
    @create: 2020-09-04---16:14
*/
public interface UserService {
    User checkUser(String username, String password);
}
