package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:19 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_menu_role")
public class SysMenuRole extends BaseModel {
    private static final long serialVersionUID = 8980839765011041251L;
    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;
}
