package com.dysjsjy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dysjsjy.common.model.entity.UserInterfaceInfo;
import com.dysjsjy.project.common.ErrorCode;
import com.dysjsjy.project.exception.BusinessException;
import com.dysjsjy.project.service.UserInterfaceInfoService;
import com.dysjsjy.project.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author dysjs
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-12-13 18:50:33
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //创建时所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }


}




