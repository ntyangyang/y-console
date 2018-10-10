package com.console.modules.sys.controller;

import com.console.common.annotation.ConsoleLog;
import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.modules.sys.controller.form.SysLoginForm;
import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.service.SysUserService;
import com.console.modules.sys.service.SysUserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 0:35 2018/9/18
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;


    /**
     * 登录
     */
    @PostMapping("/login")
    @ConsoleLog("后台用户登录")
    public Result login(@RequestBody SysLoginForm form){
        //用户信息
        SysUser user = sysUserService.queryByUsername(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return ResultGenerator.genFailedResult("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == -1) {
            return ResultGenerator.genFailedResult("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        return sysUserTokenService.createToken(user.getId());
    }


    /**
     * 退出
     */
    @PostMapping("/logout")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return ResultGenerator.genOkResult();
    }

}