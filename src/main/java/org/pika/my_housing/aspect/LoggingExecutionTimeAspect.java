package org.pika.my_housing.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingExecutionTimeAspect {
    Logger logger = LoggerFactory.getLogger(LoggingExecutionTimeAspect.class);

    @Pointcut("execution(public * org.pika.my_housing.controller..*(..))")
    public void callAtAllControllers(){}

    @Pointcut("@annotation(org.pika.my_housing.aspect.annotaion.LogExecutionTime)")
    public void callAtAnnotation(){}

    @Around("callAtAllControllers()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        long finish = System.currentTimeMillis();
        logger.info("Exec time " + joinPoint.getSignature() + ": " + (finish - start) + "ms");
        return o;
    }
}
