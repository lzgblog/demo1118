package com.springbootdemo.demo1118.service.impl;

import com.springbootdemo.demo1118.mapper.DemoMapper;
import com.springbootdemo.demo1118.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luzg
 * @date 2022-11-18 14:46
 */
@Component
public abstract class DemoServiceImpl implements DemoService {

    @Autowired
    public DemoMapper mapper;
    @Override
    public String list(String id) {
        String list = mapper.list(id);
        return list;
    }

}
