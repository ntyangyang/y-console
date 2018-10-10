package com.console.modules.sys.service;

import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.model.SysUserToken;

import java.util.Set;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:34 2018/9/17
 * @Modified By:
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> queryUserPermissions(long userId);

    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(Long userId);
}
