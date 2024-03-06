package com.springbootdemo.demo1118.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luzg
 * @date 2024-03-06 20:46
 */
@Getter
@Setter
@TableName("dept")
public class Dept {

    @TableField("dept_no")
    private int deptNo;

    @TableField("dept_name")
    private String deptName;

    @TableField("db_source")
    private String dbSource;

    @TableField(exist = false)
    private String usrId;
}
