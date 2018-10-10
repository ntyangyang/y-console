package com.console.modules.sys.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.console.common.response.Result;
import com.console.common.response.ResultGenerator;
import com.console.common.utils.PageUtils;
import com.console.common.validator.ValidatorUtils;
import com.console.common.validator.group.AddGroup;
import com.console.common.validator.group.UpdateGroup;
import com.console.modules.sys.model.SysConfig;
import com.console.modules.sys.model.SysPermission;
import com.console.modules.sys.model.SysRole;
import com.console.modules.sys.service.SysConfigService;
import com.console.modules.sys.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:59 2018/9/25
 * @Modified By:
 */
@Slf4j
@RestController()
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("/list")
    public Result search(@RequestParam Map<String, Object> params) {
        PageUtils configPage = sysConfigService.queryPageList(params);
        return ResultGenerator.genOkResult(configPage);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> ids) {
        sysConfigService.deleteByIds(ids);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysConfig config) {
        if (config.getId() == null) {
            ValidatorUtils.validateEntity(config, AddGroup.class);
            config.setCreateBy(getUserId());
            config.setCreateTime(new Date());
            sysConfigService.save(config);
        } else {
            ValidatorUtils.validateEntity(config, UpdateGroup.class);
            config.setUpdateBy(getUserId());
            config.setUpdateTime(new Date());
            sysConfigService.updateById(config);
        }
        return ResultGenerator.genOkResult();
    }
}
