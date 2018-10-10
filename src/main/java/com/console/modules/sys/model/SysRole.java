package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 21:58 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_role")
public class SysRole extends BaseModel {
    private static final long serialVersionUID = 7204172435333963146L;

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    @NotBlank(message = "角色名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @ApiModelProperty(value = "是否为注册默认角色")
    private Boolean defaultRole;

    @ApiModelProperty(value = "备注")
    private String description;

    @TableField(exist = false)
    @ApiModelProperty(value = "拥有权限")
    private List<SysPermission> permissions;
}
