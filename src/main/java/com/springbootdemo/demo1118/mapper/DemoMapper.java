package com.springbootdemo.demo1118.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luzg
 * @date 2022-11-18 15:31
 */
@Mapper
@Repository
public interface DemoMapper {

    //@Select("select dept_name from dept where dept_no = 1")
    String list(@Param("id") String id);
    List<Map<String,Object>> queryMapList(@Param("list") List<String> list);
    long insertOne(@Param("deptNo") Long deptNo);

    void updateData(@Param("map") Map<String, Object> map, @Param("deptNo") Object deptNo);
}
