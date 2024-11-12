package com.ruoyi.project.system.service;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.project.system.domain.*;

import java.util.ArrayList;

public interface ApplyGoodService {
    public ApplyGoodsBill buildSaveOrUpdateDto(boolean isUpdate, FrontApplyGoodBill bill);
}
