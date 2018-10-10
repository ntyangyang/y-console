package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import com.console.common.constant.CommonConstant;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:01 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_menu")
public class SysMenu extends BaseModel {
    private static final long serialVersionUID = 2502859449116369246L;

    @ApiModelProperty(value = "菜单/权限名称")
    @NotBlank(message = "菜单/权限名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @ApiModelProperty(value = "类型 0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty(value = "页面路径/资源链接url")
    private String path;

    @ApiModelProperty(value = "前端组件")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "父id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty(value = "说明备注")
    private String description;

    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    private Integer status = CommonConstant.STATUS_NORMAL;

    @ApiModelProperty(value = "网页链接")
    private String url;

    @TableField(exist = false)
    @ApiModelProperty(value = "子菜单")
    private List<SysMenu> children;

    @TableField(exist = false)
    @ApiModelProperty(value = "可访问角色id")
    private List<Long> roleIds;

    @TableField(exist = false)
    @ApiModelProperty(value = "节点展开 前端所需")
    private Boolean expand = true;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否勾选 前端所需")
    private Boolean open = false;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否选中 前端所需")
    private Boolean selected = false;
}
