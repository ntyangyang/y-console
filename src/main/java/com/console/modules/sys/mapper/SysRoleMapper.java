package com.console.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.console.modules.sys.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:53 2018/9/17
 * @Modified By:
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> queryByUserId(@Param("userId") long userId);

    List<SysRole> queryByMenuId(@Param("menuId")Long menuId);
}
