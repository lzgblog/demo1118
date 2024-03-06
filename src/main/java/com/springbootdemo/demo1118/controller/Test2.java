package com.springbootdemo.demo1118.controller;

import java.util.regex.Pattern;

/**
 * @author luzg
 * @date 2022-11-25 12:48
 */
public class Test2{


    public static void main(String[] args) {
        String REGEX = "[^0-9.]";
        String ticketStr = "Ark 98.5%";
        String ticket = Pattern.compile(REGEX).matcher(ticketStr).replaceAll("").trim();
        System.out.println(ticket);
        String replaceAll = ticketStr.replaceAll("[^0-9.]", "");
        System.out.println(replaceAll);
    }

}
