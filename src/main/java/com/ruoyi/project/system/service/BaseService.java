package com.ruoyi.project.system.service;


import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.framework.config.KingdeeCloudConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseService {
    @Resource
    public K3CloudApi api;

    @Resource
    public CloudLoginUtil cloudLoginUtil;

    @Resource
    public KingdeeCloudConfig config;
}
