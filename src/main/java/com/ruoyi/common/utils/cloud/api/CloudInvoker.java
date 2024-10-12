package com.ruoyi.common.utils.cloud.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.cloud.consts.CloudWebAPI;
import com.ruoyi.common.utils.cloud.consts.CloudWebAPIEx;
import com.ruoyi.common.utils.cloud.pojo.CloudLoginInfo;
import com.ruoyi.common.utils.cloud.pojo.CloudSession;
import com.ruoyi.common.utils.cloud.util.CloudDataSource;
import com.ruoyi.common.utils.cloud.pojo.CloudContext;
import com.ruoyi.common.utils.cloud.pojo.DataCenter;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @describe cloud连接工具
 * @author shenkai
 * @date 2022/10/13
 */
public class CloudInvoker {
    private CloudDataSource dataSource;
    private CloudConnect connect;

    private CloudContext context;

    public CloudInvoker(CloudDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CloudConnect getConnect() {
        if(connect == null) {
            connect = new CloudConnect(dataSource.getUrl());
        }
        return connect;
    }

    public CloudContext getContext() {
        return context;
    }

    /**
     * 登陆
     */
    public void login() throws JsonProcessingException {
        if(getConnect().isLogin()) {
            return;
        }
        connect = new CloudConnect(dataSource.getUrl());
        String res = "";
        if(StringUtils.hasText(dataSource.getPassword())) {
            res = connect.login(dataSource.getDbId(), dataSource.getLoginName(), dataSource.getPassword());
        } else {
            res = connect.login(dataSource.getDbId(), dataSource.getLoginName(), dataSource.getAppId(), dataSource.getAppSecret());
        }
        //解析登录
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = mapper.readValue(res, Map.class);
        if(Long.valueOf(json.get("LoginResultType").toString()) == 1) {
            Map<String, Object> cx = (Map<String, Object>) json.get("Context");
            context = new CloudContext();
            context.setUserId(Integer.valueOf(cx.get("UserId").toString()));
            context.setUserName(cx.get("UserName").toString());
        } else {
            throw new RuntimeException(json.get("Message").toString());
        }
    }

    public CloudLoginInfo singleLogin() throws JsonProcessingException {
        CloudLoginInfo cloudLoginInfo = new CloudLoginInfo();

        CloudConnect connect = new CloudConnect(dataSource.getUrl());
        cloudLoginInfo.setCloudConnect(connect);

        String res = "";
        if(StringUtils.hasText(dataSource.getPassword())) {
            res = connect.login(dataSource.getDbId(), dataSource.getLoginName(), dataSource.getPassword());
        } else {
            res = connect.login(dataSource.getDbId(), dataSource.getLoginName(), dataSource.getAppId(), dataSource.getAppSecret());
        }
        //解析登录
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = mapper.readValue(res, Map.class);
        if(Long.valueOf(json.get("LoginResultType").toString()) == 1) {
            Map<String, Object> cx = (Map<String, Object>) json.get("Context");
            CloudContext context = new CloudContext();
            context.setUserId(Integer.valueOf(cx.get("UserId").toString()));
            context.setUserName(cx.get("UserName").toString());
            cloudLoginInfo.setCloudContext(context);
        } else {
            throw new RuntimeException(json.get("Message").toString());
        }

        return cloudLoginInfo;
    }

    /**
     * 根据cloud地址获取数据中心列表
     * @param cloudUrl cloud地址
     * @return
     */
    public static List<DataCenter> getDataCenterList(String cloudUrl) throws Exception {
        CloudConnect connect = new CloudConnect(cloudUrl);
        String result = connect.requestWebAPI(CloudWebAPI.DATACENTER.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        List<Object> list = mapper.readValue(result, List.class);
        List<DataCenter> dataCenterList = new ArrayList<DataCenter>();
        for(Object item : list) {
            Map<String, Object> d = (Map<String, Object>) item;
            DataCenter dataCenter = new DataCenter();
            dataCenter.setDbId(d.get("Id").toString());
            dataCenter.setNumber(d.get("Number").toString());
            dataCenter.setName(d.get("Name").toString());
            dataCenterList.add(dataCenter);
        }
        return dataCenterList;
    }

    /**
     * 获取设置的cloud数据中心列表
     * @return
     */
    public List<DataCenter> getDataCenterList() throws Exception {
        return getDataCenterList(dataSource.getUrl());
    }

    /**
     * 调用自定义webapi
     * @param api
     * @param data
     * @return
     */
    public String callCustom(String api, Object ...data) throws JsonProcessingException {
        this.login();
        String result = getConnect().requestWebAPI(api, data);
        return result;
    }

    public String execSql(String sql, CloudSession cloudSession) throws JsonProcessingException, UnsupportedEncodingException {
        byte[] bytes = sql.getBytes("UTF-8");
        String sqlText = Base64.getEncoder().encodeToString(bytes);
        String result = getConnect().requestWebAPIBySession(CloudWebAPIEx.SQL_QUERY.getUrl(), sqlText, cloudSession.getSessionValue(), cloudSession.getSessionValueAspnet());
        return result;
    }

    /**
     * 调用标准api
     * @param api api名称
     * @param formId 表单formId
     * @param data 数据
     * @return
     */
    public String callStandard(String api, String formId, Map<String, Object> data) throws JsonProcessingException {
        this.login();
        String result = getConnect().requestWebAPI2(api, formId, data);
        return result;
    }
}
