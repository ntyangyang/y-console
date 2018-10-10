package com.console.modules.sys.service.impl;

import com.console.base.BaseServiceImpl;
import com.console.modules.sys.mapper.SysUserRoleMapper;
import com.console.modules.sys.model.SysUserRole;
import com.console.modules.sys.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:32 2018/9/21
 * @Modified By:
 */
@Slf4j
@Service("sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
