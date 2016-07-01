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
    public String saveAppointsRegis(PatMasterIndex patMasterIndex);

    /**
     * 根据条件查询  预约list
     * @param clinicAppoints
     * @return
     */
    public List<ClinicAppoints> findList(ClinicAppoints clinicAppoints);

    /**
     * 根据条件查询  预约list
     * @param name
     * @param cardNo
     * @param visitDate
     * @return
     * @author zhaoning
     */
    public List<ClinicAppoints> findListAppoints(String name,String cardNo,String visitDate);

    /**
     * 获取对象
     * @param id
     * @return
     * @author zhaoning
     */
    public ClinicAppoints get(String id);

    /**
     * 预约确认保存
     * @param id
     * @return
     */
    public String saveAppointReg(String id);

    /**
     * 删除预约信息
     * @param id
     * @return
     * @author zhaoning
     */
    public String deleteAppoints(String id);

    /**
     * 编辑预约信息
     * @param clinicAppoints
     * @return
     * @author zhaoning
     */
    public String editAppoints(ClinicAppoints clinicAppoints);
}