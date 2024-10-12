package com.ruoyi.common.utils.cloud.pojo;


import com.ruoyi.common.utils.cloud.util.CloudDataSource;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CloudSession {
 private String SessionValue;
 private String SessionValueAspnet;
 private CloudDataSource cloudDataSource;

}
