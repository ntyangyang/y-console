package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 3:50 2018/9/18
 * @Modified By:
 */
@Data
@TableName("sys_log")
public class SysLog extends BaseModel {
    private static final long serialVersionUID = 8313998026157841932L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户操作")
    private String operation;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "执行时长(毫秒)")
    private Long time;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "ip信息")
    private String ipInfo;

}
