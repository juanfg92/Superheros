package com.jfg.superheros.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("@annotation(ExecutionTime)")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        System.out.println(joinPoint.getSignature().getName()+ ": " + (System.currentTimeMillis() - startTime) + " milliseconds");
        return result;
    }
}