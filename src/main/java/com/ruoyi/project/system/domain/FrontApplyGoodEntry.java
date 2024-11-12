package com.ruoyi.project.system.domain;

import lombok.Data;

@Data
public class FrontApplyGoodEntry {
//    private String materialNumber;
//    private String unitNumber;
//    private double applyNums;
//    private String arrivalDate;

    private Integer createOrgId;
    private Integer groupId;
    private Integer materialId;
    private int entryId;
    private String materialName;
    private String materialNumber;
    private Integer nums;
    private String unitName;
    private String unitNumber;
    private Integer unitID;
    private Integer useOrgId;

}
