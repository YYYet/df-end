package com.ruoyi.common.utils.cloud;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.cloud.api.CloudHelper;
import com.ruoyi.common.utils.cloud.api.CloudInvoker;
import com.ruoyi.common.utils.cloud.consts.SqlConst;
import com.ruoyi.common.utils.cloud.util.CloudDataSource;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author shenkai
 * @since 2024/10/8
 */
public class Demo {

    public void test() throws JsonProcessingException, UnsupportedEncodingException {

        CloudDataSource source = new CloudDataSource();
        source.setUrl("http://118.178.135.61:2024/K3Cloud");
        source.setDbId("6705e2449ceff8");
        source.setLoginName("金蝶1");
        source.setPassword("123@.com");

        //调用标准webapi
        CloudHelper cloud = new CloudHelper(source);


        //cloud.save();//保存
        //cloud.audit();//审核
        //....其他
        List<List<Map<String, Object>>> data = cloud.sqlQuery("select FMATERIALID as id," +
                "FBaseUnitId.FNAME as unit,FMaterialGroup as groupId,FNumber as number," +
                "FName as name,FCreateOrgId as createOrgId,FUseOrgId as useOrgId where t_bd_material");//查询sql

//        //调用自定义webapi
//        CloudInvoker cloud1 = new CloudInvoker(source);
//        cloud1.login();
//        String res1 = cloud1.callCustom("插件调用地址", "参数1", "参数2", "参数3");
    }

