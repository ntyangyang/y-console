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
 * @Date:Create In 23:11 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_permission")
public class SysPermission extends BaseModel {
    private static final long serialVersionUID = 851674882870815892L;
    @ApiModelProperty(value = "权限名")
    @NotBlank(message = "角色名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @ApiModelProperty(value = "权限组名")
    private String groupName;

    @ApiModelProperty(value = "权限组名")
    private Boolean isGroup;

    @ApiModelProperty(value = "备注")
    private String description;

    @TableField(exist = false)
    @ApiModelProperty(value = "权限组下的权限")
    private List<SysPermission> children;

}
