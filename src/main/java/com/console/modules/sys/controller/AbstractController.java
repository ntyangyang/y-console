package com.console.modules.sys.controller;

import com.console.modules.sys.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 0:34 2018/9/18
 * @Modified By:
 */
public abstract class AbstractController {

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getId();
    }
}
