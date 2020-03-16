package com.decamincow.sss.model;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName AdminUser
 * @Description TODO
 * @Author decamincow
 * @Date 16/03/2020 5:52 PM
 * @Version 1.0
 **/
@Data
@Builder
public class AdminUser {

    private String userName;

    private String password;
}
