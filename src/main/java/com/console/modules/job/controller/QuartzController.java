package com.console.modules.job.controller;

import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.PageUtils;
import com.console.common.validator.ValidatorUtils;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.console.modules.job.model.SysJob;
import com.console.modules.job.service.SysJobService;
import com.console.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 3:35 2018/9/22
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/quartz")
public class QuartzController extends AbstractController {
    @Autowired
    private SysJobService sysJobService;

    @GetMapping("list")
    public Result search(@RequestParam Map<String, Object> params) {
        PageUtils jobPage = sysJobService.queryPageList(params);
        return ResultGenerator.genOkResult(jobPage);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> ids) {
        sysJobService.deleteBatch(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/pause")
    public Result pause(@RequestBody Long[] ids) {
        sysJobService.pauseBatch(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/resume")
    public Result resume(@RequestBody Long[] ids) {
        sysJobService.resumeBatch(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/run")
    public Result run(@RequestBody Long[] ids) {
        sysJobService.runBatch(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysJob job) {
        if (job.getId() == null) {
            ValidatorUtils.validateEntity(job, AddGroup.class);
            job.setCreateBy(getUserId());
            job.setCreateTime(new Date());
            sysJobService.create(job);
        } else {
            ValidatorUtils.validateEntity(job, UpdateGroup.class);
            job.setUpdateBy(getUserId());
            job.setUpdateTime(new Date());
            sysJobService.update(job);
        }
        return ResultGenerator.genOkResult();
    }

}
