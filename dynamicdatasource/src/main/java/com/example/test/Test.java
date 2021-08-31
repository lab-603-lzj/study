package com.example.test;

import com.example.entity.TestDto;
import com.example.config.DataSource;
import com.example.mapper.TestMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class Test {

    @Autowired
    TestMapper testMapper;

    @GetMapping("/test")
    @DataSource(name = "tmp")
    public void test(){
        System.out.println(testMapper.test());
    }
    
}
