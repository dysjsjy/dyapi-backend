package com.dysjsjy.project.service;

import com.dysjsjy.common.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dysjs
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-12-13 18:50:33
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    boolean invokeCount(long interfaceInfoId, long userId);
}
