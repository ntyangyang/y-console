package com.console.common.response;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 2:07 2018/8/6
 * @Modified By:
 */
@ApiModel(value = "响应结果")
public class Result {
     @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private Object data;

    @ApiModelProperty(value = "成功/失败")
    private  Boolean success;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Result setCode(final Integer code) {
        this.code = code;
        return this;
    }

    public Result setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Result setData(final Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Result setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
}
