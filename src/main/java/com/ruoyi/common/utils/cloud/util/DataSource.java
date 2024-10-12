package com.ruoyi.common.utils.cloud.util;

import java.util.List;
import java.util.Map;

/**
 * @describe 数据源
 * @author shenkai
 * @date 2022/11/24
 */
public class DataSource {

    /**
     * 数据源id
     */
    private int dataSourceId;

    /**
     * 数据源类型
     */
    private String type;

    /**
     * 数据源编码
     */
    private String code;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 数据源内容
     */
    private String content;

    /********************** 扩展属性 **********************/
    /**
     * 数据源内容对象
     */
    private Map<String, Object> contentMap;

    /**
     * 可用入口
     */
    private List<String> entrances;

    public DataSource() {

    }

    public int getDataSourceId() {
        return dataSourceId;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }


    public String getContent() {
        return content;
    }

    public Map<String, Object> getContentMap() {
        return contentMap;
    }

    public void setDataSourceId(int dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentMap(Map<String, Object> contentMap) {
        this.contentMap = contentMap;
    }

    public void setEntrances(List<String> entrances) {
        this.entrances = entrances;
    }

    public List<String> getEntrances() {
        return entrances;
    }

}
