package com.console.common.response;

import org.springframework.http.HttpStatus;

/**
 * @Author: yang
 * @Description: 响应结果生成工具
 * @Date:Create In 2:08 2018/8/6
 * @Modified By:
 */
public class ResultGenerator {
    /**
     * 成功响应结果
     *
     * @param data 内容
     * @return 响应结果
     */
    public static Result genOkResult(final Object data) {
        return new Result()
                .setSuccess(true)
                .setCode(HttpStatus.OK.value())
                .setData(data);
    }

    /**
     * 成功响应结果
     *
     * @return 响应结果
     */
    public static Result genOkResult() {
        return genOkResult(null);
    }

    /**
     * 失败响应结果
     *
     * @param code    状态码
     * @param message 消息
     * @return 响应结果
     */
    public static Result genFailedResult(final int code, final String message) {
        return new Result()
                .setSuccess(false)
                .setCode(code)
                .setMessage(message);
    }

    /**
     * 失败响应结果
     *
     * @param message 消息
     * @return 响应结果
     */
    public static Result genFailedResult(final String message) {
        return genFailedResult(ResultCode.SUCCEED_REQUEST_FAILED_RESULT.getValue(), message);
    }

    /**
     * 失败响应结果
     *
     * @param resultCode 状态码枚举
     * @return 响应结果
     */
    public static Result genFailedResult(final ResultCode resultCode) {
        return genFailedResult(resultCode.getValue(), resultCode.getReason());
    }

    /**
     * 失败响应结果
     *
     * @return 响应结果
     */
    public static Result genFailedResult() {
        return genFailedResult(ResultCode.SUCCEED_REQUEST_FAILED_RESULT);
    }
}
