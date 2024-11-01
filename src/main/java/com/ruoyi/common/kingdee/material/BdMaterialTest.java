package com.ruoyi.common.kingdee.material;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import cn.hutool.Hutool;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingdee.bos.webapi.entity.*;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.google.gson.Gson;
import com.ruoyi.common.kingdee.common.SeqHelper;
import com.ruoyi.common.kingdee.customer.BdCustomer;
import lombok.var;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BdMaterialTest {
    private static String FNumber = SeqHelper.genNumber("WL");
    private static String FName = "auwl_" + UUID.randomUUID().toString();
    private static String groupid = "";
    private static String materid = "";
    private static String fileid = "";

    /* 本接口用于实现物料 (BD_Material) 的保存功能 */
    @Test
    public void atestSaveMaterial() throws Exception {
        var api = new K3CloudApi(false);
        var c = new BdMaterial();
        String data = "{\"NeedUpDateFields\": [],\"NeedReturnFields\": [],\"IsDeleteEntry\": \"true\",\"SubSystemId\": \"\",\"IsVerifyBaseDataField\": \"false\",\"IsEntryBatchFill\": \"true\",\"ValidateFlag\": \"true\",\"NumberSearch\": \"true\",\"IsAutoAdjustField\": \"false\",\"InterationFlags\": \"\",\"IgnoreInterationFlag\": \"\",\"IsControlPrecision\": \"false\",\"ValidateRepeatJson\": \"false\",\"Model\": {\"FNumber\": "+"\""+FNumber+"\""+",\"FName\": "+"\""+FName+"\""+",\"FImgStorageType\": \"A\",\"FCreateOrgId\": {\"FNumber\": \"100\"},\"FUseOrgId\": {\"FNumber\": \"100\"},\"FSubHeadEntity\": {\"FTimeUnit\": \"H\"},\"SubHeadEntity\": {\"FErpClsID\": \"1\",\"FFeatureItem\": \"1\",\"FCategoryID\": {\"FNumber\": \"CHLB01_SYS\"},\"FTaxType\": {\"FNumber\": \"WLDSFL01_SYS\"},\"FTaxRateId\": {\"FNUMBER\": \"SL02_SYS\"},\"FBaseUnitId\": {\"FNumber\": \"Pcs\"},\"FIsPurchase\": true,\"FIsInventory\": true,\"FIsSale\": true,\"FWEIGHTUNITID\": {\"FNUMBER\": \"kg\"},\"FVOLUMEUNITID\": {\"FNUMBER\": \"m\"}},\"SubHeadEntity1\": {\"FStoreUnitID\": {\"FNumber\": \"Pcs\"},\"FUnitConvertDir\": \"1\",\"FIsLockStock\": true,\"FCountCycle\": \"1\",\"FCountDay\": 1,\"FCurrencyId\": {\"FNumber\": \"PRE001\"},\"FSNManageType\": \"1\",\"FSNGenerateTime\": \"1\"},\"SubHeadEntity2\": {\"FSaleUnitId\": {\"FNumber\": \"Pcs\"},\"FSalePriceUnitId\": {\"FNumber\": \"Pcs\"},\"FMaxQty\": 100000,\"FIsReturn\": true,\"FISAFTERSALE\": true,\"FISPRODUCTFILES\": true,\"FWARRANTYUNITID\": \"D\",\"FOutLmtUnit\": \"SAL\"},\"SubHeadEntity3\": {\"FPurchaseUnitId\": {\"FNumber\": \"Pcs\"},\"FPurchasePriceUnitId\": {\"FNumber\": \"Pcs\"},\"FQuotaType\": \"1\",\"FIsReturnMaterial\": true,\"FPOBillTypeId\": {\"FNUMBER\": \"CGSQD01_SYS\"},\"FPrintCount\": 1,\"FMinPackCount\": 1},\"SubHeadEntity4\": {\"FPlanningStrategy\": \"1\",\"FMfgPolicyId\": {\"FNumber\": \"ZZCL001_SYS\"},\"FFixLeadTimeType\": \"1\",\"FVarLeadTimeType\": \"1\",\"FCheckLeadTimeType\": \"1\",\"FOrderIntervalTimeType\": \"3\",\"FMaxPOQty\": 100000,\"FEOQ\": 1,\"FVarLeadTimeLotSize\": 1,\"FIsMrpComBill\": true,\"FReserveType\": \"1\",\"FCanDelayDays\": 999,\"FAllowPartDelay\": true,\"FPlanOffsetTimeType\": \"1\",\"FWriteOffQty\": 1},\"SubHeadEntity5\": {\"FProduceUnitId\": {\"FNumber\": \"Pcs\"},\"FProduceBillType\": {\"FNUMBER\": \"SCDD03_SYS\"},\"FOrgTrustBillType\": {\"FNUMBER\": \"SCDD06_SYS\"},\"FBOMUnitId\": {\"FNumber\": \"Pcs\"},\"FIssueType\": \"1\",\"FOverControlMode\": \"1\",\"FMinIssueQty\": 1,\"FMinIssueUnitId\": {\"FNUMBER\": \"Pcs\"},\"FStandHourUnitId\": \"3600\",\"FBackFlushType\": \"1\"},\"SubHeadEntity7\": {\"FSubconUnitId\": {\"FNumber\": \"Pcs\"},\"FSubconPriceUnitId\": {\"FNumber\": \"Pcs\"},\"FSubBillType\": {\"FNUMBER\": \"WWDD01_SYS\"}},\"FEntityAuxPty\": [{\"FAuxPropertyId\": {\"FNumber\": \"10\"}},{\"FAuxPropertyId\": {\"FNumber\": \"20\"}},{\"FAuxPropertyId\": {\"FNumber\": \"30\"}}],\"FEntityInvPty\": [{\"FInvPtyId\": {\"FNumber\": \"01\"},\"FIsEnable\": true},{\"FInvPtyId\": {\"FNumber\": \"02\"},\"FIsEnable\": true},{\"FInvPtyId\": {\"FNumber\": \"03\"}},{\"FInvPtyId\": {\"FNumber\": \"04\"}},{\"FInvPtyId\": {\"FNumber\": \"06\"}}]}}";
        String result = api.save("BD_Material",  data);
        var gson = new Gson();
        RepoRet sRet = gson.fromJson(result, RepoRet.class);
        if (sRet.isSuccessfully()) {
            materid = sRet.getResult().getId();
            System.out.printf("物料保存接口: %s%n", gson.toJson(sRet.getResult()));
        } else {
            fail("物料保存接口: " + gson.toJson(sRet.getResult()));
        }
    }


    /*本接口用于实现物料 (BD_Material) 的提交功能*/
    @Test
    public void btestSubmitMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{\"CreateOrgId\": 0,\"Numbers\": "+"\""+FNumber+"\""+" ,\"Ids\": \"\",\"SelectedPostId\": 0,\"NetworkCtrl\": \"\",\"IgnoreInterationFlag\": \"\"}";
        String result = api.submit("BD_Material", data);
        var gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料提交接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料提交接口: " + gson.toJson(repoRet.getResult()));
        }

    }

    /*本接口用于实现物料 (BD_Material) 的审核功能*/
    @Test
    public void ctestauditMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{\"CreateOrgId\": 0,\"Numbers\": ["+"\""+FNumber+"\""+"],\"Ids\": \"\",\"InterationFlags\": \"\",\"NetworkCtrl\": \"\",\"IsVerifyProcInst\": \"\",\"IgnoreInterationFlag\": \"\"}";
        String result = api.audit("BD_Material", data);
        var gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料审核接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料审核接口: "+gson.toJson(repoRet.getResult()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的分配功能*/
    @Test
    public void etestallocateMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{ \"PkIds\": "+materid+", \"TOrgIds\": \"100002\"}";
        String result = api.allocate("BD_Material", data);
        var gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料分配接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料分配接口: "+gson.toJson(repoRet.getResult()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的取消分配功能*/
    @Test
    public void ftestcancelAllocatetMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{ \"PkIds\": "+materid+", \"TOrgIds\": \"100002\"}";
        String result = api.cancelAllocate("BD_Material", data);
        var gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料取消分配接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料取消分配接口: "+gson.toJson(repoRet.getResult()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的反审核功能*/
    @Test
    public void gestunauditMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{\"CreateOrgId\": 0,\"Numbers\": ["+"\""+FNumber+"\""+"],\"Ids\": \"\",\"InterationFlags\": \"\",\"NetworkCtrl\": \"\",\"IsVerifyProcInst\": \"\",\"IgnoreInterationFlag\": \"\"}";
        String result = api.unAudit("BD_Material", data);
        var gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料反审核接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料反审核接口: " + gson.toJson(repoRet.getResult()));
        }
    }


    /*本接口用于实现物料 (BD_Material) 的附件上传功能*/
    @Test
    public void htestAttachmentUploadMaterial() throws Exception {
        var api = new K3CloudApi();
        String path = (new File(".")).getCanonicalPath() + "/image2.png";
        byte[] buffer = image2Bytes(path);
        String imgbase64 = new String(Base64.getEncoder().encode(buffer));
        String json = "{\"FileName\":\"1016.txt\",\"FEntryKey\":\"\",\"FormId\": \"BD_MATERIAL\",\"IsLast\": true,\"InterId\": "+materid+"," +
                "\"BillNO\": "+"\""+FNumber+"\""+",\"AliasFileName\": \"test\",\"SendByte\": \""+imgbase64+"\"}";
        String result = api.attachmentUpload(json);
        var gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            JsonElement jsondata = new JsonParser().parse(result);
            fileid = jsondata.getAsJsonObject().get("Result").getAsJsonObject().get("FileId").toString();
            System.out.printf("物料附件上传接口: %s%n", result);
        } else {
            fail("物料附件上传接口: " + result);
        }
    }

    private static byte[] image2Bytes(String imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
        return bytes;
    }

    /*本接口用于实现物料 (BD_Material) 的附件下载功能*/
    @Test
    public void itestattachmentDownLoadMaterial() throws Exception {
        var api = new K3CloudApi();
        String json = "{\"FileId\": "+fileid+", \"StartIndex\": 0}";
        String result = api.attachmentDownLoad(json);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料附件下载接口: %s%n", result);
        } else {
            fail("物料附件下载接口: " + result);
        }
    }


    /*本接口用于实现物料 (BD_Material) 的禁用功能*/
    @Test
    public void jtestForbidMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{\"CreateOrgId\": 0,\"Numbers\": ["+"\""+FNumber+"\""+"],\"Ids\": \"\",\"PkEntryIds\":[],\"NetworkCtrl\": \"\",\"IgnoreInterationFlag\": \"\"}";
        String result = api.excuteOperation("BD_Material", "Forbid", data);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料禁用接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料提交接口: " + gson.toJson(repoRet.getResult()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的反禁用功能*/
    @Test
    public void ktestenableMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{\"CreateOrgId\": 0,\"Numbers\": ["+"\""+FNumber+"\""+"],\"Ids\": \"\",\"PkEntryIds\":[],\"NetworkCtrl\": \"\",\"IgnoreInterationFlag\": \"\"}";
        String result = api.excuteOperation("BD_Material", "enable",data);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料反禁用接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料反禁用接口: " + gson.toJson(repoRet.getResult()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的查看功能*/
    @Test
    public void ltestviewMaterial() throws Exception {
        Properties properties = new Properties();
        File file = new File((new File("./src")).getCanonicalPath() + "/kdwebapi.properties");
        System.out.println("读取根目录下的配置文件->" + file.getPath());
        InputStream inputStream = new FileInputStream(file.getPath());
        properties.load(new InputStreamReader(inputStream, "utf-8"));
        inputStream.close();
        IdentifyInfo iden = new IdentifyInfo();
        iden.setUserName(properties.getProperty("X-KDApi-UserName"));
        iden.setAppId(properties.getProperty("X-KDApi-AppID"));
        iden.setdCID(properties.getProperty("X-KDApi-AcctID"));
        iden.setAppSecret(properties.getProperty("X-KDApi-AppSec"));
        iden.setlCID(2052);
        iden.setServerUrl(properties.getProperty("X-KDApi-ServerUrl"));
        K3CloudApi api = new K3CloudApi(iden);
        String data = "{\"CreateOrgId\": 0,\"Number\": "+"\""+FNumber+"\""+",\"Id\": \"\",\"IsSortBySeq\": \"false\"}";
        String result = api.view("BD_Material", data);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料查看接口: %s%n", result);
        } else {
            fail("物料查看接口: " + result);
        }
    }


    /*本接口用于实现物料 (BD_Material)的单据查询功能*/
    @Test
    public void mtestQueryMaterial2() throws Exception {
        var api = new K3CloudApi();
        var gson = new Gson();
        QueryParam<List<Map<String,Object>>> para = new QueryParam<>();
        para.setFormId("BD_Material");
        para.setFieldKeys("FMATERIALID,FNumber,FName,FCreateOrgId,FUseOrgId");
        Map<String,Object> map = Map.of("Left", "(", "FieldName", "FNumber", "Compare", "=",
                "Value", FNumber,"Right", ")","Logic", "and");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        para.setFilterString(list);
        System.out.println("list: " + gson.toJson(list));
        List result = api.executeBillQuery(para, BdCustomer.class);
        System.out.println("物料单据查询接口: " + gson.toJson(result));
    }

    /*本接口用于实现物料 (BD_Material)的单据查询功能*/
    @Test
    public void mtestQueryMaterial() throws Exception {
        var api = new K3CloudApi();
        String json = "{\n" +
                "    \"FormId\": \"BD_Material\",\n" +
                "    \"FieldKeys\": \"FMATERIALID,FMaterialGroup,FNumber,FName,FCreateOrgId,FUseOrgId,\",\n" +

                "    \"OrderString\": \"\",\n" +
                "    \"TopRowCount\": 0,\n" +
                "    \"StartRow\": 0,\n" +
                "    \"Limit\": 2000,\n" +
                "    \"SubSystemId\": \"\"\n" +
                "}";

        JSONObject obj = JSONUtil.createObj();
        obj.set("FormId", "BD_Material");
        obj.set("FieldKeys", "FMATERIALID,FMaterialGroup,FNumber,FName,FCreateOrgId,FUseOrgId");
        System.out.println(obj.toString());
        String result = String.valueOf(api.executeBillQuery(json));
        System.out.println("物料单据查询接口: " + result);
    }

    /*本接口用于实现物料 (BD_Material)的单据查询功能*/
    @Test
    public void mtestMaterialBillQuery() throws Exception {
        var api = new K3CloudApi();
        String json = "{\n" +
                "    \"FormId\": \"BD_Material\",\n" +
                "    \"FieldKeys\": \"FMATERIALID,FNumber,FName,FCreateOrgId,FUseOrgId,\",\n" +
                "    \"FilterString\": \"FNumber=\'" + FNumber + "\'\",\n" +
                "    \"OrderString\": \"\",\n" +
                "    \"TopRowCount\": 0,\n" +
                "    \"StartRow\": 0,\n" +
                "    \"Limit\": 2000,\n" +
                "    \"SubSystemId\": \"\"\n" +
                "}";
        String result = api.billQuery(json);
        System.out.println("物料单据查询接口: " + result);
    }
    @Test
    public void tttt() throws Exception {
        var api = new K3CloudApi();
        String json = "{\n" +
                "    \"FormId\": \"BD_Empinfo\",\n" +
                "    \"FieldKeys\": \"FNumber,FCARDNO,FPERSONID,FName,FCreateOrgId,FUseOrgId,\",\n" +
                "    \"OrderString\": \"\",\n" +
                "    \"TopRowCount\": 0,\n" +
                "    \"StartRow\": 0,\n" +
                "    \"Limit\": 2000,\n" +
                "    \"SubSystemId\": \"\"\n" +
                "}";
        String result = api.billQuery(json);
        System.out.println("物料单据查询接口: " + result);
    }


    @Test
    public void ss() throws Exception {
        var api = new K3CloudApi(false);
        var c = new BdMaterial();
//        String data =
//                "{\"NeedUpDateFields\": []," +
//                        "\"NeedReturnFields\": []," +
//                        "\"IsDeleteEntry\": \"true\"," +
//                        "\"SubSystemId\": \"\"," +
//                        "\"IsVerifyBaseDataField\": \"false\"," +
//                        "\"IsEntryBatchFill\": \"true\"," +
//                        "\"ValidateFlag\": \"true\"," +
//                        "\"NumberSearch\": \"true\"," +
//                        "\"IsAutoAdjustField\": \"false\"," +
//                        "\"InterationFlags\": \"\"," +
//                        "\"IgnoreInterationFlag\": \"\"," +
//                        "\"IsControlPrecision\": \"false\"," +
//                        "\"ValidateRepeatJson\": \"false\"," +
//                        "\"Model\": " +
//                        "{\"FNumber\": "+"\""+FNumber+"\""+
//                        ",\"FName\": "+"\""+FName+"\""+
//                        ",\"FImgStorageType\": \"A\"," +
//                        "\"FCreateOrgId\": " +
//                        "{\"FNumber\": \"100\"}," +
//                        "\"FUseOrgId\": " +
//                        "{\"FNumber\": \"100\"}," +
//                        "\"FSubHeadEntity\": " +
//                        "{\"FTimeUnit\": \"H\"}," +
//                        "\"SubHeadEntity\": " +
//                        "{\"FErpClsID\": \"1\"," +
//                        "\"FFeatureItem\": \"1\",\"FCategoryID\": {\"FNumber\": \"CHLB01_SYS\"},\"FTaxType\": {\"FNumber\": \"WLDSFL01_SYS\"},\"FTaxRateId\": {\"FNUMBER\": \"SL02_SYS\"},\"FBaseUnitId\": {\"FNumber\": \"Pcs\"},\"FIsPurchase\": true,\"FIsInventory\": true,\"FIsSale\": true,\"FWEIGHTUNITID\": {\"FNUMBER\": \"kg\"},\"FVOLUMEUNITID\": {\"FNUMBER\": \"m\"}},\"SubHeadEntity1\": {\"FStoreUnitID\": {\"FNumber\": \"Pcs\"},\"FUnitConvertDir\": \"1\",\"FIsLockStock\": true,\"FCountCycle\": \"1\",\"FCountDay\": 1,\"FCurrencyId\": {\"FNumber\": \"PRE001\"},\"FSNManageType\": \"1\",\"FSNGenerateTime\": \"1\"},\"SubHeadEntity2\": {\"FSaleUnitId\": {\"FNumber\": \"Pcs\"},\"FSalePriceUnitId\": {\"FNumber\": \"Pcs\"},\"FMaxQty\": 100000,\"FIsReturn\": true,\"FISAFTERSALE\": true,\"FISPRODUCTFILES\": true,\"FWARRANTYUNITID\": \"D\",\"FOutLmtUnit\": \"SAL\"},\"SubHeadEntity3\": {\"FPurchaseUnitId\": {\"FNumber\": \"Pcs\"},\"FPurchasePriceUnitId\": {\"FNumber\": \"Pcs\"},\"FQuotaType\": \"1\",\"FIsReturnMaterial\": true,\"FPOBillTypeId\": {\"FNUMBER\": \"CGSQD01_SYS\"},\"FPrintCount\": 1,\"FMinPackCount\": 1},\"SubHeadEntity4\": {\"FPlanningStrategy\": \"1\",\"FMfgPolicyId\": {\"FNumber\": \"ZZCL001_SYS\"},\"FFixLeadTimeType\": \"1\",\"FVarLeadTimeType\": \"1\",\"FCheckLeadTimeType\": \"1\",\"FOrderIntervalTimeType\": \"3\",\"FMaxPOQty\": 100000,\"FEOQ\": 1,\"FVarLeadTimeLotSize\": 1,\"FIsMrpComBill\": true,\"FReserveType\": \"1\",\"FCanDelayDays\": 999,\"FAllowPartDelay\": true,\"FPlanOffsetTimeType\": \"1\",\"FWriteOffQty\": 1},\"SubHeadEntity5\": {\"FProduceUnitId\": {\"FNumber\": \"Pcs\"},\"FProduceBillType\": {\"FNUMBER\": \"SCDD03_SYS\"},\"FOrgTrustBillType\": {\"FNUMBER\": \"SCDD06_SYS\"},\"FBOMUnitId\": {\"FNumber\": \"Pcs\"},\"FIssueType\": \"1\",\"FOverControlMode\": \"1\",\"FMinIssueQty\": 1,\"FMinIssueUnitId\": {\"FNUMBER\": \"Pcs\"},\"FStandHourUnitId\": \"3600\",\"FBackFlushType\": \"1\"},\"SubHeadEntity7\": {\"FSubconUnitId\": {\"FNumber\": \"Pcs\"},\"FSubconPriceUnitId\": {\"FNumber\": \"Pcs\"},\"FSubBillType\": {\"FNUMBER\": \"WWDD01_SYS\"}},\"FEntityAuxPty\": [{\"FAuxPropertyId\": {\"FNumber\": \"10\"}},{\"FAuxPropertyId\": {\"FNumber\": \"20\"}},{\"FAuxPropertyId\": {\"FNumber\": \"30\"}}],\"FEntityInvPty\": [{\"FInvPtyId\": {\"FNumber\": \"01\"},\"FIsEnable\": true},{\"FInvPtyId\": {\"FNumber\": \"02\"},\"FIsEnable\": true},{\"FInvPtyId\": {\"FNumber\": \"03\"}},{\"FInvPtyId\": {\"FNumber\": \"04\"}},{\"FInvPtyId\": {\"FNumber\": \"06\"}}]}}";
//
        String number = "";
        String group = "test02";
       String data = "{\n" +

               "    \"NeedUpDateFields\": [],\n" +
               "    \"IsAutoSubmitAndAudit\": \"true\",\n" +
               "    \"NeedReturnFields\": [],\n" +
               "    \"IsDeleteEntry\": \"true\",\n" +
               "    \"SubSystemId\": \"\",\n" +
               "    \"IsVerifyBaseDataField\": \"false\",\n" +
               "    \"IsEntryBatchFill\": \"true\",\n" +
               "    \"ValidateFlag\": \"true\",\n" +
               "    \"NumberSearch\": \"true\",\n" +
               "    \"IsAutoAdjustField\": \"true\",\n" +
               "    \"InterationFlags\": \"\",\n" +
               "    \"IgnoreInterationFlag\": \"\",\n" +
               "    \"IsControlPrecision\": \"false\",\n" +
               "    \"ValidateRepeatJson\": \"false\",\n" +
               "    \"Model\": {\n" +
               "        \"FMATERIALID\": 0,\n" +
               "        \"FCreateOrgId\": {\n" +
               "            \"FNumber\": \"100\"\n" +
               "        },\n" +
               "        \"FUseOrgId\": {\n" +
               "            \"FNumber\": \"100\"\n" +
               "        },\n" +
               "        \"FNumber\": \"%s\",\n" +
               "        \"FName\": \"%s\",\n" +
               "        \"FMaterialGroup\": {\n" +
               "            \"FNumber\": \"%s\"\n" +
               "        },\n" +
               "        \"FDSMatchByLot\": false,\n" +
               "        \"FImgStorageType\": \"A\",\n" +
               "        \"FIsSalseByNet\": false,\n" +
               "        \"FIndicatorClass\": {\n" +
               "            \"FNumber\": \"ZBFL01\"\n" +
               "        },\n" +
               "        \"FIsHandleReserve\": true,\n" +
               "        \"FOrderNumber\": 1000,\n" +
               "        \"FSubHeadEntity\": {\n" +
               "            \"FIsControlSal\": false,\n" +
               "            \"FLowerPercent\": 0.0,\n" +
               "            \"FUpPercent\": 0.0,\n" +
               "            \"FCalculateBase\": \"0\",\n" +
               "            \"FMaxSalPrice_CMK\": 0.0,\n" +
               "            \"FMinSalPrice_CMK\": 0.0,\n" +
               "            \"FIsAutoRemove\": false,\n" +
               "            \"FIsMailVirtual\": false,\n" +
               "            \"FIsFreeSend\": \"0\",\n" +
               "            \"FTimeUnit\": \"H\",\n" +
               "            \"FRentFreeDura\": 0.0,\n" +
               "            \"FPricingStep\": 0.0,\n" +
               "            \"FMinRentDura\": 0.0,\n" +
               "            \"FPriceType\": \"0\",\n" +
               "            \"FRentBeginPrice\": 0.0,\n" +
               "            \"FRentStepPrice\": 0.0,\n" +
               "            \"FDepositAmount\": 0.0,\n" +
               "            \"FLogisticsCount\": 0.0,\n" +
               "            \"FRequestMinPackQty\": 0.0,\n" +
               "            \"FMinRequestQty\": 0.0,\n" +
               "            \"FIsPrinttAg\": false,\n" +
               "            \"FIsAccessory\": false,\n" +
               "            \"FUploadSkuImage\": false\n" +
               "        },\n" +
               "        \"SubHeadEntity\": {\n" +
               "            \"FErpClsID\": \"1\",\n" +
               "            \"FFeatureItem\": \"1\",\n" +
               "            \"FCategoryID\": {\n" +
               "                \"FNumber\": \"CHLB01_SYS\"\n" +
               "            },\n" +
               "            \"FTaxType\": {\n" +
               "                \"FNumber\": \"WLDSFL01_SYS\"\n" +
               "            },\n" +
               "            \"FIsUnificationCD\": true,\n" +
               "            \"FIsDirectCD\": false,\n" +
               "            \"FTaxRateId\": {\n" +
               "                \"FNUMBER\": \"SL02_SYS\"\n" +
               "            },\n" +
               "            \"FBaseUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FIsPurchase\": true,\n" +
               "            \"FIsInventory\": true,\n" +
               "            \"FIsSubContract\": false,\n" +
               "            \"FIsSale\": true,\n" +
               "            \"FIsProduce\": false,\n" +
               "            \"FIsAsset\": false,\n" +
               "            \"FGROSSWEIGHT\": 0.0,\n" +
               "            \"FNETWEIGHT\": 0.0,\n" +
               "            \"FWEIGHTUNITID\": {\n" +
               "                \"FNUMBER\": \"kg\"\n" +
               "            },\n" +
               "            \"FLENGTH\": 0.0,\n" +
               "            \"FWIDTH\": 0.0,\n" +
               "            \"FHEIGHT\": 0.0,\n" +
               "            \"FVOLUME\": 0.0,\n" +
               "            \"FVOLUMEUNITID\": {\n" +
               "                \"FNUMBER\": \"m\"\n" +
               "            },\n" +
               "            \"FSuite\": \"0\",\n" +
               "            \"FCostPriceRate\": 0.0\n" +
               "        },\n" +
               "        \"SubHeadEntity1\": {\n" +
               "            \"FStoreUnitID\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FUnitConvertDir\": \"1\",\n" +
               "            \"FIsLockStock\": true,\n" +
               "            \"FIsCycleCounting\": false,\n" +
               "            \"FCountCycle\": \"1\",\n" +
               "            \"FCountDay\": 1,\n" +
               "            \"FIsMustCounting\": false,\n" +
               "            \"FIsBatchManage\": false,\n" +
               "            \"FIsKFPeriod\": false,\n" +
               "            \"FIsExpParToFlot\": false,\n" +
               "            \"FExpPeriod\": 0,\n" +
               "            \"FOnlineLife\": 0,\n" +
               "            \"FRefCost\": 0.0,\n" +
               "            \"FCurrencyId\": {\n" +
               "                \"FNumber\": \"PRE001\"\n" +
               "            },\n" +
               "            \"FIsEnableMinStock\": false,\n" +
               "            \"FIsEnableMaxStock\": false,\n" +
               "            \"FIsEnableSafeStock\": false,\n" +
               "            \"FIsEnableReOrder\": false,\n" +
               "            \"FMinStock\": 0.0,\n" +
               "            \"FSafeStock\": 0.0,\n" +
               "            \"FReOrderGood\": 0.0,\n" +
               "            \"FEconReOrderQty\": 0.0,\n" +
               "            \"FMaxStock\": 0.0,\n" +
               "            \"FIsSNManage\": false,\n" +
               "            \"FIsSNPRDTracy\": false,\n" +
               "            \"FSNManageType\": \"1\",\n" +
               "            \"FSNGenerateTime\": \"1\",\n" +
               "            \"FBoxStandardQty\": 0.0\n" +
               "        },\n" +
               "        \"SubHeadEntity2\": {\n" +
               "            \"FSaleUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FSalePriceUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FOrderQty\": 0.0,\n" +
               "            \"FMinQty\": 0.0,\n" +
               "            \"FMaxQty\": 100000.0,\n" +
               "            \"FOutStockLmtH\": 0.0,\n" +
               "            \"FOutStockLmtL\": 0.0,\n" +
               "            \"FAgentSalReduceRate\": 0.0,\n" +
               "            \"FIsATPCheck\": false,\n" +
               "            \"FIsReturnPart\": false,\n" +
               "            \"FIsInvoice\": false,\n" +
               "            \"FIsReturn\": true,\n" +
               "            \"FAllowPublish\": false,\n" +
               "            \"FISAFTERSALE\": true,\n" +
               "            \"FISPRODUCTFILES\": true,\n" +
               "            \"FISWARRANTED\": false,\n" +
               "            \"FWARRANTY\": 0,\n" +
               "            \"FWARRANTYUNITID\": \"D\",\n" +
               "            \"FOutLmtUnit\": \"SAL\",\n" +
               "            \"FIsTaxEnjoy\": false,\n" +
               "            \"FTaxDiscountsType\": \"0\",\n" +
               "            \"FUnValidateExpQty\": false\n" +
               "        },\n" +
               "        \"SubHeadEntity3\": {\n" +
               "            \"FBaseMinSplitQty\": 0.0,\n" +
               "            \"FPurchaseUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FPurchasePriceUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FIsQuota\": false,\n" +
               "            \"FQuotaType\": \"1\",\n" +
               "            \"FMinSplitQty\": 0.0,\n" +
               "            \"FIsVmiBusiness\": false,\n" +
               "            \"FEnableSL\": false,\n" +
               "            \"FIsPR\": false,\n" +
               "            \"FIsReturnMaterial\": true,\n" +
               "            \"FIsSourceControl\": false,\n" +
               "            \"FReceiveMaxScale\": 0.0,\n" +
               "            \"FReceiveMinScale\": 0.0,\n" +
               "            \"FReceiveAdvanceDays\": 0,\n" +
               "            \"FReceiveDelayDays\": 0,\n" +
               "            \"FPOBillTypeId\": {\n" +
               "                \"FNUMBER\": \"CGSQD01_SYS\"\n" +
               "            },\n" +
               "            \"FAgentPurPlusRate\": 0.0,\n" +
               "            \"FPrintCount\": 1,\n" +
               "            \"FMinPackCount\": 1.0,\n" +
               "            \"FDailyOutQtySub\": 0.0,\n" +
               "            \"FIsEnableScheduleSub\": false\n" +
               "        },\n" +
               "        \"SubHeadEntity4\": {\n" +
               "            \"FPlanMode\": \"0\",\n" +
               "            \"FBaseVarLeadTimeLotSize\": 0.0,\n" +
               "            \"FPlanningStrategy\": \"1\",\n" +
               "            \"FMfgPolicyId\": {\n" +
               "                \"FNumber\": \"ZZCL001_SYS\"\n" +
               "            },\n" +
               "            \"FOrderPolicy\": \"0\",\n" +
               "            \"FFixLeadTime\": 0,\n" +
               "            \"FFixLeadTimeType\": \"1\",\n" +
               "            \"FVarLeadTime\": 0,\n" +
               "            \"FVarLeadTimeType\": \"1\",\n" +
               "            \"FCheckLeadTime\": 0,\n" +
               "            \"FCheckLeadTimeType\": \"1\",\n" +
               "            \"FOrderIntervalTimeType\": \"3\",\n" +
               "            \"FOrderIntervalTime\": 0,\n" +
               "            \"FMaxPOQty\": 100000.0,\n" +
               "            \"FMinPOQty\": 0.0,\n" +
               "            \"FIncreaseQty\": 0.0,\n" +
               "            \"FEOQ\": 1.0,\n" +
               "            \"FVarLeadTimeLotSize\": 1.0,\n" +
               "            \"FPlanIntervalsDays\": 0,\n" +
               "            \"FPlanBatchSplitQty\": 0.0,\n" +
               "            \"FRequestTimeZone\": 0,\n" +
               "            \"FPlanTimeZone\": 0,\n" +
               "            \"FIsMrpComReq\": false,\n" +
               "            \"FCanLeadDays\": 0,\n" +
               "            \"FIsMrpComBill\": true,\n" +
               "            \"FLeadExtendDay\": 0,\n" +
               "            \"FReserveType\": \"1\",\n" +
               "            \"FAllowPartAhead\": false,\n" +
               "            \"FPlanSafeStockQty\": 0.0,\n" +
               "            \"FCanDelayDays\": 999,\n" +
               "            \"FDelayExtendDay\": 0,\n" +
               "            \"FAllowPartDelay\": true,\n" +
               "            \"FPlanOffsetTimeType\": \"1\",\n" +
               "            \"FPlanOffsetTime\": 0,\n" +
               "            \"FWriteOffQty\": 1.0,\n" +
               "            \"FDailyOutQty\": 0.0\n" +
               "        },\n" +
               "        \"SubHeadEntity5\": {\n" +
               "            \"FProduceUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FFinishReceiptOverRate\": 0.0,\n" +
               "            \"FFinishReceiptShortRate\": 0.0,\n" +
               "            \"FProduceBillType\": {\n" +
               "                \"FNUMBER\": \"SCDD03_SYS\"\n" +
               "            },\n" +
               "            \"FOrgTrustBillType\": {\n" +
               "                \"FNUMBER\": \"SCDD06_SYS\"\n" +
               "            },\n" +
               "            \"FIsProductLine\": false,\n" +
               "            \"FIsSNCarryToParent\": false,\n" +
               "            \"FBOMUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FConsumVolatility\": 0.0,\n" +
               "            \"FLOSSPERCENT\": 0.0,\n" +
               "            \"FIsMainPrd\": false,\n" +
               "            \"FIsCoby\": false,\n" +
               "            \"FIsECN\": false,\n" +
               "            \"FIssueType\": \"1\",\n" +
               "            \"FOverControlMode\": \"1\",\n" +
               "            \"FMinIssueQty\": 1.0,\n" +
               "            \"FISMinIssueQty\": false,\n" +
               "            \"FIsKitting\": false,\n" +
               "            \"FIsCompleteSet\": false,\n" +
               "            \"FStdLaborPrePareTime\": 0.0,\n" +
               "            \"FStdLaborProcessTime\": 0.0,\n" +
               "            \"FStdMachinePrepareTime\": 0.0,\n" +
               "            \"FStdMachineProcessTime\": 0.0,\n" +
               "            \"FMinIssueUnitId\": {\n" +
               "                \"FNUMBER\": \"kg\"\n" +
               "            },\n" +
               "            \"FStandHourUnitId\": \"3600\",\n" +
               "            \"FBackFlushType\": \"1\",\n" +
               "            \"FFIXLOSS\": 0.0,\n" +
               "            \"FIsEnableSchedule\": false\n" +
               "        },\n" +
               "        \"SubHeadEntity7\": {\n" +
               "            \"FSubconUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FSubconPriceUnitId\": {\n" +
               "                \"FNumber\": \"kg\"\n" +
               "            },\n" +
               "            \"FSubBillType\": {\n" +
               "                \"FNUMBER\": \"WWDD01_SYS\"\n" +
               "            }\n" +
               "        },\n" +
               "        \"SubHeadEntity6\": {\n" +
               "            \"FCheckIncoming\": false,\n" +
               "            \"FCheckProduct\": false,\n" +
               "            \"FCheckStock\": false,\n" +
               "            \"FCheckReturn\": false,\n" +
               "            \"FCheckDelivery\": false,\n" +
               "            \"FEnableCyclistQCSTK\": false,\n" +
               "            \"FStockCycle\": 0,\n" +
               "            \"FEnableCyclistQCSTKEW\": false,\n" +
               "            \"FEWLeadDay\": 0,\n" +
               "            \"FCheckEntrusted\": false,\n" +
               "            \"FCheckOther\": false,\n" +
               "            \"FIsFirstInspect\": false,\n" +
               "            \"FCheckReturnMtrl\": false,\n" +
               "            \"FCheckSubRtnMtrl\": false,\n" +
               "            \"FFirstQCControlType\": \"0\"\n" +
               "        },\n" +
               "        \"SubHeadEntity9\": {\n" +
               "            \"FRate\": 100.0,\n" +
               "            \"FCostProperty\": \"1\",\n" +
               "            \"FCheckDeviationRate\": 0.0,\n" +
               "            \"FGoodsUnitID\": {\n" +
               "                \"FNUMBER\": \"kg\"\n" +
               "            },\n" +
               "            \"FRackStatus\": \"A\",\n" +
               "            \"FIsDelivery\": true,\n" +
               "            \"FIsOwnPurchase\": false,\n" +
               "            \"FIfAsFoodSale\": false,\n" +
               "            \"FFoodPrice\": 0.0,\n" +
               "            \"FDayCheck\": false,\n" +
               "            \"FWeekCheck\": false,\n" +
               "            \"FLastDayOfMonth\": false,\n" +
               "            \"FMonthCheck\": false,\n" +
               "            \"FWeekCheckDay\": 0,\n" +
               "            \"FMonthCheckDay\": 0,\n" +
               "            \"FCanOrder\": true,\n" +
               "            \"FIsAgentPurchase\": false,\n" +
               "            \"FFirstUnitID\": {\n" +
               "                \"FNUMBER\": \"kg\"\n" +
               "            },\n" +
               "            \"FIsFood\": false,\n" +
               "            \"FBarcodeUnitID\": {\n" +
               "                \"FNUMBER\": \"kg\"\n" +
               "            }\n" +
               "        },\n" +
               "        \"FoodSubHeadEntity\": {\n" +
               "            \"FSalePrice\": 0.0,\n" +
               "            \"FWeightQty\": 0.0,\n" +
               "            \"FMaterialOrFood\": false\n" +
               "        },\n" +
               "        \"FEntityInvPty\": [\n" +
               "            {\n" +
               "                \"FInvPtyId\": {\n" +
               "                    \"FNumber\": \"01\"\n" +
               "                },\n" +
               "                \"FIsEnable\": true,\n" +
               "                \"FIsAffectPrice\": false,\n" +
               "                \"FIsAffectPlan\": false,\n" +
               "                \"FIsAffectCost\": false\n" +
               "            },\n" +
               "            {\n" +
               "                \"FInvPtyId\": {\n" +
               "                    \"FNumber\": \"02\"\n" +
               "                },\n" +
               "                \"FIsEnable\": true,\n" +
               "                \"FIsAffectPrice\": false,\n" +
               "                \"FIsAffectPlan\": false,\n" +
               "                \"FIsAffectCost\": false\n" +
               "            },\n" +
               "            {\n" +
               "                \"FInvPtyId\": {\n" +
               "                    \"FNumber\": \"03\"\n" +
               "                },\n" +
               "                \"FIsEnable\": false,\n" +
               "                \"FIsAffectPrice\": false,\n" +
               "                \"FIsAffectPlan\": false,\n" +
               "                \"FIsAffectCost\": false\n" +
               "            },\n" +
               "            {\n" +
               "                \"FInvPtyId\": {\n" +
               "                    \"FNumber\": \"04\"\n" +
               "                },\n" +
               "                \"FIsEnable\": false,\n" +
               "                \"FIsAffectPrice\": false,\n" +
               "                \"FIsAffectPlan\": false,\n" +
               "                \"FIsAffectCost\": false\n" +
               "            },\n" +
               "            {\n" +
               "                \"FInvPtyId\": {\n" +
               "                    \"FNumber\": \"06\"\n" +
               "                },\n" +
               "                \"FIsEnable\": false,\n" +
               "                \"FIsAffectPrice\": false,\n" +
               "                \"FIsAffectPlan\": false,\n" +
               "                \"FIsAffectCost\": false\n" +
               "            }\n" +
               "        ]\n" +
               "    }\n" +
               "}";


        for (int i = 0; i < 50; i++) {

            number = "test"+i;
            int i1 = new Random().nextInt(2) + 1; // 生成1或2
            group = "test0"+i1;
            String format = String.format(data, number,"测试"+i, group);
            String result = api.save("BD_Material",  format);
        }


        var gson = new Gson();
//        RepoRet sRet = gson.fromJson(result, RepoRet.class);
//        if (sRet.isSuccessfully()) {
//            materid = sRet.getResult().getId();
//            System.out.printf("物料保存接口: %s%n", gson.toJson(sRet.getResult()));
//        } else {
//            fail("物料保存接口: " + gson.toJson(sRet.getResult()));
//        }
    }

    @Test
    public void tttt2() throws Exception {
        var api = new K3CloudApi();
//        String json = "{\n" +
//                "    \"FormId\": \"BD_MATERIAL\",\n" +
//                "    \"FieldKeys\": \"FNumber,FNAME,FUseOrgId,FMaterialGroup\",\n" +
//                "    \"FilterString\": \"FUseOrgId=\'" + 1 + "\'\",\n" +
//                "    \"OrderString\": \"\",\n" +
//                "    \"TopRowCount\": 0,\n" +
//                "    \"StartRow\": 0,\n" +
//                "    \"Limit\": 2000,\n" +
//                "    \"SubSystemId\": \"\"\n" +
//                "}";

        HashMap map = new HashMap();
        map.put("FormId", "BD_Material");
        map.put("FieldKeys", "FMATERIALID,FBaseUnitId.FNAME as FUNIT,FMaterialGroup,FNumber,FName,FCreateOrgId,FUseOrgId");
        map.put("FilterString", "FUseOrgId='"+1+"' and FMaterialGroup='"+105455+"'");
        String result = api.billQuery(     JSONUtil.toJsonStr(map));
        System.out.println("物料单据查询接口: " + result);
    }
    @Test
    public void tttt3() throws Exception {
        var api = new K3CloudApi();
        String json = "{\n" +
                "    \"FormId\": \"SAL_MATERIALGROUP\",\n" +
                "    \"FieldKeys\": \"fid,fname\",\n" +
                "    \"OrderString\": \"\",\n" +
                "    \"TopRowCount\": 0,\n" +
                "    \"StartRow\": 0,\n" +
                "    \"Limit\": 2000,\n" +
                "    \"SubSystemId\": \"\"\n" +
                "}";
        String result = api.billQuery(json);
        System.out.println("物料单据查询接口: " + result);
    }



    @Test
    public void mtestMaterialBillQuery223() throws Exception {
        var api = new K3CloudApi();

        String json = "{\n" +
                "    \"FormId\": \"PINV_BarcodeAuth\",\n" +
                "    \"FieldKeys\": \"fid,FNumber,FName\",\n" +
                "    \"OrderString\": \"\",\n" +
                "    \"TopRowCount\": 0,\n" +
                "    \"StartRow\": 0,\n" +
                "    \"Limit\": 2000,\n" +
                "    \"SubSystemId\": \"\"\n" +
                "}";
        String result = api.billQuery(json);
        System.out.println("物料单据查询接口: " + result);
    }

    /*本接口用于实现物料 (BD_Material)的删除功能*/
    @Test
    public void ntestdeleteMaterial() throws Exception {
        var api = new K3CloudApi();
        String data = "{\"CreateOrgId\": 0,\"Numbers\": ["+"\""+FNumber+"\""+"],\"Id\": \"\",\"IsSortBySeq\": \"false\"}";
        String result = api.delete("BD_Material", data);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料删除接口: %s%n", gson.toJson(repoRet.getResult().getResponseStatus().getSuccessEntitys()));
        } else {
            fail(gson.toJson(repoRet.getResult().getResponseStatus()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的分组保存功能*/
    @Test
    public void otestgroupSaveMaterial() throws Exception {
        var api = new K3CloudApi();
        String json = "{\"GroupFieldKey\": \"\", \"GroupPkId\": 0, \"FParentId\": 0, \"FNumber\": "+"\""+FNumber+"\""+", \"FName\": "+"\""+FNumber+"\""+", \"FDescription\": \"\"}";
        String result = api.groupSave("BD_Material", json);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料分组保存接口: %s%n", gson.toJson(repoRet.getResult().getResponseStatus().getSuccessEntitys()));
            String re = gson.toJson(repoRet.getResult().getResponseStatus().getSuccessEntitys().get(0));
            JsonElement jsondata = new JsonParser().parse(re);
            JsonElement id = jsondata.getAsJsonObject().get("Id");
            groupid = id.toString();
        } else {
            fail("物料分组保存接口: " + gson.toJson(repoRet.getResult().getResponseStatus()));
        }
    }


    /*本接口用于实现物料 (BD_Material) 的分组查询功能*/
    @Test
    public void ptestqueryGroupInfoMaterial() throws Exception {
        var api = new K3CloudApi();
        String json = "{\"FormId\": \"BD_MATERIAL\", \"GroupFieldKey\": \"FMaterialGroup\", \"GroupPkIds\": "+groupid+", \"Ids\": \"\"}";
        String result = api.queryGroupInfo(json);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料分组查询接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料分组查询接口: " + gson.toJson(repoRet.getResult().getResponseStatus()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的分组删除功能*/
    @Test
    public void qtestgroupDeleteMaterial() throws Exception {
        var api = new K3CloudApi();
        String json = "{\"FormId\": \"BD_MATERIAL\", \"GroupFieldKey\": \"FMaterialGroup\", \"GroupPkIds\": "+groupid+",}";
        String result = api.groupDelete(json);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料分组删除接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料分组删除接口: " + gson.toJson(repoRet.getResult().getResponseStatus()));
        }
    }

    /*本接口用于实现物料 (BD_Material) 的切换组织接口功能*/
    @Test
    public void rtestswitchOrg() throws Exception {
        var api = new K3CloudApi();
        String json = "{ \"OrgNumber\": \"200\"}";
        String result = api.switchOrg(json);
        /*为不影响组织数据，切换组织后再切换回来*/
        String  json2 = "{ \"OrgNumber\": \"100\"}";
        String result2 = api.switchOrg(json);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result2, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("物料切换组织接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("物料切换组织接口: " + gson.toJson(repoRet.getResult().getResponseStatus()));
        }

    }


    /*本接口用于实现物料 (BD_Material) 的批量保存功能*/
    @Test
    public void stestBatchSaveCustomer() throws Exception {
        K3CloudApi api = new K3CloudApi();
        List<String> materialsmodel = new ArrayList<String>();
        int count =0;
        for (int i = 0; i < 2; i++) {
            String m =  "{\"FNumber\": "+"\""+SeqHelper.genNumber("WL")+"\""+",\"FName\": "+"\""+"auwl_" + UUID.randomUUID().toString()+"\""+",\"FImgStorageType\": \"A\",\"FCreateOrgId\": {\"FNumber\": \"100\"},\"FUseOrgId\": {\"FNumber\": \"100\"},\"FSubHeadEntity\": {\"FTimeUnit\": \"H\"},\"SubHeadEntity\": {\"FErpClsID\": \"1\",\"FFeatureItem\": \"1\",\"FCategoryID\": {\"FNumber\": \"CHLB01_SYS\"},\"FTaxType\": {\"FNumber\": \"WLDSFL01_SYS\"},\"FTaxRateId\": {\"FNUMBER\": \"SL02_SYS\"},\"FBaseUnitId\": {\"FNumber\": \"Pcs\"},\"FIsPurchase\": true,\"FIsInventory\": true,\"FIsSale\": true,\"FWEIGHTUNITID\": {\"FNUMBER\": \"kg\"},\"FVOLUMEUNITID\": {\"FNUMBER\": \"m\"}},\"SubHeadEntity1\": {\"FStoreUnitID\": {\"FNumber\": \"Pcs\"},\"FUnitConvertDir\": \"1\",\"FIsLockStock\": true,\"FCountCycle\": \"1\",\"FCountDay\": 1,\"FCurrencyId\": {\"FNumber\": \"PRE001\"},\"FSNManageType\": \"1\",\"FSNGenerateTime\": \"1\"},\"SubHeadEntity2\": {\"FSaleUnitId\": {\"FNumber\": \"Pcs\"},\"FSalePriceUnitId\": {\"FNumber\": \"Pcs\"},\"FMaxQty\": 100000,\"FIsReturn\": true,\"FISAFTERSALE\": true,\"FISPRODUCTFILES\": true,\"FWARRANTYUNITID\": \"D\",\"FOutLmtUnit\": \"SAL\"},\"SubHeadEntity3\": {\"FPurchaseUnitId\": {\"FNumber\": \"Pcs\"},\"FPurchasePriceUnitId\": {\"FNumber\": \"Pcs\"},\"FQuotaType\": \"1\",\"FIsReturnMaterial\": true,\"FPOBillTypeId\": {\"FNUMBER\": \"CGSQD01_SYS\"},\"FPrintCount\": 1,\"FMinPackCount\": 1},\"SubHeadEntity4\": {\"FPlanningStrategy\": \"1\",\"FMfgPolicyId\": {\"FNumber\": \"ZZCL001_SYS\"},\"FFixLeadTimeType\": \"1\",\"FVarLeadTimeType\": \"1\",\"FCheckLeadTimeType\": \"1\",\"FOrderIntervalTimeType\": \"3\",\"FMaxPOQty\": 100000,\"FEOQ\": 1,\"FVarLeadTimeLotSize\": 1,\"FIsMrpComBill\": true,\"FReserveType\": \"1\",\"FCanDelayDays\": 999,\"FAllowPartDelay\": true,\"FPlanOffsetTimeType\": \"1\",\"FWriteOffQty\": 1},\"SubHeadEntity5\": {\"FProduceUnitId\": {\"FNumber\": \"Pcs\"},\"FProduceBillType\": {\"FNUMBER\": \"SCDD03_SYS\"},\"FOrgTrustBillType\": {\"FNUMBER\": \"SCDD06_SYS\"},\"FBOMUnitId\": {\"FNumber\": \"Pcs\"},\"FIssueType\": \"1\",\"FOverControlMode\": \"1\",\"FMinIssueQty\": 1,\"FMinIssueUnitId\": {\"FNUMBER\": \"Pcs\"},\"FStandHourUnitId\": \"3600\",\"FBackFlushType\": \"1\"},\"SubHeadEntity7\": {\"FSubconUnitId\": {\"FNumber\": \"Pcs\"},\"FSubconPriceUnitId\": {\"FNumber\": \"Pcs\"},\"FSubBillType\": {\"FNUMBER\": \"WWDD01_SYS\"}},\"FEntityAuxPty\": [{\"FAuxPropertyId\": {\"FNumber\": \"10\"}},{\"FAuxPropertyId\": {\"FNumber\": \"20\"}},{\"FAuxPropertyId\": {\"FNumber\": \"30\"}}],\"FEntityInvPty\": [{\"FInvPtyId\": {\"FNumber\": \"01\"},\"FIsEnable\": true},{\"FInvPtyId\": {\"FNumber\": \"02\"},\"FIsEnable\": true},{\"FInvPtyId\": {\"FNumber\": \"03\"}},{\"FInvPtyId\": {\"FNumber\": \"04\"}},{\"FInvPtyId\": {\"FNumber\": \"06\"}}]}";
            materialsmodel.add(m);
            count = i+1;
        }
        String data = "{\"NumberSearch\":\"true\",\"ValidateFlag\":\"true\",\"IsDeleteEntry\":\"true\",\"IsEntryBatchFill\":\"true\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"SubSystemId\":\"\",\"InterationFlags\":\"\",\"Model\":"+materialsmodel+",\"BatchCount\":"+count+",\"IsVerifyBaseDataField\":\"false\",\"IsAutoAdjustField\":\"false\",\"IgnoreInterationFlag\":\"false\",\"IsControlPrecision\":\"false\",\"ValidateRepeatJson\":\"false\",}";
        // 批量保存物料信息
        String sRet = api.batchSave("BD_Material", data);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(sRet, RepoRet.class);
        if (repoRet.isSuccessfully()) {
            for (int i = 0; i < materialsmodel.size(); i++) {
                System.out.println(String.format("批量保持物料:%s%s", i+1,
                        gson.toJson(repoRet.getResult().getResponseStatus().getSuccessEntitys().get(i))));
            }
        } else {
            fail("物料批量保存接口："+gson.toJson(repoRet.getResult()));
        }
    }

    /*本接口用于实现发送消息功能*/
    @Test
    public void ttestsendmsg() throws Exception {
        K3CloudApi api = new K3CloudApi();
        String data = "{\"Model\":[{\"FTitle\":\"我是标题\",\"FContent\":\"我是内容\",\"FReceivers\":\"demo\",\"FType\":\"1\"}]}";
        String result = api.sendMsg(data);
        Gson gson = new Gson();
        RepoRet repoRet = gson.fromJson(result, RepoRet.class);
        if (repoRet.getResult().getResponseStatus().isIsSuccess()) {
            System.out.printf("消息发送接口: %s%n", gson.toJson(repoRet.getResult()));
        } else {
            fail("消息发送接口: " + gson.toJson(repoRet.getResult().getResponseStatus()));
        }
    }

}




