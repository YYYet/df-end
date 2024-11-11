package com.ruoyi.project.system.domain;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApplyGoodsModel {
    private Integer FID;
    private BillNumberEntity FBillTypeID = new BillNumberEntity("YH01_JGLYHSQ");
    private String FRequestType = "Material";
//    private BillNumberEntity FDispatchOrgId;
    private BillNumberEntity FApplicationOrgId;
    private BillNumberEntity FCurrencyId;
    private BillNumberEntity FReceiveOrgId;
    private String FAppDate;
    private String F_UC_tempName;
    private String F_UC_tempNo;
    private boolean FIsIncludedTax;
    private boolean FIsOfflinePay;
    private String FMobileOrderType;
    private boolean FDeliveryControl;
    private boolean FIsAgentPurchase;
    private String FGoodsStatus;
    private String FNote;
    private boolean FIsRushOrder;
    private ArrayList<ApplyGoodsBillEntry> FEntity;

//        "FEntity": [
//            {
//                "FMaterialId": {
//                    "FNumber": "1001"
//                },
//                "FUnitId": {
//                    "FNumber": "Pcs"
//                },
//                "FMaxPOQty": 100000.0000000000,
//                "FMinPOQty": 0.0,
//                "FIncreaseQty": 0.0,
//                "FReqQty": 22.0,
//                "FApproveQty": 22.0,
//                "FLeadTime": 0,
//                "FAuxQty": 0.0,
//                "FSuggestPurDate": "2024-11-07 00:00:00",
//                "FExtAuxQty": 0.0,
//                "FApplyBaseQty": 22.0,
//                "FLowLimitePrice": 0.0,
//                "FArrivalDate": "2024-11-07 00:00:00",
//                "FSrcBillTypeId": "",
//                "FBaseUnitId": {
//                    "FNumber": "Pcs"
//                },
//                "FActualApproveQty": 0.0,
//                "FActualApproveBaseQty": 0.0,
//                "FJNSumQty": 0.0,
//                "FAUXCDSumJNQty": 0.0,
//                "FConversionRate": 0.0,
//                "FSrcBillNo": "",
//                "FDispatchOrgIdDetail": {
//                    "FNumber": "101"
//                },
//                "FIsPurchase": "0",
//                "FIsProduce": "0",
//                "FDeliveryMaxQty": 22.0,
//                "FBaseDeliveryMaxQty": 22.0,
//                "FISPresent": false,
//                "FTaxPrice": 0.0,
//                "FTaxAmount": 0.0,
//                "FTaxRate": 0.0,
//                "FAllAmount": 0.0,
//                "FAllAmount_LC": 0.0,
//                "FDetailIntax": false,
//                "FIsSelfPurchase": "1",
//                "FOWNERTYPEID": "BD_OwnerOrg",
//                "FOWNERID": {
//                    "FNumber": "101"
//                },
//                "FKEEPERTYPEID": "BD_KeeperOrg",
//                "FKEEPERID": {
//                    "FNumber": "101"
//                },
//                "FExpectQty": 0.0,
//                "FProductJNQty": 0.0,
//                "FRowGoodsStatus": "F"
//            },
//            {
//                "FMaterialId": {
//                    "FNumber": "1001"
//                },
//                "FUnitId": {
//                    "FNumber": "Pcs"
//                },
//                "FMaxPOQty": 100000.0000000000,
//                "FMinPOQty": 0.0,
//                "FIncreaseQty": 0.0,
//                "FReqQty": 23.0,
//                "FApproveQty": 23.0,
//                "FLeadTime": 0,
//                "FAuxQty": 0.0,
//                "FSuggestPurDate": "2024-11-07 00:00:00",
//                "FExtAuxQty": 0.0,
//                "FApplyBaseQty": 23.0,
//                "FLowLimitePrice": 0.0,
//                "FArrivalDate": "2024-11-07 00:00:00",
//                "FSrcBillTypeId": "",
//                "FBaseUnitId": {
//                    "FNumber": "Pcs"
//                },
//                "FActualApproveQty": 0.0,
//                "FActualApproveBaseQty": 0.0,
//                "FJNSumQty": 0.0,
//                "FAUXCDSumJNQty": 0.0,
//                "FConversionRate": 0.0,
//                "FSrcBillNo": "",
//                "FDispatchOrgIdDetail": {
//                    "FNumber": "101"
//                },
//                "FIsPurchase": "0",
//                "FIsProduce": "0",
//                "FDeliveryMaxQty": 23.0,
//                "FBaseDeliveryMaxQty": 23.0,
//                "FISPresent": false,
//                "FTaxPrice": 0.0,
//                "FTaxAmount": 0.0,
//                "FTaxRate": 0.0,
//                "FAllAmount": 0.0,
//                "FAllAmount_LC": 0.0,
//                "FDetailIntax": false,
//                "FIsSelfPurchase": "1",
//                "FOWNERTYPEID": "BD_OwnerOrg",
//                "FOWNERID": {
//                    "FNumber": "101"
//                },
//                "FKEEPERTYPEID": "BD_KeeperOrg",
//                "FKEEPERID": {
//                    "FNumber": "101"
//                },
//                "FExpectQty": 0.0,
//                "FProductJNQty": 0.0,
//                "FRowGoodsStatus": "F"
//            }
//        ]
//    }
//}
}