    public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {
        CloudDataSource source = new CloudDataSource();
        source.setUrl("http://118.178.135.61:2024/K3Cloud/");
        source.setDbId("6705e2449ceff8");
        source.setLoginName("17336268371");
        source.setPassword("123@.com");

        //调用标准webapi
        CloudHelper cloud = new CloudHelper(source);

//        cloud.login();
//        System.out.println(cloud.getConnect().getSessionValue());
//        System.out.println(cloud.getConnect().getSessionValue_aspnet());

        //cloud.save();//保存
        //cloud.audit();//审核

//        String sql = "select * from T_HR_EMPINFO, T_HR_EMPINFO_L where T_HR_EMPINFO.fid = T_HR_EMPINFO_L.FID AND FCARDNO = 18888888888 and FPASSWORD = 123456";
        String sql = "select T_UC_BarcodeAuth.FID,T_UC_BarcodeAuth.FNUMBER, from T_UC_BarcodeAuth,T_UC_BarcodeAuthFun,T_UC_BarcodeAuthEmp where " +
                "T_UC_BarcodeAuth.fid = T_UC_BarcodeAuthFun.fid and  T_UC_BarcodeAuthFun.fid = T_UC_BarcodeAuthEmp.fid and T_UC_BarcodeAuth.FDOCUMENTSTATUS = 'C'";
//        INNER JOIN Table2 ON Table1.common_column = Table2.common_column
//INNER JOIN Table3 ON Table2.common_column = Table3.common_column;
        String sqla = "select T_HR_EMPINFO.fid AS EMPFID,T_HR_EMPINFO.FNUMBER AS EMPNUMBER,T_UC_BarcodeMenuItem.FID AS MENUFID,T_UC_BarcodeMenuItem.FNUMBER AS MENUNUMBER," +
                "T_HR_EMPINFO.FCARDNO  from T_UC_BarcodeAuth " +
                "INNER JOIN T_UC_BarcodeAuthFun ON T_UC_BarcodeAuth.fid = T_UC_BarcodeAuthFun.fid  INNER JOIN T_UC_BarcodeMenuItem ON T_UC_BarcodeMenuItem.fid = T_UC_BarcodeAuthFun.FITEMID " +
                "INNER JOIN T_UC_BarcodeAuthEmp ON T_UC_BarcodeAuthFun.fid = T_UC_BarcodeAuthEmp.fid INNER JOIN T_HR_EMPINFO ON T_HR_EMPINFO.fid = T_UC_BarcodeAuthEmp.FEMPID " +
                "INNER JOIN T_HR_EMPINFO_L ON T_UC_BarcodeAuthEmp.FEMPID = T_HR_EMPINFO_L.FID " +
                "where T_UC_BarcodeAuth.FDOCUMENTSTATUS = 'C' AND T_HR_EMPINFO.FCARDNO = 18888888888";

//        String sqlaB = "select T_HR_EMPINFO.fid AS EMPFID,T_HR_EMPINFO.FNUMBER AS EMPNUMBER,T_UC_BarcodeMenuItem.FID AS MENUFID,T_UC_BarcodeMenuItem.FNUMBER AS MENUNUMBER " +
//                " from T_UC_BarcodeAuth " +
//                "INNER JOIN T_UC_BarcodeAuthFun ON T_UC_BarcodeAuth.fid = T_UC_BarcodeAuthFun.fid  INNER JOIN T_UC_BarcodeMenuItem ON T_UC_BarcodeMenuItem.fid = T_UC_BarcodeAuthFun.FITEMID " +
//                "INNER JOIN T_UC_BarcodeAuthEmp ON T_UC_BarcodeAuthFun.fid = T_UC_BarcodeAuthEmp.fid INNER JOIN T_HR_EMPINFO_L ON T_HR_EMPINFO_L.fid = T_UC_BarcodeAuthEmp.FEMPID " +
//
//                "where T_UC_BarcodeAuth.FDOCUMENTSTATUS = 'C'";

        String sql2 = "select * from T_UC_BarcodeAuthFun";
        String sql3 = "select * from T_UC_BarcodeAuthEmp";
        String sql4 = "select * from T_UC_BarcodeMenuItem INNER JOIN T_UC_BarcodeMenuItem_L ON T_UC_BarcodeMenuItem_L.fid = T_UC_BarcodeMenuItem.fid  where FDOCUMENTSTATUS = 'C'";
        String sql5 = "select * from T_UC_BarcodeMenuItem_L";
        String sql6 ="select * from t_bd_material,t_bd_material_l,t_bd_material_p " +
                "where t_bd_material.FMATERIALID = t_bd_material_l.FMATERIALID and t_bd_material_l.FMATERIALID";




        String sql8 ="select t_bd_material.FMATERIALID as id," +
                "T_BD_MATERIALBASE.FBaseUnitId as unit," +
                "t_bd_material.FMaterialGroup as groupId," +
                "t_bd_material.FNumber as number," +
                "t_bd_material_l.FName as name," +
                "t_bd_material.FCreateOrgId as createOrgId," +
                "ISNULL(UNW_t_Cust_Entry100007.F_UC_QTY, 0) as nums," +
                "t_bd_material.FUseOrgId as useOrgId from t_bd_material " +
                "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = t_bd_material.FMATERIALID " +
                "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = t_bd_material.FMATERIALID " +
                "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = t_bd_material.FMATERIALID " +
                "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
                "left  JOIN UNW_t_Cust_Entry100007 on UNW_t_Cust_Entry100007.F_UC_WL = T_BD_MATERIALBASE.FMATERIALID and UNW_t_Cust_Entry100007.F_UC_CARDNO1 = '{cardNo}' " +
                "WHERE t_bd_material.FUseOrgId = '{useOrgId}' and FMaterialGroup= '{groupId}' and t_bd_material_l.FName like '%{likeTxt}%'" +
                "ORDER BY t_bd_material.FMATERIALID " +
                "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
                "FETCH NEXT {pageSize} ROWS ONLY";
//        String formatSql = String.format(sql8, "18888888888", "105454", 1, 100, 100);

        String sql9 ="select t_bd_material.FMATERIALID as id," +
                "T_BD_MATERIALBASE.FBaseUnitId as unit," +
                "t_bd_material.FMaterialGroup as groupId," +
                "t_bd_material.FNumber as number," +
                "t_bd_material_l.FName as name," +
                "t_bd_material.FCreateOrgId as createOrgId," +
                "ISNULL(UNW_t_Cust_Entry100007.F_UC_QTY, 0) as nums," +
                "t_bd_material.FUseOrgId as useOrgId from UNW_t_Cust_Entry100007 " +
                "left  JOIN t_bd_material on t_bd_material.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
                "WHERE UNW_t_Cust_Entry100007.F_UC_CARDNO1 = '{cardNo}' and t_bd_material.FUseOrgId = '{useOrgId}' and FMaterialGroup= '{groupId}'" +
                "ORDER BY t_bd_material.FMATERIALID " +
                "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
                "FETCH NEXT {pageSize} ROWS ONLY";

        String sql10 ="select t_bd_material.FMATERIALID as id," +
                "T_BD_MATERIALBASE.FBaseUnitId as unit," +
                "t_bd_material.FMaterialGroup as groupId," +
                "t_bd_material.FNumber as number," +
                "t_bd_material_l.FName as name," +
                "t_bd_material.FCreateOrgId as createOrgId," +
                "ISNULL(UNW_t_Cust_Entry100007.F_UC_QTY, 0) as nums," +
                "t_bd_material.FUseOrgId as useOrgId from UNW_t_Cust_Entry100007 " +
                "left  JOIN t_bd_material on t_bd_material.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = UNW_t_Cust_Entry100007.F_UC_WL " +
                "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
                "WHERE UNW_t_Cust_Entry100007.F_UC_CARDNO1 = '{cardNo}' and t_bd_material.FUseOrgId = '{useOrgId}' and FMaterialGroup= '{groupId}'" +
                "ORDER BY t_bd_material.FMATERIALID " +
                "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
                "FETCH NEXT {pageSize} ROWS ONLY";


        Map<String, Object> f = new HashMap<>();
        f.put("cardNo", "18888888888");
        f.put("useOrgId", 1);
        f.put("groupId", 105454);
        f.put("likeTxt", "测试");
        f.put("pageNumber", 1);
        f.put("pageSize", 10);
//        String result = StrFormatter.format(sql8, f);
        String result =StrUtil.format(SqlConst.MATERIAL_LIMIT, f);
        System.out.println(result);

        String sql7 ="delete from UNW_t_Cust100004 where F_UC_CARDNO = {cardNo} and F_UC_WL1 = {materialId} ";
        String sql71 ="INSERT INTO UNW_t_Cust100004 (FBILLNO, FDOCUMENTSTATUS, F_UC_YG, F_UC_DATE, F_UC_CARDNO, F_UC_WL1, F_UC_QTY1)" +
                " VALUES ({billNo}, 'C', {userId}, {nowDate}, {cardNo}, {materialId}, {nums});";
        String sql111 ="select *  from UNW_t_Cust100004 ";
        Map<String, Object> fa = new HashMap<>();
        fa.put("cardNo", "18888888888");
        fa.put("materialId", 105108);
        result =StrUtil.format(sql7, fa);

//        UNW_t_Cust100006.FBILLNO as number,F_UC_WL as materialId, " +
//                "UNW_t_Cust_Entry100009.F_UC_WL.fname as materialName, " +
//                "UNW_t_Cust100006.F_UC_YHZQ as day," +
//                "UNW_t_Cust_Entry100009.F_UC_WL.FMaterialGroup as groupName, " +
//                "UNW_t_Cust_Entry100009.F_UC_WL.FMaterialGroup.id as groupId
//        "FBILLNO as number," +
//                "F_UC_WL as materialId, F_UC_WL.fname as materialName, F_UC_YHZQ as day," +
//                "F_UC_WL.FMaterialGroup as groupName, F_UC_WL.FMaterialGroup.id as groupId " +
//                "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = t_bd_material.FMATERIALID " +
//                "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = t_bd_material.FMATERIALID " +
//                "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = t_bd_material.FMATERIALID " +
//                "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
//                "left  JOIN UNW_t_Cust100004 on UNW_t_Cust100004.F_UC_WL1 = T_BD_MATERIALBASE.FMATERIALID and UNW_t_Cust100004.F_UC_CARDNO = '18888888888' " +
//                ""

//        t_bd_material.FMATERIALID as id," +
//            "T_BD_UNIT_L.FNAME as unit," +
//            "T_BD_MATERIALBASE.FBaseUnitId as unitID," +
//            "t_bd_material.FMaterialGroup as groupId," +
//            "t_bd_material.FNumber as number," +
//            "t_bd_material_l.FName as name," +
//            "t_bd_material.FCreateOrgId as createOrgId," +
//            "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums," +
//            "t_bd_material.FUseOrgId as useOrgId from t_bd_material " +
        String sql333 = "select t_bd_material.FMATERIALID as id, " +
                "T_BD_UNIT_L.FNAME as unit, " +
                "T_BD_MATERIALBASE.FBaseUnitId as unitID, " +
                "t_bd_material.FMaterialGroup as groupId, " +
                "t_bd_material.FNumber as number, " +
                "t_bd_material_l.FName as name, " +
                "t_bd_material.FCreateOrgId as createOrgId, " +
                "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums, " +
                "t_bd_material.FUseOrgId as useOrgId " +
                "from UNW_t_Cust_Entry100009 " +
                "left JOIN UNW_t_Cust100006 on UNW_t_Cust100006.fid = UNW_t_Cust_Entry100009.fid " +
                "left  JOIN t_bd_material on t_bd_material.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
                "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
                "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
                "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
                "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
                "left  JOIN UNW_t_Cust100004 on UNW_t_Cust100004.F_UC_WL1 = T_BD_MATERIALBASE.FMATERIALID and UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}}' " +
                "WHERE t_bd_material.FUseOrgId = '{useOrgId}' and FMaterialGroup= '{groupId}' " +
                "ORDER BY t_bd_material.FMATERIALID " +
                "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
                "FETCH NEXT {pageSize} ROWS ONLY";
        String sql2222 = "select * " +
                " from UNW_t_Cust_Entry100009" +
                " left JOIN UNW_t_Cust100006 on UNW_t_Cust100006.fid = UNW_t_Cust_Entry100009.fid";

//        String sss = "select FBILLNO as number," +
//                "UNW_t_Cust_Entry100009.F_UC_NAME as name, UNW_t_Cust_Entry100009.F_UC_YHZQ as day, " +
//                "F_UC_MD.FNUMBER as orgNumber, F_UC_MDNAME as orgName," +
//                "UNW_t_Cust_Entry100009.F_UC_YHLX as typeId, F_UC_YHLX.FNAME as typeName, F_UNW_USERID_C1C.fname as auditor " +
//                "from UNW_t_Cust_Entry100009 " +
//                "left JOIN UNW_t_Cust_Entry100008 on UNW_t_Cust_Entry100008.fid = UNW_t_Cust_Entry100009.fid ";
        String sss = "select UNW_t_Cust100006.F_UC_NAME as name," +
                "UNW_t_Cust100006.F_UC_YHLX as typeId," +
                "UNW_t_Cust100005_l.FNAME as typeName," +
                "UNW_t_Cust100006.F_UNW_USERID_C1C as auditorId," +
                "T_SEC_user.FNAME as auditor," +
                "UNW_t_Cust100006.F_UC_YHZQ as day," +
                "UNW_t_Cust100006.F_UC_QSSJ," +
                "UNW_t_Cust_Entry100008.F_UC_MD as orgId," +
                "T_ORG_Organizations_l.FNAME as orgName," +
                "UNW_t_Cust100006.F_UC_JZSJ from UNW_t_Cust100006 " +
                "inner JOIN UNW_t_Cust_Entry100008 on UNW_t_Cust_Entry100008.fid = UNW_t_Cust100006.fid and UNW_t_Cust_Entry100008.F_UC_MD = 1 "+
                "left JOIN UNW_t_Cust100005_l on UNW_t_Cust100005_l.FID = UNW_t_Cust100006.F_UC_YHLX and UNW_t_Cust100005_l.FLOCALEID = '2052'"+
                "left JOIN T_SEC_user on T_SEC_user.FUSERID = UNW_t_Cust100006.F_UNW_UserId_c1c "+
                "left JOIN T_ORG_Organizations_l on  T_ORG_Organizations_l.FLOCALEID = '2052' " +
                "and T_ORG_Organizations_l.FOrgID = 1 " +
                "where UNW_t_Cust100006.F_UC_W3 = 1 and ( CAST(UNW_t_Cust100006.F_UC_QSSJ AS TIME) <= CAST('2024-11-06 21:00' AS TIME)\n" +
                "AND CAST(UNW_t_Cust100006.F_UC_JZSJ AS TIME) >= CAST('2024-11-06 21:00' AS TIME))";
        String aaa = "select * from UNW_t_Cust100006";
        List<List<Map<String, Object>>> data0 = cloud.sqlQuery(sss);//查询sql
        List<List<Map<String, Object>>> data1 = cloud.sqlQuery(aaa);//查询sql
//        System.out.println("data1 "+data1);
//        System.out.println("data0 "+data0);
//        List<Map<String, Object>> maps = data0.get(0);
//        for (Map<String, Object> map : maps) {
//            System.out.println(map);
//        }

//        Map<String, Object> fF = new HashMap<>();
//        Week week = DateUtil.thisDayOfWeekEnum();
//        int nowWeek = week.getIso8601Value();
//        String nowTime = DateUtil.now();
//        fF.put("useOrgId", 1);
//        fF.put("nowTime", nowTime);
//        fF.put("nowWeek", nowWeek);
//        System.out.println("f "+ fF);
//        String sql32323 = StrUtil.format(SqlConst.TEMPLATE_LIMIT, fF);
//        System.out.println("sql "+ sql32323);
////        List<Map<String, Object>> maps = new CloudLoginUtil().execSql(StrUtil.format(SqlConst.TEMPLATE_LIMIT, f));
//        System.out.println(cloud.sqlQuery(sql32323).get(0));



        Map<String, Object> f2 = new HashMap<>();
        f2.put("cardNo", "18888888888");
        f2.put("useOrgId", 1);
        f2.put("groupId", 105454);
        f2.put("pageNumber", 1);
        f2.put("pageSize", 10);
        f2.put("billNo", 4);
        String formatSql = StrUtil.format(SqlConst.MATERIAL_LIMIT_V2, f2);
        List<Map<String, Object>> maps = cloud.sqlQuery(formatSql).get(0);
        for (int i = 0; i < maps.size(); i++) {
            System.out.println(maps.get(i));
        }
        System.out.println("===============================================");
//        System.out.println(cloud.sqlQuery(formatSql).get(0));
        System.out.println(cloud.sqlQuery("select * from UNW_t_Cust100004").get(0));
        for (Map<String, Object> stringObjectMap : cloud.sqlQuery("select UNW_t_Cust_Entry100009.F_UC_WL from UNW_t_Cust100006 " +
                "inner JOIN UNW_t_Cust_Entry100009 on UNW_t_Cust100006.fid = UNW_t_Cust_Entry100009.fid and UNW_t_Cust100006.FBILLNO = 4 " +
                "left  JOIN UNW_t_Cust100004 on  UNW_t_Cust_Entry100009.F_UC_WL = UNW_t_Cust100004.F_UC_WL1  and UNW_t_Cust100004.F_UC_CARDNO = '18888888888' "
                ).get(0)) {
            System.out.println(stringObjectMap);
        }






//        List<List<Map<String, Object>>> data1 = cloud.sqlQuery("select * from T_UC_BarcodeAuth");//查询sql
//        List<List<Map<String, Object>>> data2 = cloud.sqlQuery(sql2);//查询sql
//        List<List<Map<String, Object>>> data3 = cloud.sqlQuery(sql3);//查询sql
//        System.out.println(data0);
//        System.out.println(data1);
//        System.out.println(data2);
//        System.out.println(data3);
    }
}
