package com.ruoyi.project.system.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FrontApplyGoodBill {
    private String applyDate;
    private String arrivalDate;
    private String applyOrgNumber;
    private String reviceOrgNumber;
    private String note;
    private ArrayList<FrontApplyGoodEntry> entry;
}
