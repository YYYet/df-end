package com.ruoyi.project.system.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ruoyi.project.system.service.BaseService;
import com.ruoyi.project.system.service.OrgService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrgServiceImpl extends BaseService implements OrgService {
    @Override
    public JSONArray getAllOrg() throws Exception {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("FormId", "KCY_SCM_OrderTemplet");
//        map.put("FieldKeys", "FNUMBER as number,FUSEORGID as useOrgId," +
//                "FNAME as name ");
//        map.put("FilterString", StrUtil.format("FUSEORGID = '{}' and FDOCUMENTSTATUS = 'C' and FFORBIDSTATUS = 'A'", useOrgId));
//        String result = api.billQuery(JSONUtil.toJsonStr(map));
//        JSON entries = JSONUtil.parse(result);
        return null;
    }
}
