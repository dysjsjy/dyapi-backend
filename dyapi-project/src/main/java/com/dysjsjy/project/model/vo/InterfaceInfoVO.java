package com.dysjsjy.project.model.vo;


import com.dysjsjy.common.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceInfoVO extends InterfaceInfo {

    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}
