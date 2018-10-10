package com.console.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.console.modules.sys.model.SysUserToken;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:39 2018/9/17
 * @Modified By:
 */
public interface SysUserTokenMapper extends BaseMapper<SysUserToken> {
    SysUserToken queryByToken(String token);
}
