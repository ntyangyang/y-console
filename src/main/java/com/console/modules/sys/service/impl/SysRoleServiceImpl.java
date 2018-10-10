package com.console.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.console.base.BaseServiceImpl;
import com.console.common.utils.PageUtils;
import com.console.common.utils.Pagination;
import com.console.modules.sys.mapper.SysRoleMapper;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.model.SysRolePermission;
import com.console.modules.sys.model.SysUserRole;
import com.console.modules.sys.service.SysRolePermissionService;
import com.console.modules.sys.service.SysRoleService;
import com.console.modules.sys.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 21:52 2018/9/20
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysRole> queryByUserId(Long userId) {
        return this.baseMapper.queryByUserId(userId);
    }

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        String name = (String) params.get("name");
        Pagination<SysRole> pagination = new Pagination<>(params);
        IPage<SysRole> page = this.page(
                pagination.getPage(),
                new QueryWrapper<SysRole>()
                        .like(StringUtils.isNotEmpty(name), "name", name)
                        .orderByAsc("create_time"));
        return new PageUtils(page);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("role_id",ids));
        sysRolePermissionService.remove((new QueryWrapper<SysRolePermission>().in("role_id",ids)));
        this.removeByIds(ids);
    }

    @Override
    public List<SysRole> queryByMenuId(Long menuId) {
        return this.baseMapper.queryByMenuId(menuId);
    }
}
