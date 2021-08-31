package com.example;

import com.example.config.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(value = "com/example/mapper")
@Import(DynamicDataSourceRegister.class)
public class DynamicApplication {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(DynamicApplication.class,args);
    }

}
