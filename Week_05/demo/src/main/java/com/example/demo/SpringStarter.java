package com.example.demo;

import com.hf.starterdemo.spring02.SpringDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SpringStarter {

    @Resource
    private SpringDemoService springDemoService;

    @RequestMapping("/test")
    public String test(){
        springDemoService.getBeanInfo();
        return "success";
    }
}
