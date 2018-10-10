package com.console.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.console.modules.sys.model.SysMenu;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 12:57 2018/9/19
 * @Modified By:
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> selectByUserId(Long userId);
}
