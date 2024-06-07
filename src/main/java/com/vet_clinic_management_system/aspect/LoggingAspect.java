package com.vet_clinic_management_system.aspect;

import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.exception.VetClinicException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Configuration
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("com.vet_clinic_management_system.aspect.PointcutConfig.serviceLayer()")
    public void logServiceMethodsBefore(JoinPoint jp){
        logger.info("BEFORE ADVICE : method called {}",jp.getSignature().getName());
    }

    @AfterReturning("com.vet_clinic_management_system.aspect.PointcutConfig.serviceLayer()")
    public void logServiceMethodsAfterReturn(JoinPoint jp){
        logger.info("AFTER RETURN ADVICE : method called {} witho args: {}",jp.getSignature().getName(),jp.getArgs());
    }

    @After("com.vet_clinic_management_system.aspect.PointcutConfig.controllerServiceLayer()")
    public void logServiceMethodsAfter(JoinPoint jp){
        logger.info("AFTER ADVICE : method called {} with args: {}",jp.getSignature().getName(),jp.getArgs());
    }

    @AfterThrowing(value = "com.vet_clinic_management_system.aspect.PointcutConfig.serviceLayer()", throwing = "exp")
    public void logServiceMethodsAfterThrowing(JoinPoint jp, VetClinicException exp){
        logger.info("AFTER THROWING ADVICE : Exception message thrown in method {} is {} ",jp.getSignature().getName(),exp.getMessage());
    }

    @Around(value = "@annotation(com.vet_clinic_management_system.aspect.MeasureTime)")
    public Object countTimeTakenToExecute(ProceedingJoinPoint pjp) throws Throwable {
        var startTime = System.currentTimeMillis();
        var object = pjp.proceed();
        var endTime = System.currentTimeMillis();
        logger.info("Time taken for method {} to complete is {} ms",pjp.getSignature().getName(),(endTime-startTime));
        return object;
    }

    @Around("execution(* com.vet_clinic_management_system.service..*(..)) || execution(* com.vet_clinic_management_system.controller..*(..))")
    public Object logServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Executing {} inside {}", proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getThis().getClass().getSimpleName());
        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.vet_clinic_management_system.controller..*(..))")
    public Object logUser(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDTO) {
            logger.info("Logged user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }
        return proceedingJoinPoint.proceed();
    }

}
