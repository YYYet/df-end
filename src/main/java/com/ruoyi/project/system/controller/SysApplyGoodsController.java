package com.ruoyi.project.system.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.BillStatus;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.domain.Page;
import com.ruoyi.project.system.service.impl.OrderTemplateServiceImpl;
import com.ruoyi.project.system.service.impl.WarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/applyGoods")
public class SysApplyGoodsController {
    @Resource
    K3CloudApi api;

    @Resource
    CloudLoginUtil cloudLoginUtil;




    @GetMapping("page")
    public AjaxResult getApplyGood(@RequestParam String status, @RequestParam Integer page,
                                   @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();

        String description = BillStatus.fromDescription(status).getIdentifier();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        String totalSql = "select count(*) as total " +
                "from T_SCMS_ApplyGools where FAPPLICATIONORGID = "+user.getUseOrgId();
        HashMap map = new HashMap();
        map.put("FormId", "DE_SCMS_ApplyGools");
        map.put("FieldKeys", "FID as id,FBILLNO as billNumber,FAPPLICATIONORGID.fname as applyOrgName," +
                "FDOCUMENTSTATUS as status,FARRIVALDATE as arrivalDate, FRECEIVEORGID.fname as orderOrg, " +
                "FDISPATCHORGIDDETAIL.fname as distributionOrg");
        if (description.isEmpty()){
            map.put("FilterString", "FSeq = 1 and FAPPLICATIONORGID = "+user.getUseOrgId());
        }else {
            map.put("FilterString", "FSeq = 1 and FAPPLICATIONORGID = "+user.getUseOrgId()+" and "+description);
            totalSql = totalSql+ "and "+description;
        }
        map.put("StartRow", (page-1)*pageSize);
        map.put("Limit", pageSize);
        List<Map<String, Object>> maps = cloudLoginUtil.execSql(totalSql);
        Integer total = Integer.valueOf(maps.get(0).get("total").toString());
        String result = api.billQuery(JSONUtil.toJsonStr(map));
        JSON entries = JSONUtil.parse(result);
        JSONArray objects = JSONUtil.parseArray(entries);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            jsonObject.set("extra","货品订货-特殊请购\n\r (7:00-22:00)");
//            jsonObject.set("arrivalDate","10/16");
            jsonObject.set("arrivalTime","0:00");
            jsonObject.set("agent","18888888888");
            jsonObject.set("distributionCenter","配送中心");
            jsonObject.set("orderWarehouse","山爸爸德清店");
        }

        Page build = Page.builder()
                .data(objects)
                .total(total)
                .build();

        ajax.put("result", build);
        return ajax;
    }

    @GetMapping("queryBill")
    public AjaxResult queryBill(@RequestParam String billNumber) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        HashMap map = new HashMap();
        map.put("Number", billNumber);
        String result = api.view("DE_SCMS_ApplyGools", JSONUtil.toJsonStr(map));
        JSON entries = JSONUtil.parse(result);
        JSONArray objects = JSONUtil.parseArray(entries);
        JSONObject bill = objects.getJSONObject(0).getJSONObject("Result").getJSONObject("Result");
        System.out.println(bill);
        JSONArray entry = bill.getJSONArray("FEntity");
        JSONObject newBill = new JSONObject();
        newBill.set("extra","货品订货-特殊请购\n\r (7:00-22:00)");

        newBill.set("arrivalTime","0:00");
        newBill.set("agent","18888888888");
        newBill.set("distributionCenter","配送中心");
        newBill.set("orderWarehouse","山爸爸德清店");
        newBill.set("orderOrg",bill.getJSONObject("ReceiveOrgId").getJSONArray("Name").getJSONObject(0).getStr("Value"));
        newBill.set("distributionOrg", entry.getJSONObject(0).getJSONObject("FDispatchOrgIdDetail").getJSONArray("Name").getJSONObject(0).getStr("Value"));
        newBill.set("billNumber", bill.getStr("BillNo"));
        newBill.set("applyOrgName", bill.getJSONObject("ApplicationOrgId").getJSONArray("Name").getJSONObject(0).getStr("Value"));
        newBill.set("status",  bill.getStr("DocumentStatus"));
        newBill.set("createDate", bill.getDate("CreateDate"));
        newBill.set("creator", bill.getJSONObject("CreatorId").getStr("Name"));
        JSONArray newEntry = new JSONArray();
        for (int i = 0; i < entry.size(); i++) {
            JSONObject item = entry.getJSONObject(i);
            JSONObject newEntryObj = new JSONObject();
            newEntryObj.set("id", item.getInt("Id"));
            newEntryObj.set("unitName", item.getJSONObject("UnitID").getJSONArray("Name").getJSONObject(0).getStr("Value"));
            newEntryObj.set("materialName", item.getJSONObject("MaterialId").getJSONArray("Name").getJSONObject(0).getStr("Value"));
            newEntryObj.set("qty", item.getInt("ReqQty"));
            newEntryObj.set("arrivalDate", item.getDate("ArrivalDate"));
            newEntry.add(newEntryObj);
        }
        newBill.set("arrivalDate", newEntry.getJSONObject(0).getDate("arrivalDate"));
        newBill.set("entry", newEntry);
        ajax.put("result", newBill);
        return ajax;
    }




}
