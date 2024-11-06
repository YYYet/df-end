package com.ruoyi.project.system.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface OrderTemplateService {
    public List<Map<String, Object>> getTemplateListByUseOrgId(String useOrgId) throws Exception;
}
