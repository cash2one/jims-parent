package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicMaster;

import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 * 病人就诊记录Api
 *@author zhaonig
 * @version 2016-04-22
 */
public interface ClinicMasterServiceApi {
    /**
     * 根据当前登录人所在科室查询 病人列表
     * @param visitDept
     * @return
     */
    public List<ClinicMaster> getClinicMaster(String visitDept);
}
