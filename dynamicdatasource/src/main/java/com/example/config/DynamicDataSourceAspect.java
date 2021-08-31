package com.example.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 注解的切点使用规则
 */
@Aspect
@Order(-1) // 确保在事务接口之前执行
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint joinPoint,DataSource dataSource){
        String dbid = dataSource.name();

        System.out.println(joinPoint.toString());
        System.out.println(joinPoint.getArgs());
        System.out.println(joinPoint.getKind());
        System.out.println(joinPoint.getSourceLocation());
        System.out.println(joinPoint.getStaticPart());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getThis());
        if (!DynamicDataSourceContextHolder.containsDataSource(dbid)){
            System.out.println("数据源"+dbid+"不存在，使用默认数据源——>"+joinPoint.getSignature());
        }else {
            System.out.println("使用数据源："+dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }
    }

    @After("@annotation(dataSource)")
    public void clearDataSource(JoinPoint joinPoint,DataSource dataSource){
        System.out.println("清除数据源。");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
