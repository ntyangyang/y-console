package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.common.utils.PageUtils;
import com.console.modules.sys.model.SysLog;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 3:59 2018/9/18
 * @Modified By:
 */
public interface SysLogService extends BaseService<SysLog> {
    PageUtils queryPageList(Map<String, Object> params);
}
