package com.console.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.console.base.BaseServiceImpl;
import com.console.common.constant.CommonConstant;
import com.console.common.utils.PageUtils;
import com.console.common.utils.Pagination;
import com.console.modules.job.mapper.SysJobMapper;
import com.console.modules.job.model.SysJob;
import com.console.modules.job.service.SysJobService;
import com.console.modules.job.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:34 2018/9/18
 * @Modified By:
 */
@Slf4j
@Service("sysJobService")
@Transactional
public class SysJobServiceImpl extends BaseServiceImpl<SysJobMapper, SysJob> implements SysJobService {
    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<SysJob> sysJobList = this.baseMapper.selectList(null);
        for (SysJob sysJob : sysJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, sysJob.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, sysJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, sysJob);
            }
        }
    }

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        String beanName = (String) params.get("beanName");
        Pagination<SysJob> pagination = new Pagination<>(params);
        IPage<SysJob> page = this.page(
                pagination.getPage(),
                new QueryWrapper<SysJob>()
                        .like(StringUtils.isNotEmpty(beanName), "bean_name", beanName)
                        .orderByAsc("id"));
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            ScheduleUtils.deleteScheduleJob(scheduler, id);
        }
        this.removeByIds(ids);
    }

    @Override
    public void pauseBatch(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.pauseJob(scheduler, id);
        }
        updateBatch(ids, CommonConstant.STATUS_PAUSE);
    }

    @Override
    public void updateBatch(Long[] ids, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", ids);
        map.put("status", status);
        this.baseMapper.updateBatch(map);
    }

    @Override
    public void resumeBatch(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.resumeJob(scheduler, id);
        }
        updateBatch(ids, CommonConstant.STATUS_NORMAL);
    }

    @Override
    public void runBatch(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.run(scheduler, this.getById(id));
        }
    }

    @Override
    public void create(SysJob job) {
        this.baseMapper.insert(job);
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    @Override
    public void update(SysJob job) {
        this.baseMapper.updateById(job);
        ScheduleUtils.updateScheduleJob(scheduler, job);
    }

}
