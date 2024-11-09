package com.ruoyi.common.utils.cloud.consts;

public class SqlConst {
    /*
    根据卡号和密码查询用户
     */
    public final static String EMPINFO = "select T_HR_EMPINFO.FID,FNAME,FMOBILE,FEMAIL,FJOINDATE,FSTAFFNUMBER,FUSEORGID " +
            "from T_HR_EMPINFO, T_HR_EMPINFO_L where T_HR_EMPINFO.FDOCUMENTSTATUS = 'C' AND T_HR_EMPINFO.fid = T_HR_EMPINFO_L.FID AND FCARDNO = '%s' and FPASSWORD = '%s'";

    public final static String PERMISSIONS = "select DISTINCT T_UC_BarcodeMenuItem.FID AS MENUFID,T_UC_BarcodeMenuItem.FNUMBER AS MENUNUMBER, T_UC_BarcodeMenuItem_L.FNAME AS MENUNAME," +
            "T_HR_EMPINFO.FCARDNO  from T_UC_BarcodeAuth " +
            "INNER JOIN T_UC_BarcodeAuthFun ON T_UC_BarcodeAuth.fid = T_UC_BarcodeAuthFun.fid  INNER JOIN T_UC_BarcodeMenuItem ON T_UC_BarcodeMenuItem.fid = T_UC_BarcodeAuthFun.FITEMID " +
            "INNER JOIN T_UC_BarcodeMenuItem_L ON T_UC_BarcodeMenuItem_L.fid = T_UC_BarcodeMenuItem.fid " +
            "INNER JOIN T_UC_BarcodeAuthEmp ON T_UC_BarcodeAuthFun.fid = T_UC_BarcodeAuthEmp.fid INNER JOIN T_HR_EMPINFO ON T_HR_EMPINFO.fid = T_UC_BarcodeAuthEmp.FEMPID " +
            "INNER JOIN T_HR_EMPINFO_L ON T_UC_BarcodeAuthEmp.FEMPID = T_HR_EMPINFO_L.FID " +
            "where T_UC_BarcodeAuth.FDOCUMENTSTATUS = 'C' AND T_HR_EMPINFO.FCARDNO = %s";

    public final static String MENUITEMS = "select T_UC_BarcodeMenuItem_L.FID,T_UC_BarcodeMenuItem.FNUMBER,T_UC_BarcodeMenuItem_L.FNAME,T_UC_BarcodeMenuItem_L.FPKID from T_UC_BarcodeMenuItem " +
            "INNER JOIN T_UC_BarcodeMenuItem_L ON T_UC_BarcodeMenuItem_L.fid = T_UC_BarcodeMenuItem.fid " +
            "where FDOCUMENTSTATUS = 'C' AND FFORBIDSTATUS = 'A'";


    public final static String MATERIAL_LIMIT = "select t_bd_material.FMATERIALID as id," +
            "T_BD_UNIT_L.FNAME as unit," +
            "T_BD_MATERIALBASE.FBaseUnitId as unitID," +
            "t_bd_material.FMaterialGroup as groupId," +
            "t_bd_material.FNumber as number," +
            "t_bd_material_l.FName as name," +
            "t_bd_material.FCreateOrgId as createOrgId," +
            "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums," +
            "t_bd_material.FUseOrgId as useOrgId from t_bd_material " +
            "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = t_bd_material.FMATERIALID " +
            "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = t_bd_material.FMATERIALID " +
            "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = t_bd_material.FMATERIALID " +
            "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
            "left  JOIN UNW_t_Cust100004 on UNW_t_Cust100004.F_UC_WL1 = T_BD_MATERIALBASE.FMATERIALID and UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}' " +
            "WHERE t_bd_material.FUseOrgId = '{useOrgId}' and FMaterialGroup= '{groupId}' " +
            "ORDER BY t_bd_material.FMATERIALID " +
            "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
            "FETCH NEXT {pageSize} ROWS ONLY";
    public final static String MATERIAL_LIMIT_V2 = "select " +
//            "t_bd_material.FMATERIALID as id, " +
            "T_BD_UNIT_L.FNAME as unit, " +
            "T_BD_UNIT.FNumber as unitNumber, "+
            "T_BD_MATERIALBASE.FBaseUnitId as unitID, " +
            "t_bd_material.FMaterialGroup as groupId, " +
            "t_bd_material.FNumber as number, " +
            "t_bd_material_l.FName as name, " +
            "t_bd_material.FCreateOrgId as createOrgId, " +
//            "UNW_t_Cust100004.F_UC_WL1, " +
            "UNW_t_Cust_Entry100009.F_UC_WL as id ," +
            "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums, " +
            "t_bd_material.FUseOrgId as useOrgId " +
            "from UNW_t_Cust_Entry100009 " +
            "inner JOIN UNW_t_Cust100006 on UNW_t_Cust100006.fid = UNW_t_Cust_Entry100009.fid and UNW_t_Cust100006.FBILLNO = {billNo} " +
            "left  JOIN t_bd_material on t_bd_material.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
            "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL and t_bd_material_l.FLOCALEID = '2052' " +
//            "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
            "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
            "left  JOIN T_BD_UNIT on T_BD_UNIT.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
            "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId and T_BD_UNIT_L.FLOCALEID = '2052' " +
            "left  JOIN UNW_t_Cust100004 on  UNW_t_Cust_Entry100009.F_UC_WL = UNW_t_Cust100004.F_UC_WL1  and UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}' " +
            "WHERE t_bd_material.FUseOrgId = '{useOrgId}' and FMaterialGroup= '{groupId}' " +
            "ORDER BY t_bd_material.FMATERIALID " +
            "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
            "FETCH NEXT {pageSize} ROWS ONLY";

