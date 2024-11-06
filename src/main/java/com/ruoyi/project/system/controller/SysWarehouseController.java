package com.ruoyi.project.system.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONArray;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.service.impl.WarehouseServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/warehouse")
public class SysWarehouseController {
    @Resource
    WarehouseServiceImpl warehouseService;
    @GetMapping("queryWarehouse")
    public AjaxResult queryWarehouse() throws Exception {
        AjaxResult success = AjaxResult.success();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        JSONArray warehouseList = warehouseService.getWarehouseByOrgId(user.getUseOrgId());
        success.put("result", warehouseList);
        return success;
    }
}
