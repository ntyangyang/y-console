package com.console.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:11 2018/9/15
 * @Modified By:
 */
@Data
@NoArgsConstructor
public class ConsoleException extends RuntimeException {
    private static final long serialVersionUID = -3929372771254388196L;
    private String msg;
    private int code = 500;

    public ConsoleException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ConsoleException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ConsoleException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ConsoleException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
