package com.ruoyi.project.system.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.service.impl.OrderTemplateServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/template")
public class SysTemplateController {
    @Resource
    OrderTemplateServiceImpl orderTemplateService;
    @GetMapping("queryTemplate")
    public AjaxResult queryTemplate() throws Exception {
        AjaxResult success = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        List<Map<String, Object>> templateList = orderTemplateService.getTemplateListByUseOrgId(user.getUseOrgId());
        success.put("result", templateList);
        return success;
    }
}
