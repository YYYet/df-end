package com.ruoyi.common.utils.cloud.pojo;

/**
 * @describe cloud数据中心
 * @author shenkai
 * @date 2022/10/13
 */
public class DataCenter {
    private String dataSourceId;

    private String number;//数据中心代码

    private String name;//数据中心名称

    private String dbId;//数据中心id

    private String appId;//应用ID

    private String appSecret;//应用密钥

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
