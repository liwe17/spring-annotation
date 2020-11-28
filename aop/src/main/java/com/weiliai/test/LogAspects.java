package com.weiliai.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author: Doug Li
 * @Date: 2019/7/23
 * @Describe:
 */
@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.weiliai.test.MathCalculator.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public  void logStart(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"运行...参数列表:{"+ Arrays.toString(joinPoint.getArgs())+"}");
    }

    //JoinPoint joinPoint一定在参数表第一位
    @After("com.weiliai.test.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"结束...");
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"正常返回...运行结果:{"+result+"]");
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"异常...异常信息:{"+exception+"}");
    }

}
