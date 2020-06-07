package com.spjiang.annotation;

import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Aspect
@Component
public class SpjiangAop {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.spjiang.annotation.Test03)")
    private void pointcut() {
    }

    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void after(JoinPoint joinPoint, Object ret) {
        System.out.println("spjiang.after");
    }
}

