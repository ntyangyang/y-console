package com.console.modules.job.service;

import com.console.base.BaseService;
import com.console.common.utils.PageUtils;
import com.console.modules.job.model.SysJob;

import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:33 2018/9/18
 * @Modified By:
 */
public interface SysJobService extends BaseService<SysJob> {
    PageUtils queryPageList(Map<String, Object> params);

    void deleteBatch(List<Long> ids);

    void pauseBatch(Long[] ids);

    void updateBatch(Long[] ids, int status);

    void resumeBatch(Long[] ids);

    void runBatch(Long[] ids);

    void create(SysJob job);

    void update(SysJob job);
}
