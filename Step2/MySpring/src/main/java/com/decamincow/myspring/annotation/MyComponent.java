package com.decamincow.myspring.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyComponent
 * @Description TODO
 * @Author decamincow
 * @Date 06/03/2020 4:22 PM
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyComponent {
    String value() default "";
}