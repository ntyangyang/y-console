package com.console.modules.sys.controller.form;

import com.baomidou.mybatisplus.annotation.TableId;
import com.console.common.validator.group.AddGroup;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 21:12 2018/10/3
 * @Modified By:
 */
@Data
public class SysPasswordForm {

    @ApiModelProperty(value = "修改用户id")
    private Long id;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message="新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "重复新密码")
    @NotBlank(message="重复新密码不能为空")
    private String reNewPassword;
}
