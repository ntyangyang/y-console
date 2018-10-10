package com.console.modules.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.console.modules.job.model.SysJob;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:35 2018/9/18
 * @Modified By:
 */
public interface SysJobMapper extends BaseMapper<SysJob> {
    void updateBatch(Map<String, Object> map);
}
