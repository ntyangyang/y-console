package com.console;

import cn.hutool.core.bean.BeanUtil;
import com.console.common.utils.SnowFlakeUtil;
import com.console.modules.sys.mapper.SysMenuMapper;
import com.console.modules.sys.mapper.SysPermissionMapper;
import com.console.modules.sys.mapper.SysRoleMapper;
import com.console.modules.sys.mapper.SysUserMapper;
import com.console.modules.sys.model.SysMenu;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class YConsoleApplicationTests {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Test
    public void contextLoads() {

        SysPermission p = new SysPermission();
        p.setCreateTime(new Date());
        p.setGroupName("用户管理");
        p.setIsGroup(true);

        SysPermission p4 = new SysPermission();
        p4.setCreateTime(new Date());
        p4.setName("user:select");
        p4.setDescription("用户查询");
        p4.setGroupName("用户管理");
        p4.setIsGroup(false);

        SysPermission p1 = new SysPermission();
        p1.setCreateTime(new Date());
        p1.setName("user:create");
        p1.setDescription("用户添加");
        p1.setGroupName("用户管理");
        p1.setIsGroup(false);

        SysPermission p2 = new SysPermission();
        p2.setCreateTime(new Date());
        p2.setName("user:update");
        p2.setDescription("用户修改");
        p2.setGroupName("用户管理");
        p2.setIsGroup(false);
        SysPermission p3 = new SysPermission();
        p3.setCreateTime(new Date());
        p3.setName("user:delete");
        p3.setDescription("用户删除");
        p3.setGroupName("用户管理");
        p3.setIsGroup(false);


        sysPermissionMapper.insert(p);
        sysPermissionMapper.insert(p1);
        sysPermissionMapper.insert(p2);
        sysPermissionMapper.insert(p3);
        sysPermissionMapper.insert(p4);
    }

}
