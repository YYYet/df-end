package com.ruoyi.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kingdee")
@Data
public class KingdeeCloudConfig {
    public String url;
    public String dbId;
    public String username;
    public String password;
}
