
package com.jims.emr.enterHospital.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.enterHospital.api.ElectronEnterHospitalServiceApi;
import com.jims.enterHospital.entity.ElectronEnterHospital;
import com.jims.emr.enterHospital.bo.ElectronEnterHospitalBo;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 病历文书--入院记录Service
 * @author zhaonig
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
public  class ElectronEnterHospitalServiceImpl  implements ElectronEnterHospitalServiceApi {
    @Autowired
	private ElectronEnterHospitalBo electronEnterHospitalBo;


    @Override
    public ElectronEnterHospital getElectronEnteHos(ElectronEnterHospital electronEnterHospital) {
        return electronEnterHospitalBo.getElectronEnteHos(electronEnterHospital);
    }

    @Override
    public String saveEnter(ElectronEnterHospital electronEnterHospital,String LoginName,String orgId) {
        return electronEnterHospitalBo.saveEnter(electronEnterHospital,LoginName,orgId);
    }
}