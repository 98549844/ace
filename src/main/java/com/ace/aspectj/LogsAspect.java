package com.ace.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.AceLogs;
import com.ace.service.AceLogsService;
import com.ace.utilities.PathUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;


/**
 * @Classname: LogsAspect
 * @Date: 17/5/24 AM2:39
 * @Author: garlam
 * @Description:
 */

//打开comment后，开启@Aspect和@Component
//切面日志开启
//@Aspect
//@Component
public class LogsAspect extends CommonController{
    private static final Logger log = LogManager.getLogger(LogsAspect.class.getName());


    //execution 表达式是 AspectJ 中最常用的切点表达式
    //execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
    /*
    modifiers-pattern（可选）：方法的可见性修饰符模式，例如 public、protected、private、*（任意修饰符）等。
    ret-type-pattern：方法的返回类型模式，例如 void、int、*（任意返回类型）等。
    declaring-type-pattern（可选）：方法所在类的全限定名模式，例如 com.example.controllers.*、*Controller 等。
    name-pattern：方法名模式，例如 *（任意方法名）、get*、do* 等。
    param-pattern：方法参数模式，使用逗号分隔多个参数，每个参数可以使用类型模式、通配符 *（任意类型）和 ..（零个或多个任意类型）。
    throws-pattern（可选）：方法抛出异常的类型模式，例如 java.lang.Exception、*Exception 等。
    */

    // Aspectj 执行顺序:
    // 1. @before => ||targetMethods||
    // 2. => 成功则执行 @afterReturning, 失败则执行 @afterThrowing
    // 3. => @after 最后执行
    // 4. @around 功能最全面,缺点是只执行一个方法体, 不能做到很好的细分

    // @annotation是针对方法的注解,
    // @within是针对类注解,
    // @target是针对目标注解,
    // @args是针对参数注解
    private final AceLogsService aceLogsService;

    public LogsAspect(AceLogsService aceLogsService) {
        this.aceLogsService = aceLogsService;
    }

    //joinPoint.getSignature().getName()  方法名
    //joinPoint.getSignature().getDeclaringTypeName()  full package path
    //joinPoint.getSignature().toLongString()  package+返回类型 and package+方法名
    //joinPoint.getSignature().toString() // 返回类型 and package+方法名
    @Before("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service) && !target(com.ace.service.AceLogsService) || @within(org.springframework.stereotype.Repository)")
    public void logBefore(JoinPoint joinPoint) {
        String clazz = joinPoint.getSignature().getDeclaringTypeName() + "->" + joinPoint.getSignature().getName();
        this.logs = record(clazz);
        this.logs.setAspectFlow(AceLogs.BEFORE + "->");
    }


    private AceLogs logs;

    private AceLogs record(String clazz) {
        AceLogs logs = new AceLogs();
        try {
            logs.setOperator(getCurrentUser().getUserAccount());
        } catch (Exception e) {
            logs.setOperator(AceLogs.NONE);
        }

        logs.setClazz(clazz);
        logs.setAccessTime(LocalDateTime.now());
        return logs;
    }


    @AfterReturning(returning = "result", pointcut = "@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service) && !target(com.ace.service.AceLogsService) || @within(org.springframework.stereotype.Repository)")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        if ("exceptionHandler".equals(joinPoint.getSignature().getName())) {
            ModelAndView modelAndView = (ModelAndView) result;
            String stackTrace = (String) modelAndView.getModel().get("stackTrace");
            String expMsg = (String) modelAndView.getModel().get("expMsg");
            String stringBuilder = "exp message: " + expMsg + PathUtil.newLine() + "exp stackTrace: " + stackTrace;
            this.logs.setException(stringBuilder);
        }

        this.logs.setAspectFlow(this.logs.getAspectFlow() + AceLogs.AFTER_RETURNING + "->");

    }


    //&& execution(* *(..)
    //execution(* *(..)) 切点表达式将匹配任意类中的任意方法 返回类型、方法名以及参数的数量和类型
    @AfterThrowing(pointcut = "@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service) && !target(com.ace.service.AceLogsService) || @within(org.springframework.stereotype.Repository)", throwing = "ex")
    public void logException(Exception ex) {
        //把printStackTrace保存到aceLogs
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        printWriter.flush();
        String stackTrace = stringWriter.toString();

        String expBuilder = "exp message: " + ex.getMessage() + PathUtil.newLine() + "exp stackTrace: " + stackTrace;
        this.logs.setException(expBuilder); //传入exception信息
        this.logs.setAspectFlow(this.logs.getAspectFlow() + AceLogs.AFTER_THROWING + "->");

    }

    @After("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service) && !target(com.ace.service.AceLogsService) || @within(org.springframework.stereotype.Repository)")
    public void after(JoinPoint joinPoint) {
        this.logs.setAspectFlow(this.logs.getAspectFlow() + AceLogs.AFTER + "; ");
        aceLogsService.save(logs);
    }

    // @Around 包含了@before @afterReturning @afterThrowing @after 四大功能, 但不够细分和灵活
    /*
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("@Around");
        *//*result为连接点的放回结果*//*
        Object result = null;
        String methodName = joinPoint.getSignature().getName();

        *//*前置通知方法*//*
        System.out.println("前置通知方法>目标方法名：" + methodName + ",参数为：" + Arrays.asList(joinPoint.getArgs()));

        *//*执行目标方法*//*
        try {
            result = joinPoint.proceed();

            *//*返回通知方法*//*
            System.out.println("返回通知方法>目标方法名" + methodName + ",返回结果为：" + result);
        } catch (Throwable e) {
            *//*异常通知方法*//*
            System.out.println("异常通知方法>目标方法名" + methodName + ",异常为：" + e);
            *//*当环绕通知方法本身还有其它异常时，非连接点方法出现的异常，此时抛出来*//*
            throw new RuntimeException();
        }
        *//*后置通知*//*
        System.out.println("后置通知方法>目标方法名" + methodName);
        return result;
    }
    */

    /*
    @Before("execution(* com.ace.controller.*.*(..))")
    public void logControllerBefore(JoinPoint joinPoint) {
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
    }
    */

    /*
    @AfterReturning("execution(* com.ace.controller.*.*(..))")
    public void logControllerAfterReturning(JoinPoint joinPoint) {
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
    }
    */

    /*
    @Before("execution(* com.ace.restController.*.*(..))")
    public void logRestControllerBefore(JoinPoint joinPoint) {
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
    }
    */

    /*
    @AfterReturning("execution(* com.ace.restController.*.*(..))")
    public void logRestControllerAfterReturning(JoinPoint joinPoint) {
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
    }
    */


}

