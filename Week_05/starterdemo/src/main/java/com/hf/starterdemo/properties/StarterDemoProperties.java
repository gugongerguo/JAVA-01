package com.hf.starterdemo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "start-demo")
@Data
public class StarterDemoProperties {

    // param第一个配置参数
    private String param;

}
