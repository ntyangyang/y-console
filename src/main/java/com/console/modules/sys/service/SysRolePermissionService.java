package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.model.SysRolePermission;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 12:56 2018/9/22
 * @Modified By:
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {
    void save(SysRole role, List<SysPermission> permissions);

    void update(SysRole role, List<SysPermission> permissions);
}
