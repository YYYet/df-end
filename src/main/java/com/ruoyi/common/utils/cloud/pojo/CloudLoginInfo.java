package com.ruoyi.common.utils.cloud.pojo;

import com.ruoyi.common.utils.cloud.api.CloudConnect;
import lombok.Data;

@Data
public class CloudLoginInfo {
    private CloudContext cloudContext;
    private CloudConnect cloudConnect;
}
