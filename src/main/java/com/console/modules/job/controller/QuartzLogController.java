package com.console.modules.job.controller;

import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.PageUtils;
import com.console.modules.job.service.SysJobLogService;
import com.console.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 5:26 2018/9/22
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/quartz/log")
public class QuartzLogController extends AbstractController {
    @Autowired
    private SysJobLogService sysJobLogService;

    @GetMapping("list")
    public Result search(@RequestParam Map<String, Object> params) {
        PageUtils jobPage = sysJobLogService.queryPageList(params);
        return ResultGenerator.genOkResult(jobPage);
    }
}
