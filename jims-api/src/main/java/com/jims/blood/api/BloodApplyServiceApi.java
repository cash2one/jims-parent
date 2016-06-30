package com.jims.blood.api;

import com.jims.blood.entity.BloodApply;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/4/28.
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
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveBloodApply(BloodApply bloodApply);
    /**
     * 删除
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);

//    public String getMatchSubNum(String applyNum);
}
