package com.console.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.console.base.BaseServiceImpl;
import com.console.common.utils.PageUtils;
import com.console.common.utils.Pagination;
import com.console.modules.sys.mapper.SysUserMapper;
import com.console.modules.sys.mapper.SysUserRoleMapper;
import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.model.SysUserRole;
import com.console.modules.sys.service.SysUserRoleService;
import com.console.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 0:55 2018/9/18
 * @Modified By:
 */
@Slf4j
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        String username = (String) params.get("username");
        Pagination<SysUser> pagination = new Pagination<>(params);
        IPage<SysUser> page = this.page(
                pagination.getPage(),
                new QueryWrapper<SysUser>()
                        .like(StringUtils.isNotEmpty(username), "username", username)
                        .excludeColumns(SysUser.class, "password", "salt").orderByAsc("id"));
        return new PageUtils(page);
    }

    @Override
    public SysUser queryByUsername(String username) {
        return this.getOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        this.baseMapper.deleteBatchIds(ids);
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("user_id",ids));
    }

    @Override
    public void saveUserRoles(Long userId, List<Long> roleIds) {
        List<SysUserRole> list = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        sysUserRoleService.saveBatch(list);
    }

    @Override
    public void updateUserRoles(Long userId, List<Long> roleIds) {
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        if (CollectionUtil.isNotEmpty(roleIds)) {
            this.saveUserRoles(userId, roleIds);
        }
    }

}
