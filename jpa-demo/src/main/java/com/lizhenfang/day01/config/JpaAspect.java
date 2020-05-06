package com.lizhenfang.day01.config;

import com.alibaba.fastjson.JSON;
import com.lizhenfang.day01.fegin.OptionLogFeginService;
import com.lizhenfang.day01.model.OptionLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
@Aspect
public class JpaAspect {
    @Autowired
    private OptionLogFeginService optionLogFeginService;

    /**
     * 定义切面，把所有controller的方法作为一个切面，*.*是指****Controller，(..)所有的方法
     */
    @Pointcut("execution(public * com.lizhenfang.day01.controller.*.*(..))")
    public void pointCut(){

    }

    //前置通知,pointCut()指定切面
    @Before("pointCut()")
    public void doBefore(){
        System.out.println("doBefore");
    }

    //后置通知
    @After("pointCut()")
    public void doAfter(){
        System.out.println("doAfter");
    }

    //异常通知
    @AfterThrowing("pointCut()")
    public void doAfterThrowing(/*ProceedingJoinPoint proceedingJoinPoint*/){
        /*//调用日志接口
        OptionLog optionLog = new OptionLog();
        optionLog.setId(UUID.randomUUID().toString());
        optionLog.setExeLog(null);
        optionLog.setMethodName(methodName);
        optionLog.setParameter(argsJson);
        optionLog.setReturnValue(resultJson);
        optionLogFeginService.addOptionLog(optionLog);*/
        System.out.println("doAfterThrowing");
    }

    //环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        //调用日志接口
        OptionLog optionLog = new OptionLog();
        try {
            System.out.println("进入环绕通知");
            //调用处理方法，也就是Controller的方法
            Object result = proceedingJoinPoint.proceed();
            //类型信息
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            System.out.println(className);
            //方法信息
            MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
            String methodName = signature.getMethod().getName();
            System.out.println(methodName);
            //根据method读取注解的信息
            Log log = signature.getMethod().getAnnotation(Log.class);
            if(log!=null){
                System.out.println(log.value());
            }
            //请求的参数
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String requestURI = request.getRequestURI();
            System.out.println(requestURI);
            //方法参数
            Object[] args = proceedingJoinPoint.getArgs();
            String argsJson = JSON.toJSONString(args);
            System.out.println(argsJson);
            //返回值
            String resultJson = JSON.toJSONString(result);
            System.out.println(resultJson);
            System.out.println("退出环绕通知");

            optionLog.setId(UUID.randomUUID().toString());
            optionLog.setExeLog(null);
            optionLog.setMethodName(methodName);
            optionLog.setParameter(argsJson);
            optionLog.setReturnValve(resultJson);
            //调用日志接口
            if(log!=null){
                optionLogFeginService.addOptionLog(optionLog);
            }
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            optionLog.setExeLog(throwable.getMessage());
        }
        //调用日志接口
        optionLogFeginService.addOptionLog(optionLog);
        return null;
    }
}
