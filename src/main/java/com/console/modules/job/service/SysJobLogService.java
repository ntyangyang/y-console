package com.console.modules.job.service;

import com.console.base.BaseService;
import com.console.common.utils.PageUtils;
import com.console.modules.job.model.SysJob;
import com.console.modules.job.model.SysJobLog;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:32 2018/9/18
 * @Modified By:
 */
public interface SysJobLogService extends BaseService<SysJobLog> {
    PageUtils queryPageList(Map<String, Object> params);
}
