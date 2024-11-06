package com.ruoyi.project.system.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.cloud.consts.SqlConst;
import com.ruoyi.project.system.domain.CloudUser;
import com.ruoyi.project.system.service.BaseService;
import com.ruoyi.project.system.service.OrderTemplateService;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class OrderTemplateServiceImpl extends BaseService implements OrderTemplateService {

    @Override
    public List<Map<String, Object>> getTemplateListByUseOrgId(String useOrgId) throws Exception {
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        Week week = DateUtil.thisDayOfWeekEnum();
        int nowWeek = week.getIso8601Value();
        String nowTime = DateUtil.now();

        Map<String, Object> f = new HashMap<>();
        f.put("useOrgId", user.getUseOrgId());
        f.put("nowTime", nowTime);
        f.put("nowWeek", nowWeek);
        List<Map<String, Object>> maps = cloudLoginUtil.execSql(StrUtil.format(SqlConst.TEMPLATE_LIMIT, f));
        Date today = DateUtil.date();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> item = maps.get(i);
            Date newDate = DateUtil.offsetDay(today, Integer.parseInt(item.get("day").toString()));
            item.put("arrivalDate", newDate);
        }
        return maps;
    }
}
