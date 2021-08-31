package com.example.config;

import java.util.ArrayList;
import java.util.List;


/**
 * 上下文环境管理
 */
public class DynamicDataSourceContextHolder {
    // ThreadLocal线程内部的存储类，属于线程内私有的数据
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static List<String> dataSourceIds = new ArrayList<>();

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    public static void clearDataSourceType(){
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
