package com.ruoyi.common.utils.cloud.api;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.cloud.consts.CloudWebAPI;
import org.springframework.util.StringUtils;

import java.net.HttpCookie;
import java.util.*;

/**
 * @describe cloud连接工具
 * @author shenkai
 * @date 2022/10/13
 */
public class CloudConnect {
    private final int LOCALE_CN = 2052;
    private final String SESSION_KEY = "kdservice-sessionid";
    private final String SESSION_KEY_ASP = "ASP.NET_SessionId";
    private String sessionValue = "";
    private String sessionValue_aspnet = "";
    private String cloud_url = "";

    public CloudConnect(String url) {
        this.cloud_url = url;
    }

    public CloudConnect(String cloud_url,String sessionValue, String sessionValue_aspnet) {
        this.sessionValue = sessionValue;
        this.sessionValue_aspnet = sessionValue_aspnet;
        this.cloud_url = cloud_url;
    }

    public int getLOCALE_CN() {
        return LOCALE_CN;
    }

    public String getSESSION_KEY() {
        return SESSION_KEY;
    }

    public String getSESSION_KEY_ASP() {
        return SESSION_KEY_ASP;
    }

    public String getSessionValue() {
        return sessionValue;
    }

    public String getSessionValue_aspnet() {
        return sessionValue_aspnet;
    }

    public String getCloud_url() {
        return cloud_url;
    }

    public void setSessionValue(String sessionValue) {
        this.sessionValue = sessionValue;
    }

    public void setSessionValue_aspnet(String sessionValue_aspnet) {
        this.sessionValue_aspnet = sessionValue_aspnet;
    }

    /**
     * 是否已经登录
     * @return
     */
    public Boolean isLogin() {
        if(StringUtils.hasText(sessionValue)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户名密码登录
     * @param acctId 账套id
     * @param userName 用户名
     * @param password 密码
     */
    public String login(String acctId, String userName, String password) {
        System.out.println("登录星空");
        try {
            String url = cloud_url + "/" + CloudWebAPI.USERLOGIN.getUrl();

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("acctID", acctId);
            data.put("userName", userName);
            data.put("password", password);
            data.put("lcid", LOCALE_CN);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);

            HttpResponse res = HttpRequest.post(url).body(json).execute();
            loginBack(res);
            return res.body();
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 第三方集成登陆
     * @param acctId 账套id
     * @param userName 用户名
     * @param appId 应用id
     * @param appSecret 应用密钥
     * @throws Exception
     */
    public String login(String acctId, String userName, String appId, String appSecret) {
        try {
            String url = cloud_url + "/" + CloudWebAPI.APPLOGIN.getUrl();

            Map<String, Object> data = new HashMap<>();
            data.put("acctID", acctId);
            data.put("userName", userName);
            data.put("appId", appId);
            data.put("appSecret", appSecret);
            data.put("lcid", LOCALE_CN);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            HttpResponse res = HttpRequest.post(url).body(json).execute();
            loginBack(res);
            return res.body();
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 请求登录和自定义webapi
     * @param webapi
     * @param params
     * @return
     * @throws Exception
     */
    public String requestWebAPI(String webapi, Object ...params) {
        try {
            //session
            Map<String, String> header = new HashMap<String, String>();
            header.put(SESSION_KEY, sessionValue);
            header.put(SESSION_KEY_ASP, sessionValue_aspnet);

            String url = cloud_url + "/" + webapi;

            Map<String, Object> data = new HashMap<>();
            data.put("format", 1);
            data.put("useragent", "ApiClient");
            data.put("rid", UUID.randomUUID().toString());
            data.put("parameters", params);
            data.put("timestamp", new Date().getTime());
            data.put("v", "1.0");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            HttpResponse res = HttpRequest.post(url).body(json).headerMap(header, false).execute();
            return res.body();
        } catch (Exception e) {

        }
        return "";
    }

    public String requestWebAPIBySession(String webapi, String sql, String sessionValue, String sessionValue_aspnet) {
        try {
            //session
            Map<String, String> header = new HashMap<String, String>();
            header.put(SESSION_KEY, sessionValue);
            header.put(SESSION_KEY_ASP, sessionValue_aspnet);

            String url = cloud_url + "/" + webapi;

            Map<String, Object> data = new HashMap<>();
            data.put("format", 1);
            data.put("useragent", "ApiClient");
            data.put("rid", UUID.randomUUID().toString());
            data.put("parameters", sql);
            data.put("timestamp", new Date().getTime());
            data.put("v", "1.0");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            HttpResponse res = HttpRequest.post(url).body(json).headerMap(header, false).execute();
            return res.body();
        } catch (Exception e) {

        }
        return "";
    }
    /**
     * 请求标准webapi
     * @param webapi
     * @param params
     * @return
     * @throws Exception
     */
    public String requestWebAPI2(String webapi, String formId, Object params) {
        try {
            //session
            Map<String, Object> header = new HashMap<String, Object>();
            header.put(SESSION_KEY, sessionValue);
            header.put(SESSION_KEY_ASP, sessionValue_aspnet);

            String url = cloud_url + "/" + webapi;

            Map<String, Object> data = new HashMap<>();
            data.put("formId", formId);
            data.put("data", params);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            String post = HttpUtil.post(url, json);
            return post;
        } catch (Exception e) {
        }
        return "";
    }
    private void loginBack(HttpResponse httpResult) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.readValue(httpResult.body(), Map.class);
        if(Integer.valueOf(result.get("LoginResultType").toString()) == 1) {//登陆成功
            System.out.println(httpResult.getCookies());
            sessionValue = httpResult.getCookieValue(SESSION_KEY);
            sessionValue_aspnet = httpResult.getCookieValue(SESSION_KEY_ASP);
        } else {

        }
    }
//    private void loginBack(HttpResult httpResult) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> result = mapper.readValue(httpResult.getResult(), Map.class);
//        if(Integer.valueOf(result.get("LoginResultType").toString()) == 1) {//登陆成功
//            //保存session
//            List<String> headers = httpResult.getHeader("Set-Cookie");
//            for (String head : headers) {
//                if (head.trim().startsWith(SESSION_KEY)) {
//                    int endIndex = head.indexOf(';');
//                    sessionValue = head.substring(20, endIndex);
//                } else if (head.trim().startsWith(SESSION_KEY_ASP)) {
//                    int endIndex = head.indexOf(';');
//                    sessionValue_aspnet = head.substring(18, endIndex);
//                }
//            }
//        } else {
//            throw new Exception(result.get("Message").toString());
//        }
//    }
}
