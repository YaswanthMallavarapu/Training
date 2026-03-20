package org.ecom.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RepositoryLoggingAspect {

    // Target all repository methods
    @Pointcut("execution(* org.ecom.repository.*.*(..))")
    public void repositoryMethods() {}

    // Before method execution
    @Before("repositoryMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Method Called: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    // After returning
    @AfterReturning(pointcut = "repositoryMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("Method Completed: " + joinPoint.getSignature().getName());
        System.out.println("Return Value: " + result);
    }

    // After throwing exception
    @AfterThrowing(pointcut = "repositoryMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception in: " + joinPoint.getSignature().getName());
        System.out.println("Error: " + ex.getMessage());
    }
}