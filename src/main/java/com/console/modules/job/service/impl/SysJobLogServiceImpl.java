package com.console.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.console.base.BaseServiceImpl;
import com.console.common.utils.PageUtils;
import com.console.common.utils.Pagination;
import com.console.modules.job.mapper.SysJobLogMapper;
import com.console.modules.job.model.SysJob;
import com.console.modules.job.model.SysJobLog;
import com.console.modules.job.service.SysJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:57 2018/9/18
 * @Modified By:
 */
@Slf4j
@Service("sysJobLogService")
@Transactional
public class SysJobLogServiceImpl extends BaseServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");
        Pagination<SysJobLog> pagination = new Pagination<>(params);
        QueryWrapper<SysJobLog> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(jobId)) {
            queryWrapper.eq("job_id",Long.valueOf(jobId));
        }
        queryWrapper.orderByAsc("create_time");
        IPage<SysJobLog> page = this.page(
                pagination.getPage(),
                queryWrapper);
        return new PageUtils(page);
    }
}
