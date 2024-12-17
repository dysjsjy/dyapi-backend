package com.dysjsjy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dysjsjy.common.model.entity.Post;
import com.dysjsjy.common.model.enums.PostGenderEnum;
import com.dysjsjy.common.model.enums.PostReviewStatusEnum;
import com.dysjsjy.project.common.ErrorCode;
import com.dysjsjy.project.exception.BusinessException;
import com.dysjsjy.project.service.PostService;
import com.dysjsjy.project.mapper.PostMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author dysjs
* @description 针对表【post(用户帖子表)】的数据库操作Service实现
* @createDate 2024-12-13 21:36:54
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Override
    public void validPost(Post post, boolean add) {
        if (post == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer age = post.getAge();
        Integer gender = post.getGender();
        String content = post.getContent();
        String job = post.getJob();
        String place = post.getPlace();
        String education = post.getEducation();
        String loveExp = post.getLoveExp();
        Integer reviewStatus = post.getReviewStatus();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(content, job, place, education, loveExp) || ObjectUtils.anyNull(age, gender)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (reviewStatus != null && !PostReviewStatusEnum.getValues().contains(reviewStatus)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (age != null && (age < 18 || age > 100)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "年龄不符合要求");
        }
        if (gender != null && !PostGenderEnum.getValues().contains(gender)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "性别不符合要求");
        }
    }
}

