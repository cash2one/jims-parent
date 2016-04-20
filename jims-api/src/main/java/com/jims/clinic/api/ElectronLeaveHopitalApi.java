package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronLeaveHospital;

/**
 * Created by Administrator on 2016/4/20.
 * 出院记录Api接口
 * @Author zhaoning
 * @version 2015-04-20
 */
public interface ElectronLeaveHopitalApi {

    /**
     * 新增\修改 出院记录信息
     * @param electronLeaveHospital
     */
    public void saveElectronLeaveHos(ElectronLeaveHospital electronLeaveHospital);
}
