/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">EMR</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.EmrDataIcd10ServiceApi;
import com.jims.clinic.dao.EmrDataIcd10Dao;
import com.jims.clinic.entity.EmrDataIcd10;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ICD10编码Service
 * @author zhaoning
 * @version 2015-12-04
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class EmrDataIcd10ServiceImpl extends CrudImplService<EmrDataIcd10Dao, EmrDataIcd10> implements EmrDataIcd10ServiceApi {

/*	public EmrDataIcd10 get(String id) {
		return super.get(id);
	}

	
	public List<EmrDataIcd10> findList(Page<EmrDiagnosis> page,EmrDataIcd10 emrDataIcd10) {

		return super.findList(emrDataIcd10);
	}
*/
	/*public Page<EmrDataIcd10> findPage(Page<EmrDataIcd10> page, EmrDataIcd10 emrDataIcd10) {
		return super.findPage(page, emrDataIcd10);
	}

	@Transactional(readOnly = false)
	public String save(EmrDataIcd10 emrDataIcd10) {
		return  super.save(emrDataIcd10);
	}
	
	@Transactional(readOnly = false)
	public String delete(EmrDataIcd10 emrDataIcd10) {

		return super.delete(emrDataIcd10);
	}*/
	
}