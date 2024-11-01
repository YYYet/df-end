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
            "WHERE UNW_t_Cust100004.F_UC_CARDNO = '{cardNo}' and t_bd_material.FUseOrgId = '{useOrgId}' " +
            "ORDER BY t_bd_material.FMATERIALID " +
            "OFFSET ({pageNumber} - 1) * {pageSize} ROWS " +
            "FETCH NEXT {pageSize} ROWS ONLY";

    public final static String SHOR_CART_INSERT = "INSERT INTO UNW_t_Cust100004 (FID,FBILLNO, FDOCUMENTSTATUS, F_UC_YG, F_UC_DATE, F_UC_CARDNO, F_UC_WL1, F_UC_QTY1)" +
            " VALUES ({billId},'{billNo}', 'C', '{userId}', '{nowDate}', '{cardNo}', {materialId}, {nums});";
    public final static String SHOR_CART_DELETE = "delete from UNW_t_Cust100004 where F_UC_CARDNO = {cardNo} and F_UC_WL1 = {materialId} ";
    public final static String SHOR_CART_CLEAR = "delete from UNW_t_Cust100004 where F_UC_CARDNO = {cardNo}";

}