    public final static String MATERIAL_LIMIT_MATCH_NAME_V2 = "select " +
//            "t_bd_material.FMATERIALID as id, " +
            "T_BD_UNIT_L.FNAME as unit, " +
            "T_BD_UNIT.FNumber as unitNumber, "+
            "T_BD_MATERIALBASE.FBaseUnitId as unitID, " +
            "t_bd_material.FMaterialGroup as groupId, " +
            "t_bd_material.FNumber as number, " +
            "t_bd_material_l.FName as name, " +
            "t_bd_material.FCreateOrgId as createOrgId, " +
//            "UNW_t_Cust100004.F_UC_WL1, " +
            "UNW_t_Cust_Entry100009.F_UC_WL as id ," +
            "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums, " +
            "t_bd_material.FUseOrgId as useOrgId " +
            "from UNW_t_Cust_Entry100009 " +
            "inner JOIN UNW_t_Cust100006 on UNW_t_Cust100006.fid = UNW_t_Cust_Entry100009.fid and UNW_t_Cust100006.FBILLNO = {billNo} " +
            "left  JOIN t_bd_material on t_bd_material.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
            "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL and t_bd_material_l.FLOCALEID = '2052' " +
//            "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
            "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = UNW_t_Cust_Entry100009.F_UC_WL " +
            "left  JOIN T_BD_UNIT on T_BD_UNIT.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
            "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId and T_BD_UNIT_L.FLOCALEID = '2052' " +
            "left  JOIN UNW_t_Cust100004 on  UNW_t_Cust_Entry100009.F_UC_WL = UNW_t_Cust100004.F_UC_WL1  and UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}' " +
            "WHERE t_bd_material_l.FName like '%{materialName}%' ";


    public final static String TEMPLATE_LIMIT = "select UNW_t_Cust100006.F_UC_NAME as name," +
            "UNW_t_Cust100006.F_UC_YHLX as typeId," +
            "UNW_t_Cust100005_l.FNAME as typeName," +
            "UNW_t_Cust100006.F_UNW_USERID_C1C as auditorId," +
            "T_SEC_user.FNAME as auditor," +
            "UNW_t_Cust100006.FBILLNO as billNumber," +
            "UNW_t_Cust100006.F_UC_YHZQ as day," +
            "UNW_t_Cust100006.F_UC_QSSJ as startTime," +
            "UNW_t_Cust_Entry100008.F_UC_MD as orgId," +
            "T_ORG_Organizations.FNUMBER as orgNumber," +
            "T_ORG_Organizations_l.FNAME as orgName," +
            "UNW_t_Cust100006.F_UC_JZSJ as endTime " +
            "from UNW_t_Cust100006 " +
            "inner JOIN UNW_t_Cust_Entry100008 on UNW_t_Cust_Entry100008.fid = UNW_t_Cust100006.fid and UNW_t_Cust_Entry100008.F_UC_MD = {useOrgId} "+
            "left JOIN UNW_t_Cust100005_l on UNW_t_Cust100005_l.FID = UNW_t_Cust100006.F_UC_YHLX and UNW_t_Cust100005_l.FLOCALEID = '2052'"+
            "left JOIN T_SEC_user on T_SEC_user.FUSERID = UNW_t_Cust100006.F_UNW_UserId_c1c "+
            "left JOIN T_ORG_Organizations on T_ORG_Organizations.FORGID =  {useOrgId} "+
            "left JOIN T_ORG_Organizations_l on  T_ORG_Organizations_l.FLOCALEID = '2052' " +
            "and T_ORG_Organizations_l.FOrgID = {useOrgId} " +
            "where UNW_t_Cust100006.F_UC_W{nowWeek} = 1 and UNW_t_Cust100006.FDOCUMENTSTATUS = 'C' and ( CAST(UNW_t_Cust100006.F_UC_QSSJ AS TIME) <= CAST('{nowTime}' AS TIME)\n" +
            "AND CAST(UNW_t_Cust100006.F_UC_JZSJ AS TIME) >= CAST('{nowTime}' AS TIME))";

