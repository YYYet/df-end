package com.ruoyi.project.system.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.project.system.service.WarehouseService;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service()
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    K3CloudApi api;

    @Resource
    CloudLoginUtil cloudLoginUtil;

    @Override
    public JSONArray getWarehouseByOrgId(String orgId) throws Exception {
        HashMap map = new HashMap();
        map.put("FormId", "BD_STOCK");
        map.put("FieldKeys", "FNUMBER as number,FUSEORGID as useOrgId," +
                "FNAME as name, FDOCUMENTSTATUS as status");
        map.put("FilterString", StrUtil.format("FUSEORGID = '{}' and FDOCUMENTSTATUS = 'C'", orgId));

//        map.put("StartRow", (page-1)*pageSize);
//        map.put("Limit", pageSize);
        String result = api.billQuery(JSONUtil.toJsonStr(map));
        JSON entries = JSONUtil.parse(result);
        JSONArray objects = JSONUtil.parseArray(entries);
        return objects;
    }
}
