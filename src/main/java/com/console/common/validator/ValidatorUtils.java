package com.console.common.validator;

import com.console.common.exception.ConsoleException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:31 2018/9/17
 * @Modified By:
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws ConsoleException  校验不通过，ConsoleException
     */
    public static void validateEntity(Object object, Class<?>... groups) throws ConsoleException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new ConsoleException(msg.toString());
        }
    }
}
