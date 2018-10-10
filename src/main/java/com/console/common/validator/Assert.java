package com.console.common.validator;

import com.console.common.exception.ConsoleException;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:32 2018/9/17
 * @Modified By:
 */
public class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ConsoleException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ConsoleException(message);
        }
    }
}
