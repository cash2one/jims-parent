/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ElectronLeaveHopitalServiceApi;
import com.jims.clinic.bo.ElectronLeaveHospitalBo;
import com.jims.clinic.dao.ElectronLeaveHospitalDao;
import com.jims.clinic.entity.ElectronLeaveHospital;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出院记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")
public class ElectronLeaveHospitalServiceImpl implements ElectronLeaveHopitalServiceApi {

	@Autowired
	private ElectronLeaveHospitalBo electronLeaveHospitalBo;

	/**
	 * 根据住院ID查询出院记录
 	 * @param electronLeaveHospital
	 * @return
	 * @Author zhaoning
	 * @version 2016-04-21
	 */
	@Override
	public ElectronLeaveHospital getLeaveByVisit(ElectronLeaveHospital electronLeaveHospital) {
		return  electronLeaveHospitalBo.getLeaveByVisit(electronLeaveHospital);
	}

	/**
	 * 保存
	 * @param electronLeaveHospital
	 * @return
	 * @Author zhaoning
	 * @version 2016-04-21
	 */
	public String save(ElectronLeaveHospital electronLeaveHospital) {
		return  electronLeaveHospitalBo.save(electronLeaveHospital);
	}

}