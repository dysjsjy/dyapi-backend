package com.dysjsjy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dysjsjy.common.model.entity.User;
import com.dysjsjy.project.service.UserService;
import com.dysjsjy.project.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author dysjs
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-12-13 18:50:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




