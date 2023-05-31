package com.github.patsalyukas.archivemanager.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class LogAspect {

    @Pointcut("@within(com.github.patsalyukas.archivemanager.annotation.LogExecutionTime))")
    public void callInAnnotatedClass() {}

    @Around("callInAnnotatedClass()")
    public Object logAroundAnnotatedClass(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("{}.{} executed in {} ms.", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), executionTime);
        return proceed;
    }
}
