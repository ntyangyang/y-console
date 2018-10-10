package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.modules.sys.model.SysMenu;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 13:18 2018/9/19
 * @Modified By:
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> queryUserMenuList(Long userId);


    List<SysMenu> getAll();

    void batchDelete(List<SysMenu> menuList);
}
