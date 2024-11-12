package com.ruoyi.project.system.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ApplyGoodsBillEntry {
    private int FEntryID;
    private BillNumberEntity FMaterialId;
    private BillNumberEntity FUnitId;
    private double FMaxPOQty;
    private double FMinPOQty;
    private double FIncreaseQty;
    private double FReqQty;
    private double FApproveQty;
    private int FLeadTime;
    private double FAuxQty;
    private String FSuggestPurDate;
    private double FExtAuxQty;
    private double FApplyBaseQty;
    private double FLowLimitePrice;
    private String FArrivalDate;
    private String FSrcBillTypeId;
    private BillNumberEntity FBaseUnitId;
    private double FActualApproveQty;
    private double FActualApproveBaseQty;
    private double FJNSumQty;
    private double FAUXCDSumJNQty;
    private double FConversionRate;
    private String FSrcBillNo;
    private BillNumberEntity FDispatchOrgIdDetail;
    private String FIsPurchase;
    private String FIsProduce;
    private double FDeliveryMaxQty;
    private double FBaseDeliveryMaxQty;
    private boolean FISPresent;
    private double FTaxPrice;
    private double FTaxAmount;
    private double FTaxRate;
    private double FAllAmount;
    private double FAllAmount_LC;
    private boolean FDetailIntax;
    private String FIsSelfPurchase;
    private String FOWNERTYPEID = "BD_OwnerOrg";
    private BillNumberEntity FOWNERID;
    private String FKEEPERTYPEID = "BD_KeeperOrg";
    private BillNumberEntity FKEEPERID;
    private double FExpectQty;
    private double FProductJNQty;
    private String FRowGoodsStatus = "F";

}
