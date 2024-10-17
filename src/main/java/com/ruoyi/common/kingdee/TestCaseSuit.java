package com.ruoyi.common.kingdee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ com.ruoyi.common.kingdee.material.BdMaterialTest.class,
        com.ruoyi.common.kingdee.purpurchaseorder.PurPurchaseOrderTest.class, com.ruoyi.common.kingdee.accountbalance.GlrptAccoutBalanceTest.class,
        com.ruoyi.common.kingdee.material.bdMaterialFlexTest.class,com.ruoyi.common.kingdee.purpurchaseorder.PurPurchaseOrderPushTest.class,
        com.ruoyi.common.kingdee.saleorder.SavexSaleOrder.class,com.ruoyi.common.kingdee.customer.BdCustomerTest.class
})



public class TestCaseSuit {
}
