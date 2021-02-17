package com.hf.starterdemo.configuration;

import com.hf.starterdemo.properties.StarterDemoProperties;
import com.hf.starterdemo.spring02.SpringDemoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(SpringDemoService.class)
@EnableConfigurationProperties(StarterDemoProperties.class)
public class StarterDemoAutoConfiguration {

    @Resource
    private StarterDemoProperties starterDemoProperties;

    @Bean
    @ConditionalOnMissingBean(SpringDemoService.class)
    @ConditionalOnProperty(prefix = "start-demo",value = "enabled",havingValue = "true")
    public SpringDemoService springDemoService(){
        SpringDemoService service = new SpringDemoService();
        service.setParam(starterDemoProperties.getParam());
        return service;
    }
}
