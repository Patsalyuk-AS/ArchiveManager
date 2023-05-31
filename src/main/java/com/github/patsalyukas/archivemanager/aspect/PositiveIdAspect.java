package com.github.patsalyukas.archivemanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PositiveIdAspect {

    @Pointcut("execution(* com.github.patsalyukas.archivemanager.controllers.*.*(Long)) && args(id)")
    public void checkPositiveIdPointcut(Long id) {

    }

    @Before("checkPositiveIdPointcut(id)")
    public void checkPositiveIdAdvice(JoinPoint joinPoint, Long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Значение id должно быть больше 0");
        }
    }
}
