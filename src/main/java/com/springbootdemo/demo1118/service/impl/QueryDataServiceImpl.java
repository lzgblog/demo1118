package com.springbootdemo.demo1118.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springbootdemo.demo1118.mapper.DemoMapper;
import com.springbootdemo.demo1118.mapper.Dept;
import com.springbootdemo.demo1118.mapper.DeptMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author luzg
 * @date 2023-02-09 20:39
 */
@Component
public class QueryDataServiceImpl implements QueryDataService {
    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public String queryMap() {
        String[] arr = {"dept_no","dept_name","db_source"};
        List<String> list = Arrays.asList(arr);
        List<Map<String, Object>> maps = demoMapper.queryMapList(list);
        System.out.println(maps);
        return null;
    }

    public List<Dept> queryList() {

        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("dept_no", "6");
        Page<Dept> page = new Page<>();
        page.setCurrent(1);
        page.setSize(20);
        Page<Dept> depts = deptMapper.selectDeptList(page, queryWrapper);
        System.out.println(depts);
        return null;
    }

    @Override
    public long insertOne() {
        return demoMapper.insertOne(123l);
    }

    @Override
    public void updateData() {
        List<Map<String, Object>> mapList= new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("dept_no", 1);
        map.put("dept_name", "name1");
        map.put("db_source", "source1");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("dept_no", 2);
        map2.put("dept_name", null);
        map2.put("db_source", null);
        mapList.add(map);
        mapList.add(map2);
        for (Map<String, Object> m : mapList) {
            demoMapper.updateData(m, m.get("dept_no"));
        }
    }

}
