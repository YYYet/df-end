package com.ruoyi.common.utils.cloud.pojo;

/**
 * @describe 金蝶云星空登录上下文
 * @author shenkai
 * @date 2022/10/18
 */
public class CloudContext {
    /**
     * 用户id
     */
    private int userId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }
}
