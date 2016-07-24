package com.jims.blood.api;

import com.jims.blood.entity.BloodApply;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by qinlongxin on 2016/4/28.
 * 用血
 */
public interface BloodApplyServiceApi {
//    /**
//     *点击用血申请获取病人信息通过patient_id获得
//     * @param patientId
//     * @param
//     * @return
//     */
//    public BloodApply getPatientInformation(String patientId);
    /**
     * 根据id查询用血表信息
     * @author zhangyao
     * @version 2016/4/23
     */
    public BloodApply  get(String id);
    /**
     * 查询用血主表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<BloodApply> findPage(Page<BloodApply> page, BloodApply bloodApply);
    /**
     * 保存或编辑(门诊)
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveBloodApply(BloodApply bloodApply);

    /**
     * 保存或编辑(住院)
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveHosBloodApply(BloodApply bloodApply);

    /**
     * 删除门诊用血记录
     * @param ids
     * @return
     */
    public String deleteBloodApply(String ids);

    /**
     * 删除住院用血记录
     * @param ids
     * @return
     */
    public String delHos(String ids);
    /**
     * 删除
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);

    /**
     * 确认用血
     * @param bloodApplies
     * @author pq
     * @return
     */
    public String confirmBlood(List<BloodApply> bloodApplies);


//    public String getMatchSubNum(String applyNum);
}
