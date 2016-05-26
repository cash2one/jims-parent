package com.jims.register.api;

import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.entity.ClinicAppoints;

import java.util.List;

/**
 * 预约挂号Service
 * @author zhangyao
 * @version 2016-05-20
 */
public interface ClinicAppointsServiceApi {
    /**
     * 保存预约挂号信息
     * @param patMasterIndex
     * @return
     */
    public String saveAppointsRegis(PatMasterIndex patMasterIndex) throws Exception;

    /**
     * 根据条件查询  预约list
     * @param clinicAppoints
     * @return
     */
    public List<ClinicAppoints> findList(ClinicAppoints clinicAppoints);

    /**
     * 获取对象
     * @param id
     * @return
     */
    public ClinicAppoints get(String id);

    public String saveAppointReg(PatMasterIndex patMasterIndex);
}