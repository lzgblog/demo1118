package com.springbootdemo.demo1118;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(value="com.springbootdemo.demo1118.mapper")
public class Demo1118Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1118Application.class, args);
    }

}
