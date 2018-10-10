package com.console.modules.sys.controller;

import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.validator.ValidatorUtils;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 8:38 2018/9/22
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/permission")
public class SysPermissionController extends AbstractController {
    @Autowired
    private SysPermissionService sysPermissionService;

    @GetMapping("all")
    public Result getAll() {
        List<SysPermission> list = sysPermissionService.getAll();
        return ResultGenerator.genOkResult(list);
    }

    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysPermission sysPermission) {
        if (!sysPermission.getIsGroup()) {
            ValidatorUtils.validateEntity(sysPermission, AddGroup.class);
        }
        if (sysPermission.getId() == null) {
            sysPermission.setCreateBy(getUserId());
            sysPermission.setCreateTime(new Date());
            sysPermissionService.save(sysPermission);
        } else {
            sysPermission.setUpdateBy(getUserId());
            sysPermission.setUpdateTime(new Date());
            sysPermissionService.update(sysPermission);
        }
        return ResultGenerator.genOkResult();
    }

    @PostMapping("batchDelete")
    public Result batchDelete(@RequestBody List<SysPermission> permList) {
        sysPermissionService.batchDelete(permList);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/allByRole")
    public Result allByRole(@RequestParam("roleId") Long roleId) {
        List<SysPermission> list = sysPermissionService.getAllByRole(roleId);
        List<String> ids = list.stream().map(i -> i.getId().toString()).collect(Collectors.toList());
        return ResultGenerator.genOkResult(ids);
    }

}
