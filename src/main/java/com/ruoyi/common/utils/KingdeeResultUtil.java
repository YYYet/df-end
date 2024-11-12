package com.ruoyi.common.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;

@Component
public class KingdeeResultUtil {
    public JSONObject getResponseStatus(String result){
        JSONObject resultObj = JSONUtil.parseObj(result);
        return resultObj.getJSONObject("Result").getJSONObject("ResponseStatus");
    }
}
