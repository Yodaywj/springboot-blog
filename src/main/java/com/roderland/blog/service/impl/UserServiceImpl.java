package com.roderland.blog.service.impl;

import com.roderland.blog.dao.UserRepository;
import com.roderland.blog.po.User;
import com.roderland.blog.service.UserService;
import com.roderland.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    @author: Roderland
    @create: 2020-09-04---16:15
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
