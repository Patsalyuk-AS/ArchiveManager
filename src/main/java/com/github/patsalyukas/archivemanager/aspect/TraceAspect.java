package com.github.patsalyukas.archivemanager.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Pointcut("execution(* com.github.patsalyukas.archivemanager..*(..))")
    public void all() {}

    @Before("all()")
    public void trace(JoinPoint joinPoint) {
        log.info(String.format(">> Execution: %s", joinPoint.getSignature()));
    }
}
