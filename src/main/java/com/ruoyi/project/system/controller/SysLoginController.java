package com.ruoyi.project.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.*;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.utils.JwtUtils;
import com.ruoyi.common.utils.cloud.consts.SqlConst;
import com.ruoyi.common.utils.cloud.pojo.CloudSession;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.project.system.domain.CloudUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController
{

    @Autowired
    private CloudLoginUtil cloudLoginUtil;


    @GetMapping("/parseJwt")
    public Claims parseJwt(@RequestParam("token") String token) throws Exception {
        Claims claims = JwtUtils.parseJwt(token);
        return claims;
    }

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) throws Exception {

        AjaxResult ajax = AjaxResult.success();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginBody.getUsername());
        claims.put("password", loginBody.getPassword());
        claims.put("uuid", loginBody.getUuid());
        CloudSession cloudSession = cloudLoginUtil.getCloudSession();

        StpUtil.login(loginBody.getUsername());
        StpUtil.getSession().set("cloudSession", cloudSession);
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            String formatSql = String.format(SqlConst.EMPINFO, loginBody.getUsername(), loginBody.getPassword());
            maps = cloudLoginUtil.execSql(formatSql);
            System.out.println(maps);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("账号或者密码错误");
        }

        CloudUser cloudUser = new CloudUser();
        if (maps.size() == 1){
            Map<String, Object> stringObjectMap = maps.get(0);
            cloudUser.setUserName(stringObjectMap.get("FNAME").toString());
            if (Objects.nonNull(stringObjectMap.get("FEMAIL"))){
                cloudUser.setEmail(stringObjectMap.get("FEMAIL").toString());
            }
            if (Objects.nonNull(stringObjectMap.get("FMOBILE"))){
                cloudUser.setPhone(stringObjectMap.get("FMOBILE").toString());
            }
            if (Objects.nonNull(stringObjectMap.get("FJOINDATE"))){
                cloudUser.setJoinDate(stringObjectMap.get("FJOINDATE").toString());
            }
            if (Objects.nonNull(stringObjectMap.get("FID"))){
                cloudUser.setId(stringObjectMap.get("FID").toString());
            }
            cloudUser.setUserNo(stringObjectMap.get("FSTAFFNUMBER").toString());
            cloudUser.setUseOrgId(stringObjectMap.get("FUSEORGID").toString());
            cloudUser.setCloudSession(cloudSession);
            cloudUser.setLoginName(loginBody.getUsername());
            // 此处还需取权限

            StpUtil.getSession().set("user", cloudUser);
            ajax.put(Constants.TOKEN, StpUtil.getTokenValue());
        }else {
            ajax = AjaxResult.error("账号或者密码错误");
        }


        return ajax;
    }

    @GetMapping("/isLogin")
    public AjaxResult isLogin()
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("isLogin", StpUtil.isLogin());
        return ajax;
    }

    @SaCheckLogin
    @GetMapping("/getPermissions")
    public AjaxResult getPermissions() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<Map<String, Object>> maps = new ArrayList<>();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        String formatSql = String.format(SqlConst.PERMISSIONS, user.getLoginName());
        maps = cloudLoginUtil.execSql(formatSql);
        ajax.put("result", maps);
        return ajax;
    }

    @SaCheckPermission("1")
    @GetMapping("/testPermission1")
    public AjaxResult testPermission1(){
        return AjaxResult.success();
    }
    @SaCheckPermission("2")
    @GetMapping("/testPermission2")
    public AjaxResult testPermission2(){
        return AjaxResult.success();
    }
    @SaCheckLogin
    @GetMapping("/getMenus")
    public AjaxResult getMenus() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<Map<String, Object>> maps = new ArrayList<>();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        String formatSql = String.format(SqlConst.MENUITEMS, user.getLoginName());
        maps = cloudLoginUtil.execSql(formatSql);
        ajax.put("result", maps);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @SaCheckLogin
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        AjaxResult ajax = AjaxResult.success();
        // 这里从token获取  可以做一个拦截器存入threadlocal
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        ajax.put("user", user);
        return ajax;
    }


    @GetMapping("logout")
    public AjaxResult logout()
    {
        AjaxResult ajax = AjaxResult.success();
        StpUtil.logout();
        return ajax;
    }

}
