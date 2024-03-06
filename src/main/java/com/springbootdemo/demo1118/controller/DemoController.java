package com.springbootdemo.demo1118.controller;

import com.springbootdemo.demo1118.asyn.QueueGenerationService;
import com.springbootdemo.demo1118.asyn.TestServiceHandler;
import com.springbootdemo.demo1118.nettyconfig.NettyHandler;
import com.springbootdemo.demo1118.service.DemoService;
import com.springbootdemo.demo1118.service.impl.QueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author luzg
 * @date 2022-11-18 14:28
 */
@Controller
public class DemoController{


    @Autowired
    private NettyHandler nettyHandler;
    @Autowired
    private QueryDataService queryDataService;

    @Autowired
    private QueueGenerationService queueGenerationService;
    @RequestMapping("")
    public String index(){
        //nettyHandler.channelSend("netty服务端发送消息");
        //queueGenerationService.addData(new TestServiceHandler("小明",5));
        //queryDataService.updateData();
        queryDataService.queryList();
        return "index.html";
    }
    @RequestMapping("/param/{id}")
    public String hasParam(@PathVariable("id") Integer id){
        System.out.println("带参数内容");
        return "index.html";
    }
    @RequestMapping("/list/{id}")
    @ResponseBody
    public String list(@PathVariable("id") String id){
        //String list = demoService.list(id);
        return "";
    }
   // @RequestMapping("/getName")
    //@ResponseBody
    //public String getName(@RequestParam("id") String id){
       // return demoService.list(id);
   // }

    @GetMapping("/queryMap")
    @ResponseBody
    public String queryMap(){
        return queryDataService.queryMap();
    }
    @GetMapping("/insert")
    public long insert(){
        return queryDataService.insertOne();
    }
}
