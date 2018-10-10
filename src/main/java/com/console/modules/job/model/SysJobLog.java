package com.console.modules.job.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:22 2018/9/18
 * @Modified By:
 */
@Data
@TableName("sys_job_log")
public class SysJobLog extends BaseModel {
    private static final long serialVersionUID = -331320674873470341L;

    @ApiModelProperty(value = "任务id")
    private Long jobId;

    @ApiModelProperty(value = "bean名称")
    private String beanName;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "任务参数")
    private String params;

    @ApiModelProperty(value = "任务状态  0：成功  1：失败")
    private Integer status;

    @ApiModelProperty(value = "失败信息")
    private String error;

    @ApiModelProperty(value = "耗时(单位：毫秒)")
    private Integer times;

}
