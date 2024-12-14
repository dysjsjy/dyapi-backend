package com.dysjsjy.project.service;

import com.dysjsjy.common.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author dysjs
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-12-13 18:50:33
*/
public interface UserService extends IService<User> {
    long userRegister(String userAccount, String userPassword, String checkPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);
}
