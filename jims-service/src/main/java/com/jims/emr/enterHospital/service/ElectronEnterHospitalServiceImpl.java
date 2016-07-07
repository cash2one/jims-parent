
package com.jims.emr.enterHospital.service;

import com.jims.clinic.api.ElectronEnterHospitalServiceApi;
import com.jims.emr.enterHospital.dao.ElectronEnterHospitalDao;
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

public  class ElectronEnterHospitalServiceImpl extends CrudImplService<ElectronEnterHospitalDao,ElectronEnterHospital> implements ElectronEnterHospitalServiceApi {
    @Autowired
	private ElectronEnterHospitalDao electronEnterHospitalDao;
	@Autowired
	private EmrDiagnosisDao emrDiagnosisDao;

	/**
	 * 保存病历文书
	 * @param electronEnterHospital
	 * @author pq
	 * @return
	 */
	public String saveEnter(ElectronEnterHospital electronEnterHospital){
       int num = 0;

			if (electronEnterHospital!=null) {
			String	str = save(electronEnterHospital);
				num =Integer.parseInt(str==null?"0":str);

				List<EmrDiagnosis> emrDiagnosisList = electronEnterHospital.getDiagnosisList();
				if (emrDiagnosisList!=null) {


				if (emrDiagnosisList.size() > 0) {
					for (int i = 0; i < emrDiagnosisList.size(); i++) {
						EmrDiagnosis diagnosis = emrDiagnosisList.get(i);
						diagnosis.setDiagnosisParent(electronEnterHospital.getId());
						diagnosis.setParentId("0");
						try {
							if (diagnosis.getIsNewRecord()) {
								diagnosis.preInsert();
								num = emrDiagnosisDao.insert(diagnosis);
							} else {
								diagnosis.preUpdate();
								num = emrDiagnosisDao.update(diagnosis);
							}
						} catch (Exception e) {
							return num + "";
						}
						return num + "";
					}
					return num + "";
				}
			}
			}



		return num+"";
	}

	/**
	 * 查询病历文书
	 * @param electronEnterHospital
	 * @author pq
	 * @return
	 */
	@Override
	public ElectronEnterHospital getElectronEnteHos(ElectronEnterHospital electronEnterHospital) {
		return electronEnterHospitalDao.getElectronEnteHos(electronEnterHospital);
	}


}