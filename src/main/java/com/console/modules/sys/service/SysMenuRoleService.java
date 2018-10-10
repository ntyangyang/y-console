package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.modules.sys.model.SysMenu;
import com.console.modules.sys.model.SysMenuRole;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 16:37 2018/9/25
 * @Modified By:
 */
public interface SysMenuRoleService extends BaseService<SysMenuRole> {
    void save(SysMenu sysMenu, List<Long> roleIds);

    void update(SysMenu sysMenu, List<Long> roleIds);
}
