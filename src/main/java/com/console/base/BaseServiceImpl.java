package com.console.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.console.common.constant.CommonConstant;

import java.util.Collection;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 16:49 2018/9/15
 * @Modified By:
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> extends ServiceImpl<M, T> implements BaseService<T> {


}
