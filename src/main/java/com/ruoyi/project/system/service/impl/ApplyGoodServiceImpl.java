package com.ruoyi.project.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.project.system.domain.*;
import com.ruoyi.project.system.service.ApplyGoodService;
import com.ruoyi.project.system.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApplyGoodServiceImpl extends BaseService implements ApplyGoodService {
    @Override
    public ApplyGoodsBill buildSaveOrUpdateDto(boolean isUpdate,FrontApplyGoodBill bill) {
        ArrayList<ApplyGoodsBillEntry> applyGoodsBillEntries = new ArrayList<>();

        for (FrontApplyGoodEntry item : bill.getEntry()) {
//            if (item.getNums() == 0){
//                continue;
//            }
            ApplyGoodsBillEntry entryItem = ApplyGoodsBillEntry.builder().FMaterialId(new BillNumberEntity(item.getMaterialNumber()))
                    .FUnitId(new BillNumberEntity(item.getUnitNumber()))
                    .FMaxPOQty(100000.0000000000)
                    .FMinPOQty(0.0)
                    .FIncreaseQty(0.0)
                    .FReqQty(item.getNums())
                    .FApproveQty(item.getNums())
                    .FLeadTime(0)
                    .FAuxQty(0.0)
                    // 建议配送日期
//                .FSuggestPurDate("2024-11-07 00:00:00")
                    .FExtAuxQty(0.0)
                    .FApplyBaseQty(item.getNums())
                    .FLowLimitePrice(0.0)
                    // 到货日期
                    .FArrivalDate(bill.getArrivalDate())
                    .FSrcBillTypeId("")
                    .FBaseUnitId(new BillNumberEntity(item.getUnitNumber()))
                    .FActualApproveQty(0.0)
                    .FActualApproveBaseQty(0.0)
                    .FJNSumQty(0.0)
                    .FAUXCDSumJNQty(0.0)
                    .FConversionRate(0.0)
                    .FSrcBillNo("")
                    // 配送组织
                    .FDispatchOrgIdDetail(new BillNumberEntity(config.getDistributionCenterCode()))
                    .FIsPurchase("0")
                    .FIsProduce("0")
                    .FDeliveryMaxQty(item.getNums())
                    .FBaseDeliveryMaxQty(item.getNums())
                    .FISPresent(false)
                    .FTaxPrice(0.0)
                    .FTaxAmount(0.0)
                    .FTaxRate(0.0)
                    .FAllAmount(0.0)
                    .FAllAmount_LC(0.0)
                    .FDetailIntax(false)
                    .FIsSelfPurchase("1")
                    .FOWNERTYPEID("BD_OwnerOrg")
                    .FOWNERID(new BillNumberEntity(config.getDistributionCenterCode()))
                    .FKEEPERTYPEID("BD_KeeperOrg")
                    .FKEEPERID(new BillNumberEntity(config.getDistributionCenterCode()))
                    .FExpectQty(0.0)
                    .FProductJNQty(0.0)
                    .FRowGoodsStatus("F")
                    .build();

            if (isUpdate){
                entryItem.setFEntryID(item.getEntryId());
            }

            applyGoodsBillEntries.add(entryItem);
        }





        ApplyGoodsModel model = ApplyGoodsModel.builder().FBillTypeID(new BillNumberEntity("YH01_JGLYHSQ"))
                .FRequestType("Material")
                .FNote(bill.getNote())
                .F_UC_tempName(bill.getTempName())
                .F_UC_tempNo(bill.getTempNo())
                .FApplicationOrgId(new BillNumberEntity(bill.getApplyOrgNumber())).FCurrencyId(new BillNumberEntity("PRE001"))
                .FReceiveOrgId(new BillNumberEntity(bill.getReviceOrgNumber())).FAppDate(DateUtil.now()).FIsIncludedTax(false)
                .FIsOfflinePay(false).FMobileOrderType("1").FDeliveryControl(false).FIsAgentPurchase(false).FGoodsStatus("F")
                .FIsRushOrder(false).FEntity(applyGoodsBillEntries).build();

        if (isUpdate){
             model.setFID(bill.getBillId());
        }

        return ApplyGoodsBill
                .builder()
                .IsDeleteEntry("true")
                .SubSystemId("")
                .IsVerifyBaseDataField("false")
                .IsEntryBatchFill("true")
                .ValidateFlag("true").NumberSearch("true")
                .IsAutoAdjustField("true").InterationFlags("").IgnoreInterationFlag("")
                .IsControlPrecision("false")
                .ValidateRepeatJson("false")
                .Model(model).build();
    }
}
