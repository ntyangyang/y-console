package com.console.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class PageUtils {
    //总记录数
    private long total;
    //每页记录数
    private long pageSize;
    //总页数
    private long totalPage;
    //当前页数
    private long currPage;
    //列表数据
    private List<?> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param total 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtils(List<?> list, long total, long pageSize, long currPage) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (long) Math.ceil((double) total / pageSize);
    }

    /**
     * 分页
     */
    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.total = page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = page.getPages();
    }

}