package com.springbootdemo.demo1118.service.impl;

import com.springbootdemo.demo1118.mapper.Dept;

import java.util.List;

/**
 * @author luzg
 * @date 2023-02-09 20:38
 */
public interface QueryDataService {
    String queryMap();
    long insertOne();

    void updateData();

    List<Dept> queryList();
}
