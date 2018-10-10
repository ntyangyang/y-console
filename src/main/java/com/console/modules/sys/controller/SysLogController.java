package com.console.modules.sys.controller;

import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.PageUtils;
import com.console.modules.sys.service.SysLogService;
import com.console.modules.sys.service.impl.SysLogServiceImpl;
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
 * @Date:Create In 6:13 2018/9/22
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/list")
    public Result search(@RequestParam Map<String, Object> params) {
        PageUtils userPage = sysLogService.queryPageList(params);
        return ResultGenerator.genOkResult(userPage);
    }
}
