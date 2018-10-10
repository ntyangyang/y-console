package com.console.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.console.base.BaseModel;
import com.console.common.constant.CommonConstant;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @Author: yang
 * @Description:
 * @Date:Create In 21:55 2018/9/17
 * @Modified By:
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseModel {
    private static final long serialVersionUID = 4926564820104210978L;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "邮件")
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "0男 1女 2保密")
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    private String avatar = CommonConstant.USER_DEFAULT_AVATAR;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
    private Integer type = CommonConstant.USER_TYPE_NORMAL;

    @ApiModelProperty(value = "状态 默认0正常 -1禁用")
    private Integer status = CommonConstant.USER_STATUS_NORMAL;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @ApiModelProperty(value = "所属部门id")
    private Long departmentId;

    @TableField(exist = false)
    @ApiModelProperty(value = "所属部门名称")
    private String departmentTitle;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户拥有角色")
    private List<SysRole> roles;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户拥有角色ids 前端用")
    private List<Long> roleIds;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户拥有的权限")
    private List<SysPermission> permissions;
}
