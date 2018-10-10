package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.common.utils.PageUtils;
import com.console.modules.sys.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 0:55 2018/9/18
 * @Modified By:
 */
public interface SysUserService extends BaseService<SysUser> {
    PageUtils queryPageList(Map<String, Object> params);

    SysUser queryByUsername(String username);

    void deleteByIds(List<Long> ids);

    void saveUserRoles(Long userId, List<Long> roleIds);

    void updateUserRoles(Long id, List<Long> roleIds);
}
