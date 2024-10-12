package com.ruoyi.common.utils.cloud.util;

import com.ruoyi.common.utils.cloud.util.DataSource;
import org.springframework.util.StringUtils;

/**
 * @describe cloud数据源
 * @author shenkai
 * @date 2022/12/3
 */
public class CloudDataSource extends DataSource {

    /**
     * 地址
     */
    private String url;

    /**
     * 第三方应用id
     */
    private String appId;

    /**
     * 第三方应用密钥
     */
    private String appSecret;

    /**
     * 数据中心
     */
    private String dbId;

    /**
     * 集成用户
     */
    private String loginName;

    /**
     * 指定数据中心
     */
    private boolean setDataCenter;

    /**
     * 是否第三方登录
     */
    private boolean thirdParty;

    /**
     * 密码
     */
    private String password;

    public String getUrl() {
        return url;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDbId() {
        return dbId;
    }

    public void setSetDataCenter(boolean setDataCenter) {
        this.setDataCenter = setDataCenter;
    }

    public boolean isSetDataCenter() {
        return setDataCenter;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setThirdParty(boolean thirdParty) {
        this.thirdParty = thirdParty;
    }

    public boolean isThirdParty() {
        return thirdParty;
    }
}
