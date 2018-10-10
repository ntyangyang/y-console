package com.console.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Map;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 23:04 2018/9/20
 * @Modified By:
 */
@Data
public class Pagination<T> {
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页条数
     */
    private int pageSize = 10;

    public Pagination(Map<String, Object> params) {
        //分页参数
        if (params.get("pageNum") != null) {
            pageNum = Integer.parseInt((String) params.get("pageNum"));
        }
        if (params.get("pageSize") != null) {
            pageSize = Integer.parseInt((String) params.get("pageSize"));
        }
        //mybatis-plus分页
        this.page = new Page<T>(pageNum, pageSize);
    }

}
