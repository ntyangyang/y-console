package com.console.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:33 2018/9/17
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
