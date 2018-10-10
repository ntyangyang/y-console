package com.console.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:55 2018/9/17
 * @Modified By:
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> queryByUserId(long userId);

    List<SysPermission> selectListByRoleId(Long roleId);
}
