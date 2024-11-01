package com.ruoyi.framework.config;

import com.kingdee.bos.webapi.sdk.K3CloudApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KingdeeCloudBeanConfig {
    @Bean
    public K3CloudApi k3CloudApi(){
        return new K3CloudApi();
    }
}
