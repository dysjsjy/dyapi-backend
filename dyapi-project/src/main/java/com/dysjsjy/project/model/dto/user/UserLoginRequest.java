package com.dysjsjy.project.model.dto.user;

import lombok.Data;

@Data
public class UserLoginRequest {

    private static final long serialVersionUID = 1L;

    private String userAccount;

    private String userPassword;
}
