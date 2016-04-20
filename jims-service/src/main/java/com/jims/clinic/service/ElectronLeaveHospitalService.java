/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ElectronLeaveHopitalApi;
import com.jims.clinic.dao.ElectronLeaveHospitalDao;
import com.jims.clinic.entity.ElectronEnterHospital;
import com.jims.clinic.entity.ElectronLeaveHospital;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 出院记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class ElectronLeaveHospitalService extends CrudImplService<ElectronLeaveHospitalDao, ElectronLeaveHospital> implements ElectronLeaveHopitalApi {

	@Autowired
	private ElectronLeaveHospitalDao electronLeaveHospitalDao;
	/**
	 * 新增、修改 出院记录信息
	 * @param electronLeaveHospital
	 * @author zhaoning
	 * @version 2016-04-20
	 */
	@Override
	public void saveElectronLeaveHos(ElectronLeaveHospital electronLeaveHospital) {
      this.save(electronLeaveHospital);
	}
}