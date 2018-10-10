package com.console.modules.job.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:18 2018/9/18
 * @Modified By:
 */
@Data
@TableName("sys_job")
public class SysJob extends BaseModel {
    private static final long serialVersionUID = 260622581790499645L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @ApiModelProperty(value = "bean名称")
    @NotBlank(message = "bean名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String beanName;

    @ApiModelProperty(value = "方法名称")
    @NotBlank(message = "方法名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String methodName;

    @ApiModelProperty(value = "任务参数")
    private String params;

    @ApiModelProperty(value = "cron表达式")
    @NotBlank(message = "cron表达式不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String cronExpression;

    @ApiModelProperty(value = "任务状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
