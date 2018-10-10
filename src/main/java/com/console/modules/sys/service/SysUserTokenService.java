package com.console.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.console.base.BaseService;
import com.console.common.response.Result;
import com.console.modules.sys.model.SysUserToken;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 0:36 2018/9/18
 * @Modified By:
 */
public interface SysUserTokenService extends IService<SysUserToken> {
    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    Result createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);
}
