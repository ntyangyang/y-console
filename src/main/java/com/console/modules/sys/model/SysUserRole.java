package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:17 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_user_role")
public class SysUserRole extends BaseModel {
    private static final long serialVersionUID = -9151512171272127207L;
    @ApiModelProperty(value = "用户唯一id")
    private Long userId;

    @ApiModelProperty(value = "角色唯一id")
    private Long roleId;

    @TableField(exist=false)
    @ApiModelProperty(value = "角色名")
    private String roleName;
}
