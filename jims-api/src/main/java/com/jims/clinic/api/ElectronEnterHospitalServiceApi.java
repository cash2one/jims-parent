package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronEnterHospital;

/**
 * Created by Administrator on 2016/4/20.
 * 病历文书--入院记录API接口
 * @Author zhaoning
 * @version 2016-04-20
 */
public interface ElectronEnterHospitalServiceApi {

    /**
     * 根据病人住院ID查询入院记录信息
     * @param patVisitId
     * @return
     */
    public ElectronEnterHospital getElectronEnteHos(ElectronEnterHospital electronEnterHospital);

    /**
     * 保存/修改  病历文书--入院记录
      * @param electronEnterHospital
     */
    public String saveEnter(ElectronEnterHospital electronEnterHospital);
}
