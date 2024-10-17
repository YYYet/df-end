package com.ruoyi.common.utils.cloud.consts;

public class SqlConst {
    /*
    根据卡号和密码查询用户
     */
    public final static String EMPINFO = "select FNAME,FMOBILE,FEMAIL,FJOINDATE,FSTAFFNUMBER,FUSEORGID " +
            "from T_HR_EMPINFO, T_HR_EMPINFO_L where T_HR_EMPINFO.FDOCUMENTSTATUS = 'C' AND T_HR_EMPINFO.fid = T_HR_EMPINFO_L.FID AND FCARDNO = %s and FPASSWORD = %s";

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
}
