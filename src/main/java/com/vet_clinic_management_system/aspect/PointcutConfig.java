package com.vet_clinic_management_system.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutConfig {
    @Pointcut("execution(* com.vet_clinic_management_system.service.*.*(..))")
    public void serviceLayer(){}

    @Pointcut("execution(* com.vet_clinic_management_system.controller.*.*(..))")
    public void controllerLayer(){}

    @Pointcut("com.vet_clinic_management_system.aspect.PointcutConfig.serviceLayer() || com.vet_clinic_management_system.aspect.PointcutConfig.controllerLayer()")
    public void controllerServiceLayer(){}
}
