
package com.jims.clinic.service;

import com.jims.clinic.api.ElectronEnterHospitalServiceApi;
import com.jims.clinic.bo.ElectronEnterHospitalBo;
import com.jims.clinic.dao.ElectronEnterHospitalDao;
import com.jims.doctor.diagnosis.bo.EmrDiagnosisBo;
import com.jims.doctor.diagnosis.dao.EmrDiagnosisDao;
import com.jims.clinic.entity.ElectronEnterHospital;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 病历文书--入院记录Service
 * @author zhaonig
 * @version 2016-04-20
 */
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")

public  class ElectronEnterHospitalServiceImpl  implements ElectronEnterHospitalServiceApi {
    @Autowired
	private ElectronEnterHospitalBo electronEnterHospitalBo;
	@Autowired
	private EmrDiagnosisBo emrDiagnosisBo;

	/**
	 * 保存病历文书
	 * @param electronEnterHospital
	 * @author pq
	 * @return
	 */
	public String saveEnter(ElectronEnterHospital electronEnterHospital){
       String num = "";
		num = electronEnterHospitalBo.saveEnter(electronEnterHospital);
		return num;
	}

	/**
	 * 查询病历文书
	 * @param electronEnterHospital
	 * @author pq
	 * @return
	 */
	@Override
	public ElectronEnterHospital getElectronEnteHos(ElectronEnterHospital electronEnterHospital) {
		return electronEnterHospitalBo.getElectronEnteHos(electronEnterHospital);
	}


}