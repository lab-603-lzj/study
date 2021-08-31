package com.example.mapper;

import com.example.entity.TestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TestMapper {


    //@Select("select user_id from t_user_role where user_id=${key} and role_id${condition}${value}")
    List<String> set(TestDto testDto);

    List<String> setfor(List<TestDto> dtoList);

    String test();
}
