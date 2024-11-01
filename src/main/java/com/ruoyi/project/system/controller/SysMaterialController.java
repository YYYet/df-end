package com.ruoyi.project.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.domain.FilterTemplate;
import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/system/material")
public class SysMaterialController {

    @Resource
    K3CloudApi api;


    @SaCheckLogin
    @GetMapping("/getTabs")
    public AjaxResult getTabs() throws Exception {
        AjaxResult ajax = AjaxResult.success();
        var api = new K3CloudApi();
//        String json = "{\n" +
//                "    \"FormId\": \"SAL_MATERIALGROUP\",\n" +
//                "    \"FieldKeys\": \"fid,fname\",\n" +
//                "    \"OrderString\": \"\",\n" +
//                "    \"TopRowCount\": 0,\n" +
//                "    \"StartRow\": 0,\n" +
//                "    \"Limit\": 2000,\n" +
//                "    \"SubSystemId\": \"\"\n" +
//                "}";
        HashMap map = new HashMap();
        map.put("FormId", "SAL_MATERIALGROUP");
        map.put("FieldKeys", "fid as id,fname as name");

        String result = api.billQuery(JSONUtil.toJsonStr(map));

        ajax.put("result", JSONUtil.parse(result));
        return ajax;
    }

    @SaCheckLogin
    @GetMapping("/getMaterialByTab")
    public AjaxResult getMaterialByTab(@RequestParam String groupId, @RequestParam Integer page,
                                       @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();
//        var api = new K3CloudApi();
        CloudUser cloudUser =  (CloudUser)StpUtil.getSession().get("user");

        HashMap map = new HashMap();
        map.put("FormId", "BD_Material");
        map.put("FieldKeys", "FMATERIALID as id,FBaseUnitId.FNAME as unit,FMaterialGroup as groupId,FNumber as number,FName as name,FCreateOrgId as createOrgId,FUseOrgId as useOrgId");
        map.put("FilterString", "FUseOrgId='"+cloudUser.getUseOrgId()+"' and "+"FMaterialGroup='"+groupId+"'");

        map.put("StartRow", (page-1)*pageSize);
        map.put("Limit", pageSize);

        String result = api.billQuery(JSONUtil.toJsonStr(map));

        JSON entries = JSONUtil.parse(result);
        JSONArray objects = JSONUtil.parseArray(entries);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            jsonObject.set("nums", 0);
            jsonObject.set("price", 0);
            jsonObject.set("storageNums", 0);
            jsonObject.set("storageInTransitNums", 0);
            jsonObject.set("step", 1);
        }
        ajax.put("result", objects);
        return ajax;
    }

    @SaCheckLogin
    @GetMapping("/getMaterialByName")
    public AjaxResult getMaterialByName(@RequestParam String name, @RequestParam Integer page,
                                       @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();
//        var api = new K3CloudApi();
        CloudUser cloudUser =  (CloudUser)StpUtil.getSession().get("user");

        HashMap map = new HashMap();
        map.put("FormId", "BD_Material");
        map.put("FieldKeys", "FMATERIALID as id,FBaseUnitId.FNAME as unit,FMaterialGroup as groupId,FNumber as number,FName as name,FCreateOrgId as createOrgId,FUseOrgId as useOrgId");
        map.put("FilterString", "FUseOrgId='"+cloudUser.getUseOrgId()+"' and "+"FName like '%"+name+"%'");

        map.put("StartRow", (page-1)*pageSize);
        map.put("Limit", pageSize);

        String result = api.billQuery(JSONUtil.toJsonStr(map));

        JSON entries = JSONUtil.parse(result);
        JSONArray objects = JSONUtil.parseArray(entries);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            jsonObject.set("nums", 0);
            jsonObject.set("price", 0);
            jsonObject.set("storageNums", 0);
            jsonObject.set("storageInTransitNums", 0);
            jsonObject.set("step", 1);
        }
        ajax.put("result", objects);
        return ajax;
    }
}
