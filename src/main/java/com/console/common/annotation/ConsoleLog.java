package com.console.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 3:52 2018/9/18
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsoleLog {

	String value() default "";
}

