package com.console.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.base.BaseServiceImpl;
import com.console.common.constant.CommonConstant;
import com.console.modules.sys.mapper.SysMenuMapper;
import com.console.modules.sys.model.SysMenu;
import com.console.modules.sys.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 13:18 2018/9/19
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> queryUserMenuList(Long userId) {
        if (userId == CommonConstant.USER_TYPE_ADMIN.longValue()) {
            List<SysMenu> sysMenus = this.baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 0));
            return getMenuTreeList(0L, sysMenus);
        }
        List<SysMenu> sysMenus = this.baseMapper.selectByUserId(userId);
        return getMenuTreeList(0L, sysMenus);
    }

    @Override
    public List<SysMenu> getAll() {
        List<SysMenu> menuList = this.baseMapper.selectList(new QueryWrapper<SysMenu>().orderByAsc("sort_order"));
        SysMenu root = new SysMenu();
        root.setId(0L);
        root.setName("ROOT");
        List<SysMenu> children = getTreeList(menuList, 0L);
        root.setChildren(children);
        List<SysMenu> tree = new ArrayList<>();
        tree.add(root);
        return tree;
    }

    @Override
    public void batchDelete(List<SysMenu> menuList) {
        List<Long> deleIds = menuList.stream().filter(i -> !i.getId().equals(0L)).map(i -> i.getId()).collect(Collectors.toList());
        this.removeByIds(deleIds);
    }

    private List<SysMenu> getTreeList(List<SysMenu> menuList, Long parentId) {
        List<SysMenu> list = menuList.stream().filter(i -> i.getParentId().equals(parentId)).collect(Collectors.toList());
        for (SysMenu sysMenu : list) {
            List<SysMenu> children = getTreeList(menuList, sysMenu.getId());
            sysMenu.setChildren(children);
        }
        return list;
    }


    /**
     * siderbar导航栏递归
     */
    private List<SysMenu> getMenuTreeList(Long parentId, List<SysMenu> menuList) {
        List<SysMenu> treeMenu = menuList.stream().filter(i -> parentId.equals(i.getParentId())).sorted(Comparator.comparing(SysMenu::getSortOrder)).collect(Collectors.toList());
        for (SysMenu sysMenu : treeMenu) {
            List<SysMenu> children = getMenuTreeList(sysMenu.getId(), menuList);
            sysMenu.setChildren(children);
        }
        return treeMenu;
    }


}
