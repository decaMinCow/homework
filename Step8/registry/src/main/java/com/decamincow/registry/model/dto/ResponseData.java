package com.decamincow.registry.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData {

    private Boolean success; // 是否成功
    private String error; // 错误信息
    private String code; // 错误代码
    private Object data; // 返回数据
    private String extra; // 扩展字段

}