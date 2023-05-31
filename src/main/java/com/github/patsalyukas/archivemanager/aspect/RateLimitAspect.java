package com.github.patsalyukas.archivemanager.aspect;

import com.github.patsalyukas.archivemanager.error.TooManyRequestsException;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final Bucket bucket;

    @Pointcut("execution(* com.github.patsalyukas.archivemanager.controllers.*.*(..))")
    public  void rateLimitPointcut() {

    }

    @Around("rateLimitPointcut()")
    public Object rateLimitAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        if (bucket == null) {
            return joinPoint.proceed();
        }
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return joinPoint.proceed();
        }
        throw new TooManyRequestsException();
    }
}
