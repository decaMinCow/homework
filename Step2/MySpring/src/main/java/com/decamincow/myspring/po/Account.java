package com.decamincow.myspring.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Account
 * @Description TODO
 * @Author decamincow
 * @Date 06/03/2020 11:47 AM
 * @Version 1.0
 **/
@Data
public class Account implements Serializable {

    private Integer cardNo;

    private String name;

    private Integer money;

}
