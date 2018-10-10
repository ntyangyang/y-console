package com.console.modules.job.utils;

import cn.hutool.core.bean.BeanUtil;
import com.console.common.constant.CommonConstant;
import com.console.common.utils.SpringContextUtils;
import com.console.modules.job.model.SysJob;
import com.console.modules.job.model.SysJobLog;
import com.console.modules.job.service.SysJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 4:44 2018/9/18
 * @Modified By:
 */
@Slf4j
@Component
public class ConsoleScheduleJob extends QuartzJobBean {
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        SysJob sysJob = new SysJob();
        BeanUtil.copyProperties(context.getMergedJobDataMap().get(SysJob.JOB_PARAM_KEY), sysJob);

        //获取spring bean
        SysJobLogService sysJobLogService = (SysJobLogService) SpringContextUtils.getBean("sysJobLogService");

        //数据库保存执行记录
        SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobId(sysJob.getId());
        sysJobLog.setBeanName(sysJob.getBeanName());
        sysJobLog.setMethodName(sysJob.getMethodName());
        sysJobLog.setParams(sysJob.getParams());
        sysJobLog.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            log.info("任务准备执行，任务ID：" + sysJob.getId());
            ScheduleRunnable task = new ScheduleRunnable(sysJob.getBeanName(),
                    sysJob.getMethodName(), sysJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            sysJobLog.setTimes((int) times);
            //任务状态    0：成功    1：失败
            sysJobLog.setStatus(CommonConstant.STATUS_NORMAL);

            log.info("任务执行完毕，任务ID：" + sysJob.getId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + sysJob.getId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            sysJobLog.setTimes((int) times);

            //任务状态    0：成功    1：失败
            sysJobLog.setStatus(CommonConstant.STATUS_PAUSE);
            sysJobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            sysJobLogService.save(sysJobLog);
        }
    }
}
