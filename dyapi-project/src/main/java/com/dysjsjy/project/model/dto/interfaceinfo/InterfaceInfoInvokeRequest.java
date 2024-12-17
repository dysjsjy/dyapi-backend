package com.dysjsjy.project.model.dto.interfaceinfo;

import lombok.Data;

@Data
public class InterfaceInfoInvokeRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}
