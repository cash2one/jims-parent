package com.jims.register.api;

import com.jims.clinic.entity.ClinicMaster;

/**
 * Created by Administrator on 2016/5/23.
 * 退号API接口
 * @author zhaoning
 */
public interface ClinicReturnedAcctServiceApi {

    /**
     * 根据 就诊日期和就诊序号 查询 有关退号的基本信息
     * @param visitDate
     * @param clinicNo
     * @return
     * @author zhaoning
     */
    public ClinicMaster getClinicMaster(String visitDate,Integer clinicNo);

    /**
     * 退号
     * @param id
     * @return
     * @author zhaoning
     */
    public String returnedAcctInfo(String id);
}
