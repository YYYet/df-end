package com.ruoyi.common.utils.cloud.util;

import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.cloud.api.CloudConnect;
import com.ruoyi.common.utils.cloud.api.CloudHelper;
import com.ruoyi.common.utils.cloud.consts.CloudWebAPIEx;
import com.ruoyi.common.utils.cloud.pojo.CloudLoginInfo;
import com.ruoyi.common.utils.cloud.pojo.CloudSession;
import com.ruoyi.common.utils.cloud.pojo.DataResult;
import com.ruoyi.framework.config.KingdeeCloudConfig;
import com.ruoyi.project.system.domain.CloudUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class CloudLoginUtil {
    @Resource
    KingdeeCloudConfig config;
    public CloudSession getCloudSession() throws JsonProcessingException {
        CloudDataSource source = new CloudDataSource();
        source.setUrl(config.getUrl());
        source.setDbId(config.getDbId());
        source.setLoginName(config.getUsername());
        source.setPassword(config.getPassword());

        //调用标准webapi
        CloudHelper cloud = new CloudHelper(source);
        CloudLoginInfo cloudLoginInfo = cloud.singleLogin();

        return CloudSession.builder()
                .SessionValueAspnet(cloudLoginInfo.getCloudConnect().getSessionValue_aspnet())
                .SessionValue(cloudLoginInfo.getCloudConnect().getSessionValue())
                .build();
    }
    public DataResult parseCustomResult(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map res = mapper.readValue(json, Map.class);
        if(Boolean.valueOf(res.get("isSuccess").toString())) {
            return DataResult.success(res.get("data"));
        } else {
            return DataResult.error(res.get("message").toString());
        }
    }
    public List<Map<String, Object>> execSql(String sql) throws UnsupportedEncodingException, JsonProcessingException {
        CloudDataSource source = new CloudDataSource();
        source.setUrl(config.getUrl());
        source.setDbId(config.getDbId());
        source.setLoginName(config.getUsername());
        source.setPassword(config.getPassword());
//        CloudHelper cloud = new CloudHelper(source);
        byte[] bytes = sql.getBytes("UTF-8");
        String sqlText = Base64.getEncoder().encodeToString(bytes);
        CloudSession cloudSession = (CloudSession)StpUtil.getSession().get("cloudSession");
        CloudConnect cloudConnect = new CloudConnect(source.getUrl(),
                cloudSession.getSessionValue(),
                cloudSession.getSessionValueAspnet());
        String result = cloudConnect.requestWebAPI(CloudWebAPIEx.SQL_QUERY.getUrl(), sqlText);

        DataResult dataResult =  parseCustomResult(result);
        if(dataResult.isSuccess()) {
            List<List<Map<String, Object>>> list = (List<List<Map<String, Object>>>)dataResult.getData();
            return list.get(0);
        } else {
            throw new RuntimeException(dataResult.getMessage());
        }

    }

}
