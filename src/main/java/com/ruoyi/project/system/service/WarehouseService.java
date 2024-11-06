package com.ruoyi.project.system.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.servers.Server;


public interface WarehouseService {
    public JSONArray getWarehouseByOrgId(String orgId) throws Exception;
}
