package com.dysjsjy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dysjsjy.common.model.entity.UserInterfaceInfo;
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

}




