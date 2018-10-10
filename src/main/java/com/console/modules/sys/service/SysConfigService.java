package com.console.modules.sys.service;

import com.console.base.BaseService;
import com.console.common.utils.PageUtils;
import com.console.modules.sys.model.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:41 2018/9/25
 * @Modified By:
 */
public interface SysConfigService extends BaseService<SysConfig> {
    PageUtils queryPageList(Map<String, Object> params);

    void deleteByIds(List<Long> ids);
}
