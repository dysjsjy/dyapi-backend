package com.dysjsjy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dysjsjy.common.model.entity.Post;
import com.dysjsjy.project.service.PostService;
import com.dysjsjy.project.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author dysjs
* @description 针对表【post(用户帖子表)】的数据库操作Service实现
* @createDate 2024-12-13 21:36:54
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

}




