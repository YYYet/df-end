package com.ruoyi.project.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.Hutool;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.domain.FilterTemplate;
import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Resource
    K3CloudApi api;

    @SaCheckLogin
    @GetMapping("/deliveryApplicationTemplateInformationByUserOrg")
    public AjaxResult getDeliveryApplicationTemplateInformationByUserOrg() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<FilterTemplate> maps = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            FilterTemplate filterTemplate = new FilterTemplate();
            filterTemplate.setTemplateName("测试模板"+i);
            filterTemplate.setTemplateNumber(i+"");
            maps.add(filterTemplate);
        }

        ajax.put("result", maps);
        return ajax;
    }

    @SaCheckLogin
    @GetMapping("/orderWarehouseByUserOrg")
    public AjaxResult getOrderWarehouseByUserOrg() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<FilterTemplate> maps = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            FilterTemplate filterTemplate = new FilterTemplate();
            filterTemplate.setTemplateName("测试仓库"+i);
            filterTemplate.setTemplateNumber(i+"");
            maps.add(filterTemplate);
        }

        ajax.put("result", maps);
        return ajax;
    }
    @SaCheckLogin
    @GetMapping("/distributionCenterByUserOrg")
    public AjaxResult getDistributionCenterByUserOrg() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<FilterTemplate> maps = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            FilterTemplate filterTemplate = new FilterTemplate();
            filterTemplate.setTemplateName("测试配送中心"+i);
            filterTemplate.setTemplateNumber(i+"");
            maps.add(filterTemplate);
        }

        ajax.put("result", maps);
        return ajax;
    }
    @SaCheckLogin
    @GetMapping("/agentByUserOrg")
    public AjaxResult getAgentByUserOrg() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<FilterTemplate> maps = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            FilterTemplate filterTemplate = new FilterTemplate();
            filterTemplate.setTemplateName("测试经办人"+i);
            filterTemplate.setTemplateNumber(i+"");
            maps.add(filterTemplate);
        }

        ajax.put("result", maps);
        return ajax;
    }
    @SaCheckLogin
    @GetMapping("/deliveryMethodNameByUserOrg")
    public AjaxResult getDeliveryMethodNameByUserOrg() throws UnsupportedEncodingException, JsonProcessingException {
        AjaxResult ajax = AjaxResult.success();
        List<FilterTemplate> maps = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            FilterTemplate filterTemplate = new FilterTemplate();
            filterTemplate.setTemplateName("测试配送方式"+i);
            filterTemplate.setTemplateNumber(i+"");
            maps.add(filterTemplate);
        }

        ajax.put("result", maps);
        return ajax;
    }





}
