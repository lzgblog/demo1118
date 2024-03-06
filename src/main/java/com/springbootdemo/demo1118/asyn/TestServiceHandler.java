package com.springbootdemo.demo1118.asyn;

import com.springbootdemo.demo1118.mapper.DemoMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author luzg
 * @date 2023-04-13 0:01
 */
public class TestServiceHandler implements QueueTaskHandler  {
    private String name;

    private Integer age;

    public TestServiceHandler(String name) {
        this.name = name;
    }

    public TestServiceHandler(Integer age) {
        this.age = age;
    }

    public TestServiceHandler(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // ****** end

    // 这里也就是我们实现QueueTaskHandler的处理接口
    @Override
    public void processData() {
        // 可以去做你想做的业务了
        // 这里需要引用spring的service的话，我写了一个工具类，下面会贴出来
        DemoMapper testService = SpringUtils.getBean(DemoMapper.class);
        String[] arr = {"dept_no","dept_name","db_source"};
        List<String> list = Arrays.asList(arr);
        List<Map<String, Object>> maps1 = testService.queryMapList(list);
        System.out.println(maps1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name > " + name + "," + "age > " + age);
    }
}
