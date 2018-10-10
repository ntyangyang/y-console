package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:15 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermission extends BaseModel {
    private static final long serialVersionUID = 4774208748454419489L;
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "权限id")
    private Long permissionId;
}
