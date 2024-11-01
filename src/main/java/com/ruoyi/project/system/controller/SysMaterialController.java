package com.ruoyi.project.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.common.utils.cloud.consts.SqlConst;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.domain.FilterTemplate;
import com.ruoyi.project.system.domain.Material;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/material")
public class SysMaterialController {

    @Resource
    K3CloudApi api;
    @Resource
    CloudLoginUtil cloudLoginUtil;


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
    @GetMapping("/getMaterialByTabV2")
    public AjaxResult getMaterialByTabV2(@RequestParam String groupId, @RequestParam Integer page,
                                       @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        List<Map<String, Object>> maps = new ArrayList<>();
        CloudUser user = (CloudUser)session.get("user");
//        String formatSql = String.format(SqlConst.MATERIAL_LIMIT, user.getLoginName(), groupId, page, pageSize, pageSize);

        Map<String, Object> f = new HashMap<>();
        f.put("cardNo", user.getLoginName());
        f.put("useOrgId", user.getUseOrgId());
        f.put("groupId", groupId);
        f.put("pageNumber", page);
        f.put("pageSize", pageSize);
        String formatSql = StrUtil.format(SqlConst.MATERIAL_LIMIT, f);
        maps = cloudLoginUtil.execSql(formatSql);

        ajax.put("result", maps);
        return ajax;
    }
    @SaCheckLogin
    @GetMapping("/getMaterialByNameV2")
    public AjaxResult getMaterialByNameV2(@RequestParam String name, @RequestParam Integer page,
                                        @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        List<Map<String, Object>> maps = new ArrayList<>();
        CloudUser user = (CloudUser)session.get("user");
//        String formatSql = String.format(SqlConst.MATERIAL_LIMIT, user.getLoginName(), groupId, page, pageSize, pageSize);

        Map<String, Object> f = new HashMap<>();
        f.put("cardNo", user.getLoginName());
        f.put("useOrgId", user.getUseOrgId());
        f.put("likeTxt", name);
        f.put("pageNumber", page);
        f.put("pageSize", pageSize);
        String formatSql = StrUtil.format(SqlConst.MATERIAL_LIMIT_MATCH_NAME, f);
        maps = cloudLoginUtil.execSql(formatSql);

        ajax.put("result", maps);
        return ajax;
    }

    @SaCheckLogin
    @GetMapping("/getMaterialAddedV2")
    public AjaxResult getMaterialAddedV2(@RequestParam Integer page,
                                          @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        List<Map<String, Object>> maps = new ArrayList<>();
        CloudUser user = (CloudUser)session.get("user");
//        String formatSql = String.format(SqlConst.MATERIAL_LIMIT, user.getLoginName(), groupId, page, pageSize, pageSize);

        Map<String, Object> f = new HashMap<>();
        f.put("cardNo", user.getLoginName());
        f.put("useOrgId", user.getUseOrgId());
        f.put("pageNumber", page);
        f.put("pageSize", pageSize);
        String formatSql = StrUtil.format(SqlConst.MATERIAL_AREADY_ADDED, f);
        maps = cloudLoginUtil.execSql(formatSql);

        ajax.put("result", maps);
        return ajax;
    }


    @SaCheckLogin
    @PostMapping("/addOrUpDateShopV2")
    public AjaxResult addOrUpDateShopV2(@RequestBody Material material) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");

        Map<String, Object> f = new HashMap<>();
        f.put("cardNo", user.getLoginName());
        f.put("materialId", material.getId());
        String formatSql = StrUtil.format(SqlConst.SHOR_CART_DELETE, f);
        cloudLoginUtil.execSqlNoReturn(formatSql);
//        {billNo}, 'C', {userId}, {nowDate}, {cardNo}, {materialId}, {nums}

        f.put("billNo", RandomUtil.randomNumbers(11));
        f.put("billId", RandomUtil.randomNumbers(11));
        f.put("userId", user.getId());
        f.put("nowDate", DateUtil.now());
        f.put("cardNo", user.getLoginName());
        f.put("materialId", material.getId());
        f.put("nums", material.getNums());
        formatSql = StrUtil.format(SqlConst.SHOR_CART_INSERT, f);
        cloudLoginUtil.execSqlNoReturn(formatSql);
        ajax.put("result", "ok");
        return ajax;
    }
    @SaCheckLogin
    @GetMapping("/clearShopV2")
    public AjaxResult clearShopV2() throws Exception {
        AjaxResult ajax = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");

        Map<String, Object> f = new HashMap<>();
        f.put("cardNo", user.getLoginName());
        String formatSql = StrUtil.format(SqlConst.SHOR_CART_CLEAR, f);
        cloudLoginUtil.execSqlNoReturn(formatSql);

        ajax.put("result", "ok");
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
