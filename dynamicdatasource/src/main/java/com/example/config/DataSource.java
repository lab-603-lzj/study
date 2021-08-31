package com.example.config;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD}) // 目标为类，接口或者方法
@Retention(RetentionPolicy.RUNTIME)  // 保存到运行时
@Documented // javadoc中显示注解信息
public @interface DataSource {
    String name();
}
