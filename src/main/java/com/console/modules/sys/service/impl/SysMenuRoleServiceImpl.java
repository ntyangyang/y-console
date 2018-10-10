package com.console.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.base.BaseServiceImpl;
import com.console.modules.sys.mapper.SysMenuRoleMapper;
import com.console.modules.sys.model.*;
import com.console.modules.sys.service.SysMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 16:38 2018/9/25
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class SysMenuRoleServiceImpl extends BaseServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {

    @Override
    public void save(SysMenu sysMenu, List<Long> roleIds) {
        List<SysMenuRole> list = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysMenuRole sysMenuRole = new SysMenuRole();
            sysMenuRole.setMenuId(sysMenu.getId());
            sysMenuRole.setRoleId(roleId);
            list.add(sysMenuRole);
        }
        this.saveBatch(list);
    }

    @Override
    public void update(SysMenu sysMenu, List<Long> roleIds) {
        this.remove(new QueryWrapper<SysMenuRole>().eq("menu_id", sysMenu.getId()));
        if (CollectionUtil.isNotEmpty(roleIds)) {
            this.save(sysMenu, roleIds);
        }
    }
}
