package com.ruoyi.project.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = new Date("2024/11/10");
        Week week = DateUtil.dayOfWeekEnum(date);
//        System.out.println(date);
//        int dayOfWeek = DateUtil.thisDayOfWeekEnum(date);
//        Week week = DateUtil.thisDayOfWeekEnum();
        System.out.println(week.toChinese());
        System.out.println(week.getIso8601Value());
    }
}
