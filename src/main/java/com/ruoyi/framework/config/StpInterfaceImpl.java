package com.ruoyi.framework.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.utils.cloud.consts.SqlConst;
import com.ruoyi.common.utils.cloud.util.CloudLoginUtil;
import com.ruoyi.project.system.domain.CloudUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    CloudLoginUtil cloudLoginUtil;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Map<String, Object>> maps = new ArrayList<>();
        SaSession session = StpUtil.getSession();
        CloudUser user = (CloudUser)session.get("user");
        String formatSql = String.format(SqlConst.PERMISSIONS, user.getLoginName());
        List<String> list = new ArrayList<String>();
        try {
            maps = cloudLoginUtil.execSql(formatSql);
            for (Map<String, Object> map : maps) {
                list.add(map.get("MENUNUMBER").toString());
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("user");
        return list;
    }

}
