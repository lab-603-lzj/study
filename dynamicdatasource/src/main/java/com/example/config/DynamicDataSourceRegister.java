package com.example.config;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * 注册数据源
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    // 数据源
    private DataSource defaultDataSource;
    private Map<String,DataSource> customDataSource = new HashMap<>();


    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }

    // 设置主数据源
    private void initDefaultDataSource(Environment env) {
        Map<String, String> dsMap = new HashMap<>();
        dsMap.put("driver", env.getProperty("spring.datasource.driver-class-name"));
        dsMap.put("url", env.getProperty("spring.datasource.url"));
        dsMap.put("username", env.getProperty("spring.datasource.username"));
        dsMap.put("password", env.getProperty("spring.datasource.password"));
        defaultDataSource = buildDataSource(dsMap);
    }

    // 设置从数据源
    private void initCustomDataSources(Environment env) {
        String dsPrefixes = env.getProperty("slave.datasource.names");
        for (String dsPrefix:dsPrefixes.split(",")){
            Map<String,String> dsMap = new HashMap<>();
            dsMap.put("driver",env.getProperty("slave.datasource." + dsPrefix + ".driver"));
            dsMap.put("url", env.getProperty("slave.datasource." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("slave.datasource." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("slave.datasource." + dsPrefix + ".password"));
            customDataSource.put(dsPrefix,buildDataSource(dsMap));
        }
    }


    // 绑定更多数据源更多信息
    private void dataBinder(DataSource dataSource,Environment env){
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("开始注册数据源。");

        Map<String,Object> dataSources = new HashMap<>();

        // 添加主数据源
        dataSources.put("dataSource",this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");

        // 添加其他数据源
        dataSources.putAll(customDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.addAll(customDataSource.keySet());

        // 创建多数据源
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource",defaultDataSource);
        mpv.addPropertyValue("targetDataSources",dataSources);
        // 注册多数据源
        registry.registerBeanDefinition("DataSource",beanDefinition);

        System.out.println("多数据源注册完成。");
    }

    @SuppressWarnings("uncheck")
    public DataSource buildDataSource(Map<String,String> dsMap)  {
        try {
            String type = dsMap.get("type");
            if (type ==null){
                type = DATASOURCE_TYPE_DEFAULT;
            }

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName(type);

            String driverClassName = dsMap.get("driver");
            String url = dsMap.get("url");
            String username = dsMap.get("username");
            String password = dsMap.get("password");
            DataSourceBuilder builder = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return builder.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
