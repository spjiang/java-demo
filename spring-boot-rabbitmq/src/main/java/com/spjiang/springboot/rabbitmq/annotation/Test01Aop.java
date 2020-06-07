package com.spjiang.springboot.rabbitmq.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Package: com.com.annotation
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-01 11:45
 */
@Aspect
@Component
public class Test01Aop {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.spjiang.springboot.rabbitmq.annotation.Test01)")
    public void Pointcut() {
    }

    @Before("Pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest requests = (HttpServletRequest) args[0];
        log.info("============打印日志开始============");
        log.info("URL: " + requests.getRequestURL().toString());
        String aa = requests.getParameter("aa");
        log.info(aa);
        log.info("============打印日志结束============");
//        LOG.info("before....");
    }
}
