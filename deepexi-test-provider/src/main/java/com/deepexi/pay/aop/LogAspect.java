/**
 * LogAspect  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.aop;

import com.alibaba.fastjson.JSON;
import com.deepexi.pay.extension.AppRuntimeEnv;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 日志统一打印切面
 * @program: LogAspect
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/27 下午3:37
 **/
@Component
@Aspect
public class LogAspect {

    private final static String TENANT_KEY = "tenantId";
    private final static String TOKEN_KEY = "userToken";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Pointcut("execution (* com.deepexi.pay.controller..*.*(..))")
    public void controllerLogAop() {
    }

    @Pointcut("execution (* com.deepexi.pay.service..*.*(..))")
    public void apiLogAop() {
    }

    @Around("controllerLogAop()")
    public Object aroundController(ProceedingJoinPoint point) throws Throwable {

        // controller传入记录token和tenantId这些通用参数
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            appRuntimeEnv.setTenantId(getParam(request, TENANT_KEY));
            appRuntimeEnv.setToken(getParam(request, TOKEN_KEY));
        }

        Object response = null;
        try {
            //执行该方法
            response = point.proceed();
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Around("apiLogAop()")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        logger.info("日志统一打印 ===== {}.{}() start =====,参数:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), argsToString(point.getArgs()));
        DateTime startTime = new DateTime();
        DateTime endTime = null;
        Interval interval = null;
        Object response = null;

        try {
            //执行该方法
            response = point.proceed();
        } catch (Exception e) {
            endTime = new DateTime();
            interval = new Interval(startTime, endTime);
            logger.info("日志统一打印 ===== {}.{}() end =====,响应时间:{}毫秒,响应内容:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), interval.toDurationMillis());
            throw e;
        }
        endTime = new DateTime();
        interval = new Interval(startTime, endTime);
        logger.info("日志统一打印 ===== {}.{}() end =====,响应时间:{}毫秒,响应内容:\n{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), interval.toDurationMillis(), argsToString(response));
        return response;
    }

    private String argsToString(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            logger.error("", e);
        }
        return String.valueOf(object);
    }

    /**
     * 获取业务参数
     *
     * @param request
     * @param param
     * @throws Exception
     */
    private String getParam(HttpServletRequest request, String param) throws Exception {
        String[] reqParam = request.getParameterValues(param);
        return (reqParam == null || reqParam.length < 1 ? null : reqParam[0]);
    }

}
