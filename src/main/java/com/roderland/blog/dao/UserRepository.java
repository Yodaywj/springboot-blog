package com.roderland.blog.dao;

import com.roderland.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author: Roderland
    @create: 2020-09-04---16:16
*/
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPassword(String username, String password);
}