    public final static String MATERIAL_LIMIT_MATCH_NAME = "select t_bd_material.FMATERIALID as id," +
            "T_BD_UNIT_L.FNAME as unit," +
            "T_BD_MATERIALBASE.FBaseUnitId as unitID," +
            "t_bd_material.FMaterialGroup as groupId," +
            "t_bd_material.FNumber as number," +
            "t_bd_material_l.FName as name," +
            "t_bd_material.FCreateOrgId as createOrgId," +
            "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums," +
            "t_bd_material.FUseOrgId as useOrgId from t_bd_material " +
            "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = t_bd_material.FMATERIALID " +
            "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = t_bd_material.FMATERIALID " +
            "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = t_bd_material.FMATERIALID " +
            "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
            "left  JOIN UNW_t_Cust100004 on UNW_t_Cust100004.F_UC_WL1 = T_BD_MATERIALBASE.FMATERIALID and UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}' " +
            "WHERE t_bd_material.FUseOrgId = '{useOrgId}'  and t_bd_material_l.FName like '%{likeTxt}%' " +
            "ORDER BY t_bd_material.FMATERIALID " +
            "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
            "FETCH NEXT {pageSize} ROWS ONLY";

    public final static String MATERIAL_AREADY_ADDED = "select t_bd_material.FMATERIALID as id," +
            "T_BD_UNIT_L.FNAME as unit," +
            "T_BD_UNIT.FNumber as unitNumber, "+
            "T_BD_MATERIALBASE.FBaseUnitId as unitID," +
            "t_bd_material.FMaterialGroup as groupId," +
            "t_bd_material.FNumber as number," +
            "t_bd_material_l.FName as name," +
            "t_bd_material.FCreateOrgId as createOrgId," +
            "ISNULL(UNW_t_Cust100004.F_UC_QTY1, 0) as nums," +
            "t_bd_material.FUseOrgId as useOrgId from UNW_t_Cust100004 " +
            "left  JOIN t_bd_material on t_bd_material.FMATERIALID = UNW_t_Cust100004.F_UC_WL1 " +
            "left  JOIN t_bd_material_l on t_bd_material_l.FMATERIALID = UNW_t_Cust100004.F_UC_WL1 " +
            "left  JOIN t_bd_material_p on t_bd_material_p.FMATERIALID = UNW_t_Cust100004.F_UC_WL1 " +
            "left  JOIN T_BD_MATERIALBASE on T_BD_MATERIALBASE.FMATERIALID = UNW_t_Cust100004.F_UC_WL1 " +
            "left  JOIN T_BD_UNIT_L on T_BD_UNIT_L.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
            "left  JOIN T_BD_UNIT on T_BD_UNIT.FUNITID = T_BD_MATERIALBASE.FBaseUnitId " +
            "WHERE UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}' and t_bd_material.FUseOrgId = '{useOrgId}' " +
            "ORDER BY t_bd_material.FMATERIALID " +
            "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
            "FETCH NEXT {pageSize} ROWS ONLY";

    public final static String SHOR_CART_INSERT = "INSERT INTO UNW_t_Cust100004 (FID,FBILLNO, FDOCUMENTSTATUS, F_UC_YG, F_UC_DATE, F_UC_CARDNO, F_UC_WL1, F_UC_QTY1)" +
            " VALUES ({billId},'{billNo}', 'C', '{userId}', '{nowDate}', '{cardNo}', {materialId}, {nums});";
    public final static String SHOR_CART_DELETE = "delete from UNW_t_Cust100004 where F_UC_CARDNO = {cardNo} and F_UC_WL1 = {materialId} ";
    public final static String SHOR_CART_CLEAR = "delete from UNW_t_Cust100004 where F_UC_CARDNO = {cardNo}";

}
