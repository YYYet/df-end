package com.ruoyi.project.system.domain;

import com.ruoyi.common.utils.cloud.pojo.CloudSession;
import lombok.Data;

@Data
public class CloudUser {
    private CloudSession cloudSession;
    private String userName;
    private String loginName;
    private String phone;
    private String email;
    private String joinDate;
    private String useOrgId;
    private String userNo;

}
