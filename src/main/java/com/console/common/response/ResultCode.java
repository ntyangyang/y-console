package com.console.common.response;

/**
 * @Author: yang
 * @Description: 响应状态码枚举类
 * 自定义 exception 2*** 开始
 * 原有异常 4*** 开始
 * @Date:Create In 2:07 2018/8/6
 * @Modified By:
 */
public enum ResultCode {
    /**
     * {@code 1000 成功请求，但是结果不是期望的成功结果}
     */
    SUCCEED_REQUEST_FAILED_RESULT(1000, "Successfully request, but result not expired"),

    /**
     * {@code 2000 服务异常}
     */
    SERVICE_EXCEPTION(2000, "Service exception"),

    /**
     * {@code 2001 资源未找到}
     */
    RESOURCES_NOT_FOUND_EXCEPTION(2001, "Resources not found exception"),

    /**
     * {@code 4000 token权限校验异常}
     */
    AUTHORIZED_EXCEPTION(4000, "authorized exception"),
    /**
     * {@code 4001 数据库异常}
     */
    DATABASE_EXCEPTION(4001, "Database exception"),

    /**
     * {@code 4002 需要认证}
     */
    UNAUTHORIZED(4002, "Need authorized"),

    /**
     * {@code 4003 验证异常，比如注册的邮箱格式不正确}
     */
    VIOLATION_EXCEPTION(4003, "Violation exception"),

    /**
     * {@code 4100 修改密码异常，旧密码不正确}
     */
    PASSWORD_EXCEPTION(4100, "Password exception");

    private final int value;

    private final String reason;

    ResultCode(final int value, final String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int getValue() {
        return this.value;
    }

    public String getReason() {
        return this.reason;
    }
}
