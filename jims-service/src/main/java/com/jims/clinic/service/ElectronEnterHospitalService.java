/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ElectronEnterHospitalApi;
import com.jims.clinic.dao.ElectronEnterHospitalDao;
import com.jims.clinic.entity.ElectronEnterHospital;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 病历文书--入院记录Service
 * @author zhaonig
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public  class ElectronEnterHospitalService extends CrudImplService<ElectronEnterHospitalDao,ElectronEnterHospital> implements ElectronEnterHospitalApi {
    @Autowired
	private ElectronEnterHospitalDao electronEnterHospitalDao;

	/**
	 * 新增、修改 病历文书
	 * @param electronEnterHospital
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveElectronEnterHos(ElectronEnterHospital electronEnterHospital){
      this.save(electronEnterHospital);
	}

  /**
   * 根据住院Id查找入院记录
   * @param electronEnterHospital
   * @return
   */
       public ElectronEnterHospital getEnter(ElectronEnterHospital electronEnterHospital){
	return  electronEnterHospitalDao.getEnter(electronEnterHospital);
       }
}