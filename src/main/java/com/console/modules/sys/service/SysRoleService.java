package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.common.utils.PageUtils;
import com.console.modules.sys.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 21:51 2018/9/20
 * @Modified By:
 */
public interface SysRoleService extends BaseService<SysRole> {
    List<SysRole> queryByUserId(Long userId);

    PageUtils queryPageList(Map<String, Object> params);

    void deleteByIds(List<Long> ids);

    List<SysRole> queryByMenuId(Long id);
}
