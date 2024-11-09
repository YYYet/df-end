package com.ruoyi.project.system.domain;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApplyGoodsBill {
    private ArrayList<Object> NeedUpDateFields;
    private ArrayList<Object> NeedReturnFields;
    private String IsDeleteEntry = "true";
    private String SubSystemId = "";
    private String IsVerifyBaseDataField = "false";
    private String IsEntryBatchFill = "true";
    private String ValidateFlag = "true";
    private String NumberSearch = "true";
    private String IsAutoAdjustField = "true";
    private String InterationFlags = "true";
    private String IgnoreInterationFlag = "";
    private String IsControlPrecision = "false";
    private String ValidateRepeatJson = "false";
    private ApplyGoodsModel Model;


//    "Model": {
//        "FID": 0,
//        "FBillTypeID": {
//            "FNUMBER": "YH01_JGLYHSQ"
//        },
//        "FRequestType": "Material",
//        "FDispatchOrgId": {
//            "FNumber": "100"
//        },
//        "FApplicationOrgId": {
//            "FNumber": "100"
//        },
//        "FCurrencyId": {
//            "FNumber": "PRE001"
//        },
//        "FReceiveOrgId": {
//            "FNumber": "100"
//        },
//        "FAppDate": "2024-11-07 00:00:00",
//        "FIsIncludedTax": false,
//        "FIsOfflinePay": false,
//        "FMobileOrderType": "1",
//        "FDeliveryControl": false,
//        "FIsAgentPurchase": false,
//        "FGoodsStatus": "F",
//        "FIsRushOrder": false,
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
