package com.console.modules.sys.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.validator.ValidatorUtils;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.console.modules.sys.model.SysMenu;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.service.SysMenuRoleService;
import com.console.modules.sys.service.SysMenuService;
import com.console.modules.sys.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 18:07 2018/9/18
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/nav")
    public Result getMenuNav() {
        List<SysMenu> menuTree = sysMenuService.queryUserMenuList(getUserId());
        return ResultGenerator.genOkResult(menuTree);
    }

    @GetMapping("all")
    public Result getAll() {
        List<SysMenu> list = sysMenuService.getAll();
        return ResultGenerator.genOkResult(list);
    }

    @GetMapping("roles")
    public Result getRoles(@RequestParam("id") Long id) {
        List<SysRole> roleList = sysRoleService.queryByMenuId(id);
        return ResultGenerator.genOkResult(roleList);
    }

    @PostMapping("saveOrUpdate")
    @RequiresPermissions(value = {"menu:create", "menu:update"}, logical = Logical.OR)
    public Result saveOrUpdate(@RequestBody SysMenu sysMenu) {
        if (sysMenu.getId() == null) {
            ValidatorUtils.validateEntity(sysMenu, AddGroup.class);
            sysMenu.setCreateBy(getUserId());
            sysMenu.setCreateTime(new Date());
            sysMenuService.save(sysMenu);

            List<Long> roleIds = sysMenu.getRoleIds();
            if (CollectionUtil.isNotEmpty(roleIds)) {
                sysMenuRoleService.save(sysMenu, roleIds);
            }
        } else {
            ValidatorUtils.validateEntity(sysMenu, UpdateGroup.class);
            sysMenu.setUpdateBy(getUserId());
            sysMenu.setUpdateTime(new Date());
            sysMenuService.updateById(sysMenu);

            List<Long> roleIds = sysMenu.getRoleIds();
            sysMenuRoleService.update(sysMenu, roleIds);
        }
        return ResultGenerator.genOkResult();
    }

    @PostMapping("batchDelete")
    public Result batchDelete(@RequestBody List<SysMenu> menuList) {
        sysMenuService.batchDelete(menuList);
        return ResultGenerator.genOkResult();
    }

}
