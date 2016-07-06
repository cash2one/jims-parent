package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronLeaveHospital;

/**
 * Created by Administrator on 2016/4/20.
 * 出院记录Api接口
 * @Author zhaoning
 * @version 2015-04-20
 */
public interface ElectronLeaveHopitalServiceApi {


    /**
     * 根据病人住院ID 查询出院信息
     * @param patVisitId
     * @return
     */
    public ElectronLeaveHospital getLeaveByVisit(ElectronLeaveHospital electronLeaveHospital);

    /**
     * 新增\修改 出院记录信息
     * @param electronLeaveHospital
     */
    public String save(ElectronLeaveHospital electronLeaveHospital);
}
