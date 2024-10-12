package com.ruoyi.common.utils.cloud.consts;

/**
 * @describe 自定义cloud接口
 * @author shenkai
 * @date 2023/3/10
 */
public enum CloudWebAPIEx {

    /**
     * sql查询
     * Unicre.DevCenter.CloudApi.Webapi.BaseService.Query,Unicre.DevCenter.CloudApi.common.kdsvc
     */
    SQL_QUERY("Unicre.DevCenter.CloudApi.Webapi.BaseService.Query," + System.PLUGIN_NAME + ".common.kdsvc"),

    /**
     * sql执行
     */
    SQL_EXECUTE("Unicre.DevCenter.CloudApi.Webapi.BaseService.Execute," + System.PLUGIN_NAME + ".common.kdsvc"),

    /**
     * 通用保存
     */
    COM_SAVE("Unicre.DevCenter.CloudApi.Webapi.BaseService.Save," + System.PLUGIN_NAME + ".common.kdsvc"),

    /**
     * 通用下推
     */
    COM_PUSH("Unicre.DevCenter.CloudApi.Webapi.BaseService.Push," + System.PLUGIN_NAME + ".common.kdsvc"),

    /**
     * 系统参数
     */
    COM_SYSPARAK("Unicre.DevCenter.CloudApi.Webapi.BaseService.GetSysParam," + System.PLUGIN_NAME + ".common.kdsvc");

    private String url;

    private CloudWebAPIEx(String url) {
        this.setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
