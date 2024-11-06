package com.ruoyi.project.system.domain;

import cn.hutool.json.JSONArray;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Page {
    Integer total;
    JSONArray data;
}
