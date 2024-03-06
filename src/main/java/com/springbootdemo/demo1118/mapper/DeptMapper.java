package com.springbootdemo.demo1118.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author luzg
 * @date 2024-03-06 22:30
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    @Select("SELECT dept.dept_no,dept.dept_name,dept.db_source,demo.usr_id from dept dept LEFT JOIN demo_dept dd on dept.dept_no = dd.dept\n" +
            "LEFT JOIN t_demo demo on dd.demo = demo.id  ${ew.customSqlSegment}")
    Page<Dept> selectDeptList(Page<Dept> page, @Param("ew") Wrapper<Dept> queryWrapper);
}
