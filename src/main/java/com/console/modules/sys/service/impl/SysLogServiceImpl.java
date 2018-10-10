package com.console.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.console.base.BaseServiceImpl;
import com.console.common.utils.PageUtils;
import com.console.common.utils.Pagination;
import com.console.modules.sys.mapper.SysLogMapper;
import com.console.modules.sys.model.SysLog;
import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 3:59 2018/9/18
 * @Modified By:
 */
@Slf4j
@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        String name = (String) params.get("name");
        Pagination<SysLog> pagination = new Pagination<>(params);
        boolean flag = StringUtils.isNotEmpty(name);
        IPage<SysLog> page = this.page(
                pagination.getPage(),
                new QueryWrapper<SysLog>()
                        .like(flag, "username", name)
                        .or()
                        .like(flag, "operation", name)
                        .orderByAsc("create_time"));
        return new PageUtils(page);
    }
}
