package com.console.modules.sys.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.common.response.Result;
import com.console.common.response.ResultCode;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.PageUtils;
import com.console.common.validator.ValidatorUtils;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.console.modules.sys.controller.form.SysPasswordForm;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.service.SysRoleService;
import com.console.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 16:15 2018/9/18
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/info")
    public Result userInfo() {
        SysUser user = super.getUser();
        List<SysRole> roles = new ArrayList<>();
        if (user.getId().equals(1L)) {
            SysRole role = new SysRole();
            role.setName("superadmin");
            role.setDescription("超级管理员");
            roles.add(role);
            user.setRoles(roles);
        } else {
            roles = sysRoleService.queryByUserId(user.getId());
        }
        user.setRoles(roles);
        user.setSalt(null);
        user.setPassword(null);
        return ResultGenerator.genOkResult(user);
    }

    @GetMapping("validateName")
    public Result validateName(@RequestParam("username") String username, @RequestParam("id") Long id) {
        if (id == null) {
            SysUser sysUser = sysUserService.queryByUsername(username);
            return ResultGenerator.genOkResult(sysUser == null ? true : false);
        } else {
            SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().ne("id", id).eq("username", username));
            return ResultGenerator.genOkResult(sysUser == null ? true : false);
        }
    }

    @GetMapping("roles")
    public Result getRoles(@RequestParam("id") Long id) {
        List<SysRole> roleList = sysRoleService.queryByUserId(id);
        return ResultGenerator.genOkResult(roleList);
    }

    @GetMapping("/list")
    public Result search(@RequestParam Map<String, Object> params) {
        PageUtils userPage = sysUserService.queryPageList(params);
        return ResultGenerator.genOkResult(userPage);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> ids) {
        if (CollectionUtil.isNotEmpty(ids) && ids.stream().anyMatch(i -> i.equals(1L))) {
            return ResultGenerator.genFailedResult("超级管理员不能删除！");
        }
        sysUserService.deleteByIds(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysUser user) {
        List<Long> roleIds = user.getRoleIds();
        if (user.getId() == null) {
            ValidatorUtils.validateEntity(user, AddGroup.class);
            user.setCreateBy(getUserId());
            user.setCreateTime(new Date());
            user.setSalt(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
            sysUserService.save(user);

            if (CollectionUtil.isNotEmpty(roleIds)) {
                sysUserService.saveUserRoles(user.getId(), roleIds);
            } else {
                SysRole default_role = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("default_role", true));
                if (default_role != null) {
                    roleIds.add(default_role.getId());
                    sysUserService.saveUserRoles(user.getId(), roleIds);
                }
            }
        } else {
            ValidatorUtils.validateEntity(user, UpdateGroup.class);
            user.setUpdateBy(getUserId());
            user.setUpdateTime(new Date());

            if (StringUtils.isNotEmpty(user.getPassword())) {
                SysUser oldUser = sysUserService.getById(user.getId());
                user.setPassword(new Sha256Hash(user.getPassword(), oldUser.getSalt()).toHex());
            }

            sysUserService.updateById(user);
            sysUserService.updateUserRoles(user.getId(), roleIds);
        }
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/acountUpdate")
    public Result acountUpdate(@RequestBody SysUser user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setUpdateBy(getUserId());
        user.setUpdateTime(new Date());
        user.setStatus(null);
        user.setType(null);
        user.setNickName(null);

        sysUserService.updateById(user);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/passwordUpdate")
    public Result passwordUpdate(@RequestBody SysPasswordForm passwordForm) {
        ValidatorUtils.validateEntity(passwordForm);

        Long id = passwordForm.getId();
        if (id == null) {
            id = getUserId();
        }
        SysUser user = sysUserService.getById(id);

        String oldPassword = new Sha256Hash(passwordForm.getPassword(), user.getSalt()).toHex();
        if (!user.getPassword().equals(oldPassword)) {
            return ResultGenerator.genFailedResult(ResultCode.PASSWORD_EXCEPTION);
        }

        user.setPassword(new Sha256Hash(passwordForm.getNewPassword(), user.getSalt()).toHex());
        user.setUpdateBy(getUserId());
        user.setUpdateTime(new Date());

        sysUserService.updateById(user);
        return ResultGenerator.genOkResult();
    }

}
