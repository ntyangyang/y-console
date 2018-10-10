package com.console.modules.sys.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.PageUtils;
import com.console.common.validator.ValidatorUtils;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.service.SysRolePermissionService;
import com.console.modules.sys.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 17:23 2018/9/21
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @GetMapping("all")
    public Result getAll() {
        List<SysRole> list = sysRoleService.list(null);
        return ResultGenerator.genOkResult(list);
    }

    @GetMapping("/list")
    public Result search(@RequestParam Map<String, Object> params) {
        PageUtils userPage = sysRoleService.queryPageList(params);
        return ResultGenerator.genOkResult(userPage);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> ids) {
        sysRoleService.deleteByIds(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysRole role) {
        if (role.getDefaultRole()) {
            SysRole old = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("default_role", true));
            if (old != null) {
                old.setDefaultRole(false);
                sysRoleService.updateById(old);
            }
        }

        if (role.getId() == null) {
            ValidatorUtils.validateEntity(role, AddGroup.class);
            role.setCreateBy(getUserId());
            role.setCreateTime(new Date());
            sysRoleService.save(role);
            List<SysPermission> permissions = role.getPermissions();
            if (CollectionUtil.isNotEmpty(permissions)) {
                sysRolePermissionService.save(role, permissions);
            }
        } else {
            ValidatorUtils.validateEntity(role, UpdateGroup.class);
            role.setUpdateBy(getUserId());
            role.setUpdateTime(new Date());
            sysRoleService.updateById(role);
            sysRolePermissionService.update(role, role.getPermissions());
        }
        return ResultGenerator.genOkResult();
    }

}
