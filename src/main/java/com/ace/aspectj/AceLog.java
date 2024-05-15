package com.ace.aspectj;

import java.lang.annotation.*;


/**
 * @Classname: AceLog
 * @Date: 15/5/24 PM11:10
 * @Author: garlam
 * @Description:
 */


//@Target(ElementType.METHOD)，
//@Retention(RetentionPolicy.RUNTIME)
//@Documented这三个注解意思分别是

//@Target(ElementType.METHOD)表示该注解的使用范围为方法
//除此之外还有ElementType.TYPE,ElementType.ANNOTATION_TYPE等表示作用于类、

//注解上
//@Retention定义它所注解的注解保留多久，
//RetentionPolicy.RUNTIME表示保留在运行时，
//我们可以通过反射去获取注解信息
//@Documented表示会被包含在javadoc中
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AceLog {
    //自定义aspectj, 需要在方法上打上@AceLog("自定义内容")
    String value() default "";
}
