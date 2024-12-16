package com.dysjsjy.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dysjsjy.common.model.entity.Post;
import com.dysjsjy.common.model.entity.User;
import com.dysjsjy.project.annotation.AuthCheck;
import com.dysjsjy.project.common.BaseResponse;
import com.dysjsjy.project.common.DeleteRequest;
import com.dysjsjy.project.common.ErrorCode;
import com.dysjsjy.project.common.ResultUtils;
import com.dysjsjy.project.exception.BusinessException;
import com.dysjsjy.project.model.dto.post.PostAddRequest;
import com.dysjsjy.project.model.dto.post.PostQueryRequest;
import com.dysjsjy.project.model.dto.post.PostUpdateRequest;
import com.dysjsjy.project.service.PostService;
import com.dysjsjy.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;


    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest, HttpServletRequest request) {
        if (postAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postAddRequest, post);
        //校验
        postService.validPost(post, true);
        User loginUser = userService.getLoginUser(request);
        post.setUserId(loginUser.getId());
        boolean result = postService.save(post);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newPostId = post.getId();
        return ResultUtils.success(newPostId);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        //判断是否存在
        Post oldPost = postService.getById(id);
        if (oldPost == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //仅本人或管理员可以删除
        if (!oldPost.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = postService.removeById(id);
        return ResultUtils.success(b);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updatePost(@RequestBody PostUpdateRequest postUpdateRequest, HttpServletRequest request) {
        if (postUpdateRequest == null || postUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postUpdateRequest, post);
        //参数校验
        postService.validPost(post, false);
        User loginUser = userService.getLoginUser(request);
        long id = postUpdateRequest.getId();
        //判断是否存在
        Post oldPost = postService.getById(id);
        if (oldPost == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //仅本人或者管理员可以修改
        if (!oldPost.getUserId().equals(loginUser.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }

    @GetMapping("/get")
    public BaseResponse<Post> getPostById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = postService.getById(id);
        return ResultUtils.success(post);
    }

    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<Post>> listPost(PostQueryRequest postQueryRequest, HttpServletRequest request) {
        Post postQuery = new Post();
        if (postQueryRequest != null) {
            BeanUtils.copyProperties(postQueryRequest, postQuery);
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>(postQuery);
        List<Post> postList = postService.list(queryWrapper);
        return ResultUtils.success(postList);
    }

}
