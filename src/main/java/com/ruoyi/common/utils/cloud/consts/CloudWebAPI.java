package com.ruoyi.common.utils.cloud.consts;

import com.ruoyi.common.utils.cloud.consts.System;

/**
 * @describe WebApi常量
 * @author shenkai
 * @date 2022/10/13
 */
public enum CloudWebAPI {

    /**
     * 第三方登录
     */
    APPLOGIN("Kingdee.BOS.WebApi.ServicesStub.AuthService.LoginByAppSecret.common.kdsvc"),

    /**
     * 用户登陆
     */
    USERLOGIN("Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc"),

    /**
     * 数据中心列表
     */
    DATACENTER("Kingdee.BOS.ServiceFacade.ServicesStub.Account.AccountService.GetDataCenterList.common.kdsvc"),

    /**
     * 查询单据
     */
    QUERY("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc"),

    /**
     * 查询单据
     */
    VIEW("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.View.common.kdsvc"),

    /**
     * 元数据查询
     */
    QUERYBUSINESSINFO("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.QueryBusinessInfo.common.kdsvc"),

    /**
     * 下推
     */
    PUSH("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Push.common.kdsvc"),

    /**
     * 保存
     */
    SAVE("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc"),

    /**
     * 提交
     */
    SUBMIT("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Submit.common.kdsvc"),

    /**
     * 审核
     */
    AUDIT("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc"),

    /**
     * 反审核
     */
    UNAUDIT("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.UnAudit.common.kdsvc"),

    /**
     * 删除
     */
    DELETE("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Delete.common.kdsvc");

    private String url;

    private CloudWebAPI(String url) {
        this.setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
