package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronEnterHospital;

/**
 * Created by Administrator on 2016/4/20.
 * 病历文书--入院记录API接口
 * @Author zhaoning
 * @version 2016-04-20
 */
public interface ElectronEnterHospitalApi {

    /**
     * 保存/修改  病历文书--入院记录
      * @param electronEnterHospital
     */
    public void saveElectronEnterHos(ElectronEnterHospital electronEnterHospital);

    /**
     * 查询  病历文书--入院记录
     * @param electronEnterHospital
     * @return
     */
    public ElectronEnterHospital getEnter(ElectronEnterHospital electronEnterHospital);




}
