package com.springbootdemo.demo1118.thread;

/**
 * @author luzg
 * @date 2022-12-01 16:03
 */
public class PropertiesImpl implements PropertiesData {

    public static void main(String args[]) {
        PropertiesImpl properties = new PropertiesImpl();
        properties.test();
    }
    public void test(){
        System.out.println(PropertiesData.str);
    }
}
