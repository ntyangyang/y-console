package com.console.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.console.base.BaseServiceImpl;
import com.console.common.utils.PageUtils;
import com.console.common.utils.Pagination;
import com.console.modules.sys.mapper.SysConfigMapper;
import com.console.modules.sys.model.SysConfig;
import com.console.modules.sys.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 22:42 2018/9/25
 * @Modified By:
 */
@Slf4j
@Service("sysConfigService")
@Transactional
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        String paramKey = (String) params.get("paramKey");
        Pagination<SysConfig> pagination = new Pagination<>(params);
        IPage<SysConfig> page = this.page(
                pagination.getPage(),
                new QueryWrapper<SysConfig>()
                        .like(StringUtils.isNotEmpty(paramKey), "param_key", paramKey)
                        .orderByAsc("create_time"));
        return new PageUtils(page);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        this.removeByIds(ids);
    }

}
