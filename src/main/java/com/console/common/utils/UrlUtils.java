package com.console.common.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yang
 * @Description: Url工具
 * @Date:Create In 2:02 2018/8/6
 * @Modified By:
 */
public class UrlUtils {
    private UrlUtils() {
    }

    /**
     * 请求的相对路径 /user/list
     *
     * @param request request
     * @return 相对路径
     */
    public static String getMappingUrl(final ServletRequest request) {
        return getMappingUrl((HttpServletRequest) request);
    }

    public static String getMappingUrl(final HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length());
    }
}
