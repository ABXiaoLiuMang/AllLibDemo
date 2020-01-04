//package com.dale.xutils.logintest;
//
//import com.dale.App;
//import com.dale.log.LogUtils;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//
//import java.lang.reflect.Method;
//
////http://www.sohu.com/a/291849354_608959
//
//@Aspect
//public class LoginInterceptAspect {
//
//    private static final String path = "com.dale.xutils.logintest.LoginIntercept";
//
//    @Pointcut("execution(@"+path+" * *(..))")
//    public void pointcut() {
//
//    }
//
//    @Around("pointcut()")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        boolean isLogin = App.isLogin;
//        if (isLogin) {
//            joinPoint.proceed();
//        } else {
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            Method method = methodSignature.getMethod();
//            LoginIntercept annotation = method.getAnnotation(LoginIntercept.class);
//            String content = annotation.content();
//            LogUtils.d("around: 去登录");
//        }
//    }
//
//    @AfterThrowing(value = "pointcut()", throwing = "ex")
//    public void afterThrowing(Throwable ex) {
//        LogUtils.d("afterThrowing:" + ex.getMessage());
//    }
//
//}
//
