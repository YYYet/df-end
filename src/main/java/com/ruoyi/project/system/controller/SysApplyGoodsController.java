package com.ruoyi.project.system.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.framework.config.KingdeeCloudConfig;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.impl.OrderTemplateServiceImpl;
import com.ruoyi.project.system.service.impl.WarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/system/applyGoods")
public class SysApplyGoodsController {
    @Resource
    K3CloudApi api;

    @Resource
    CloudLoginUtil cloudLoginUtil;
    @Resource
    KingdeeCloudConfig config;



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
        map.put("OrderString", "FID desc");
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

    @GetMapping("conditionPage")
    public AjaxResult getApplyGoodByCondition(@RequestParam String condition,@RequestParam String status, @RequestParam Integer page,
                                   @RequestParam Integer pageSize) throws Exception {
        AjaxResult ajax = AjaxResult.success();

        String cd = new String(Base64.getDecoder().decode(condition));
        cd = URLDecoder.decode(cd, "UTF-8");
        if (StringUtils.isEmpty(cd)){
            return AjaxResult.error("条件不能为空");
        }
        System.out.println(cd);
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
            map.put("FilterString", "FAPPLICATIONORGID = "+user.getUseOrgId()+ " and "+cd);
        }else {
            map.put("FilterString", "FAPPLICATIONORGID = "+user.getUseOrgId()+" and "+description+ " and "+cd);
            totalSql = totalSql+ " and "+description;
        }
        map.put("StartRow", (page-1)*pageSize);
        map.put("OrderString", "FID desc");
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

    @PostMapping("auditBill")
    public AjaxResult auditBill(@RequestBody BillNumberList list) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        HashMap map = new HashMap();
        map.put("Numbers", list.getNumberList());
        String result = api.submit("DE_SCMS_ApplyGools", JSONUtil.toJsonStr(map));
        JSONObject submitResult = JSONUtil.parseObj(result);
        Boolean bool = submitResult.getJSONObject("Result").getJSONObject("ResponseStatus").getBool("IsSuccess");
        if (bool){
            System.out.println(result);
            api.audit("DE_SCMS_ApplyGools", JSONUtil.toJsonStr(map));
        }
        return ajax.put("result", result);
    }
    @PostMapping("unAuditBill")
    public AjaxResult unAuditBill(@RequestBody BillNumberList list) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        HashMap map = new HashMap();
        map.put("Numbers", list.getNumberList());
        String result = api.unAudit("DE_SCMS_ApplyGools", JSONUtil.toJsonStr(map));
        return ajax.put("result", result);
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
        newBill.set("note",bill.getStr("Note"));
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



    @PostMapping("saveBill")
    public AjaxResult save(@RequestBody FrontApplyGoodBill bill) throws Exception {
        AjaxResult success = AjaxResult.success();

        System.out.println("bill  "+bill);


        ArrayList<ApplyGoodsBillEntry> applyGoodsBillEntries = new ArrayList<>();

        ArrayList<FrontApplyGoodEntry> entry = bill.getEntry();
        for (FrontApplyGoodEntry item : entry) {
            if (item.getNums() == 0){
                continue;
            }
            System.out.println(item.toString());
            applyGoodsBillEntries.add(ApplyGoodsBillEntry.builder().FMaterialId(new BillNumberEntity(item.getNumber()))
                    .FUnitId(new BillNumberEntity(item.getUnitNumber()))
                    .FMaxPOQty(100000.0000000000)
                    .FMinPOQty(0.0)
                    .FIncreaseQty(0.0)
                    .FReqQty(item.getNums())
                    .FApproveQty(item.getNums())
                    .FLeadTime(0)
                    .FAuxQty(0.0)
                    // 建议配送日期
//                .FSuggestPurDate("2024-11-07 00:00:00")
                    .FExtAuxQty(0.0)
                    .FApplyBaseQty(item.getNums())
                    .FLowLimitePrice(0.0)
                    // 到货日期
                    .FArrivalDate(bill.getArrivalDate())
                    .FSrcBillTypeId("")
                    .FBaseUnitId(new BillNumberEntity(item.getUnitNumber()))
                    .FActualApproveQty(0.0)
                    .FActualApproveBaseQty(0.0)
                    .FJNSumQty(0.0)
                    .FAUXCDSumJNQty(0.0)
                    .FConversionRate(0.0)
                    .FSrcBillNo("")
                    // 配送组织
                    .FDispatchOrgIdDetail(new BillNumberEntity(config.getDistributionCenterCode()))
                    .FIsPurchase("0")
                    .FIsProduce("0")
                    .FDeliveryMaxQty(item.getNums())
                    .FBaseDeliveryMaxQty(item.getNums())
                    .FISPresent(false)
                    .FTaxPrice(0.0)
                    .FTaxAmount(0.0)
                    .FTaxRate(0.0)
                    .FAllAmount(0.0)
                    .FAllAmount_LC(0.0)
                    .FDetailIntax(false)
                    .FIsSelfPurchase("1")
                    .FOWNERTYPEID("BD_OwnerOrg")
                    .FOWNERID(new BillNumberEntity(config.getDistributionCenterCode()))
                    .FKEEPERTYPEID("BD_KeeperOrg")
                    .FKEEPERID(new BillNumberEntity(config.getDistributionCenterCode()))
                    .FExpectQty(0.0)
                    .FProductJNQty(0.0)
                    .FRowGoodsStatus("F")
                    .build());
        }





        ApplyGoodsModel model = ApplyGoodsModel.builder().FID(0).FBillTypeID(new BillNumberEntity("YH01_JGLYHSQ"))
                .FRequestType("Material")
                .FNote(bill.getNote())
                .FApplicationOrgId(new BillNumberEntity(bill.getApplyOrgNumber())).FCurrencyId(new BillNumberEntity("PRE001"))
                .FReceiveOrgId(new BillNumberEntity(bill.getReviceOrgNumber())).FAppDate(DateUtil.now()).FIsIncludedTax(false)
                .FIsOfflinePay(false).FMobileOrderType("1").FDeliveryControl(false).FIsAgentPurchase(false).FGoodsStatus("F")
                .FIsRushOrder(false).FEntity(applyGoodsBillEntries).build();







        ApplyGoodsBill applyGoodsBill = ApplyGoodsBill
                .builder()
                .IsDeleteEntry("true")
                .SubSystemId("")
                .IsVerifyBaseDataField("false")
                .IsEntryBatchFill("true")
                .ValidateFlag("true").NumberSearch("true")
                .IsAutoAdjustField("true").InterationFlags("").IgnoreInterationFlag("")
                .IsControlPrecision("false")
                .ValidateRepeatJson("false")
                .Model(model).build();


        System.out.println(JSONUtil.toJsonStr(applyGoodsBill));
        String deScmsApplyGools = api.save("DE_SCMS_ApplyGools", JSONUtil.toJsonStr(applyGoodsBill));
        JSONObject result = JSONUtil.parseObj(deScmsApplyGools);
        if (result.getJSONObject("Result").getJSONObject("ResponseStatus").getBool("IsSuccess")){
            success.put("result", result);
        }else {
            String error =  result.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors").toString();
            return AjaxResult.error(error);
        }


        return success;
    }

}
