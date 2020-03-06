package com.decamincow.myspring.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyAutowired
 * @Description TODO
 * @Author decamincow
 * @Date 06/03/2020 1:18 PM
 * @Version 1.0
 **/
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    boolean required() default true;
}