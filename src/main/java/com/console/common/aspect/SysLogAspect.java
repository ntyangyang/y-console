package com.console.common.aspect;

import com.console.common.annotation.ConsoleLog;
import com.console.common.utils.HttpContextUtil;
import com.console.common.utils.IpUtils;
import com.console.modules.sys.model.SysLog;
import com.console.modules.sys.model.SysUser;
import com.console.modules.sys.service.SysLogService;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 3:53 2018/9/18
 * @Modified By:
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.console.common.annotation.ConsoleLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        ConsoleLog consoleLog = method.getAnnotation(ConsoleLog.class);
        if (consoleLog != null) {
            //注解上的描述
            sysLog.setOperation(consoleLog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IpUtils.getIpAddress(request));
        sysLog.setIpInfo(IpUtils.getInfoByIP(IpUtils.getIpAddress(request)));

        //用户名
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            String username = sysUser.getUsername();
            sysLog.setUsername(username);
        }

        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
    }
}
