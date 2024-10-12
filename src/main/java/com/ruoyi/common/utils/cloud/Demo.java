package com.ruoyi.common.utils.cloud;

import cn.hutool.core.codec.Base64;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.utils.cloud.api.CloudHelper;
import com.ruoyi.common.utils.cloud.api.CloudInvoker;
import com.ruoyi.common.utils.cloud.util.CloudDataSource;


import java.io.UnsupportedEncodingException;
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
        List<List<Map<String, Object>>> data = cloud.sqlQuery("select top 1 * from T_HR_EMPINFO");//查询sql

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
        List<List<Map<String, Object>>> data0 = cloud.sqlQuery(sql4);//查询sql
        List<Map<String, Object>> maps = data0.get(0);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
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
