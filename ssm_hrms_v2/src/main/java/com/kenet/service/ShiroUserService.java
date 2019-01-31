package com.kenet.service;

import com.kenet.bean.User;
import org.springframework.stereotype.Service;

@Service
public class ShiroUserService {

    public User queryUserByName(String username) {
        User user = new User();
        /*
        * query userInfo from database
        * */
        user.setPassword("1234");
        user.setUsername(username);
        user.setSalt("1234567");
        return user;
    }
}
