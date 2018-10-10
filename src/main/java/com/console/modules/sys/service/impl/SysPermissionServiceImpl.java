package com.console.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.base.BaseServiceImpl;
import com.console.modules.sys.mapper.SysPermissionMapper;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRolePermission;
import com.console.modules.sys.service.SysPermissionService;
import com.console.modules.sys.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 8:40 2018/9/22
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysPermission> getAll() {
        List<SysPermission> permissionList = this.baseMapper.selectList(new QueryWrapper<SysPermission>().orderByAsc("create_time"));
        List<SysPermission> treeList = new ArrayList<>();
        List<SysPermission> group = permissionList.stream().filter(i -> i.getIsGroup() == true).collect(Collectors.toList());
        treeList.addAll(group);
        permissionList.removeAll(group);
        for (SysPermission sysPermission : group) {
            List<SysPermission> children = permissionList.stream().filter(i -> sysPermission.getGroupName().equals(i.getGroupName())).collect(Collectors.toList());
            sysPermission.setChildren(children);
            permissionList.removeAll(children);
        }
        return treeList;
    }

    @Override
    public void batchDelete(List<SysPermission> permList) {
        if (CollectionUtil.isNotEmpty(permList)) {
            List<Long> ids = permList.stream().map(i -> i.getId()).collect(Collectors.toList());
            sysRolePermissionService.remove(new QueryWrapper<SysRolePermission>().in("permission_id", ids));
            this.removeByIds(ids);
        }
    }

    @Override
    public List<SysPermission> getAllByRole(Long roleId) {
        return this.baseMapper.selectListByRoleId(roleId);
    }

    @Override
    public void update(SysPermission sysPermission) {
        if (sysPermission.getIsGroup() == true) {
            SysPermission oldGroup = this.getById(sysPermission.getId());
            List<SysPermission> permissions = this.baseMapper.selectList(new QueryWrapper<SysPermission>().eq("group_name", oldGroup.getGroupName()).eq("is_group", false));
            permissions.stream().forEach(i -> i.setGroupName(sysPermission.getGroupName()));
            this.updateById(sysPermission);
            this.updateBatchById(permissions);
        } else {
            this.updateById(sysPermission);
        }
    }
}
