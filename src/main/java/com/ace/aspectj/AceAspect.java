package com.ace.aspectj;

import com.ace.utilities.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @Classname: AceAspect
 * @Date: 15/5/24 PM11:14
 * @Author: garlam
 * @Description:
 */
//https://blog.csdn.net/weixin_45131680/article/details/136192847

@Aspect
@Component
public class AceAspect {
    private static final Logger log = LogManager.getLogger(AceAspect.class.getName());

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     * <p>
     * execution(public * *(..)) 任意的公共方法
     * execution（* set*（..）） 以set开头的所有的方法
     * execution（* com.LoggerApply.*（..））com.LoggerApply这个类里的所有的方法
     * execution（* com.annotation.*.*（..））com.annotation包下的所有的类的所有的方法
     * execution（* com.annotation..*.*（..））com.annotation包及子包下所有的类的所有的方法
     * execution(* com.annotation..*.*(String,?,Long))
     * com.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，
     * 第二个参数为任意类型，第三个参数为Long类型的方法
     * execution(@annotation(com.lingyejun.annotation.Lingyejun))
     */

    @Pointcut(value = "@annotation(com.ace.aspectj.AceLog)")
    public void aceLogPointCut() {
    }

    @Around("aceLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(UUID.getOrigin());
        System.out.println(("Around advice running for method: " + joinPoint.getSignature().getName()));
        Object result = joinPoint.proceed();
        try {
            //保存日志
            System.out.println("保存日志");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Before("aceLogPointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println(UUID.getOrigin());
        System.out.println(("Before advice running for method: " + joinPoint.getSignature().getName()));
    }

    @After("aceLogPointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println(UUID.getOrigin());
        System.out.println(("After advice running for method: " + joinPoint.getSignature().getName()));
    }
}

