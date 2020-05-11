package com.decamincow.dubbo.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName MyTime
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/11 7:41 PM
 * @Version 1.0
 **/
@Builder
@Data
public class MyTime {
    private Long responseTime;
    private Long recordTime;
}
