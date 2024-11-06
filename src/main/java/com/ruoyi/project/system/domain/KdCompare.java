package com.ruoyi.project.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class KdCompare {
//    {"FieldName":"F_UC_QSSJ","Compare":"111","Value":"2024-11-06 03:00:00","Left":"","Right":"","Logic":0}
    String FieldName;
    String Compare;
    Object Value;
    String Left;
    String Right;
    Integer Logic;
}
