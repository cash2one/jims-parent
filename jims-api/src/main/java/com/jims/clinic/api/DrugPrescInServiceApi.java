package com.jims.clinic.api;

/**
 * 住院处方发药
 *
 * @author PangQian
 * @date2016/5/30 0030
 */
public interface DrugPrescInServiceApi {
    /**
     * 方法 confirmInDrugPresc 的功能描述
     * 住院发药 的确认（1 给药品处方主表和药品明细表插入 inp_bill_detail 2 update doct_drug_presc_master、drug_stock）
     * @paam
     * @return
     * @author pq
     * @date 2016/5/30 0030
     */
    public  String confirmInDrugPresc(String masterId);


}
