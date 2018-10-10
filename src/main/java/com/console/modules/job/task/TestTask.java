package com.console.modules.job.task;

import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 5:01 2018/9/18
 * @Modified By:
 */
@Slf4j
@Component("testTask")
public class TestTask {
    @Autowired
    private SysUserService sysUserService;

    public void test(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：" + params);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SysUser user = sysUserService.getById(1L);
        System.out.println(ToStringBuilder.reflectionToString(user));

    }


    public void test2() {
        log.info("我是不带参数的test2方法，正在被执行");
    }
}
