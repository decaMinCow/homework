package com.decamincow.myspring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName MyService
 * @Description TODO
 * @Author decamincow
 * @Date 06/03/2020 1:16 PM
 * @Version 1.0
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyService {
    String value() default "";
}