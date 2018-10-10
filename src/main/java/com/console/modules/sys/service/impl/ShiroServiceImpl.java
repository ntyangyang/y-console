package com.console.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.common.constant.CommonConstant;
import com.console.modules.sys.mapper.SysPermissionMapper;
import com.console.modules.sys.mapper.SysRoleMapper;
import com.console.modules.sys.mapper.SysUserMapper;
import com.console.modules.sys.mapper.SysUserTokenMapper;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.model.SysUserToken;
import com.console.modules.sys.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:35 2018/9/17
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> queryUserPermissions(long userId) {
        Set<String> permsSet = new HashSet<>();

        //系统管理员，拥有最高权限
        if (userId == CommonConstant.USER_TYPE_ADMIN) {
            List<SysRole> roleList = sysRoleMapper.selectList(null);
            for (SysRole sysRole : roleList) {
                permsSet.add(sysRole.getName());
            }
            List<SysPermission> permissionList = sysPermissionMapper.selectList(new QueryWrapper<SysPermission>().eq("is_group",false));
            for (SysPermission sysPermission : permissionList) {
                permsSet.add(sysPermission.getName());
            }
        } else {
            List<SysRole> roleList = sysRoleMapper.queryByUserId(userId);
            for (SysRole sysRole : roleList) {
                permsSet.add(sysRole.getName());
            }
            List<SysPermission> permissionList = sysPermissionMapper.queryByUserId(userId);
            for (SysPermission sysPermission : permissionList) {
                permsSet.add(sysPermission.getName());
            }
        }
        return permsSet;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenMapper.queryByToken(token);
    }

    @Override
    public SysUser queryUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
