package com.console.common.exception;

import com.console.common.response.Result;
import com.console.common.response.ResultCode;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:17 2018/9/15
 * @Modified By:
 */
@Slf4j
@RestControllerAdvice
public class ConsoleExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result validatorException(final ConstraintViolationException e) {
        final String msg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        log.error("==> 验证实体异常: {}", e.toString());
        return ResultGenerator.genFailedResult(ResultCode.VIOLATION_EXCEPTION.getValue(), msg);
    }

    @ExceptionHandler(ConsoleException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result serviceException(final ConsoleException e) {
        String errorMsg = "YConsole exception";
        if (e != null) {
            errorMsg = e.getMsg();
            log.error("==> 服务异常: {}", e.getMessage());
        }
        return ResultGenerator.genFailedResult(ResultCode.SERVICE_EXCEPTION.getValue(), errorMsg);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultGenerator.genFailedResult(ResultCode.RESOURCES_NOT_FOUND_EXCEPTION.getValue(), "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return ResultGenerator.genFailedResult(ResultCode.UNAUTHORIZED.getValue(),"没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result globalException(final HttpServletRequest request, final Exception e) {
        String errorMsg = "Global Exception";
        if (e != null) {
            errorMsg = e.getMessage();
            log.error("==> 全局异常: {}", errorMsg);
        }
        return ResultGenerator.genFailedResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("%s => %s", UrlUtils.getMappingUrl(request), errorMsg));
    }

}
