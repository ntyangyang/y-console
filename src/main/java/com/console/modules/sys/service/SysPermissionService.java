package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 8:39 2018/9/22
 * @Modified By:
 */
public interface SysPermissionService extends BaseService<SysPermission> {
    List<SysPermission> getAll();

    void batchDelete(List<SysPermission> permList);

    List<SysPermission> getAllByRole(Long roleId);

    void update(SysPermission sysPermission);
}
