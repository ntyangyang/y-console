package com.console.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.base.BaseServiceImpl;
import com.console.modules.sys.mapper.SysRolePermissionMapper;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.model.SysRolePermission;
import com.console.modules.sys.model.SysUserRole;
import com.console.modules.sys.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 12:56 2018/9/22
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public void save(SysRole role, List<SysPermission> permissions) {
        List<SysRolePermission> list = new ArrayList<>();
        for (SysPermission permission : permissions) {
            SysRolePermission srp = new SysRolePermission();
            srp.setRoleId(role.getId());
            srp.setPermissionId(permission.getId());
            list.add(srp);
        }
        this.saveBatch(list);
    }

    @Override
    public void update(SysRole role, List<SysPermission> permissions) {
        this.remove(new QueryWrapper<SysRolePermission>().eq("role_id", role.getId()));
        if (CollectionUtil.isNotEmpty(permissions)) {
            this.save(role, permissions);
        }
    }
}
