package com.dysjsjy.dubbo.common.service;

import com.dysjsjy.common.model.entity.User;

public interface InnerUserService {
    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
