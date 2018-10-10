package com.console.modules.sys.model;

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
 * @Date:Create In 22:37 2018/9/25
 * @Modified By:
 */
@Data
@TableName("sys_config")
public class SysConfig extends BaseModel {

    private static final long serialVersionUID = 2585828889780254883L;

    @ApiModelProperty(value = "参数名")
    @NotBlank(message = "参数名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String paramKey;

    @ApiModelProperty(value = "参数值")
    private String paramValue;

    @ApiModelProperty(value = "参数名")
    private String remark;

}
