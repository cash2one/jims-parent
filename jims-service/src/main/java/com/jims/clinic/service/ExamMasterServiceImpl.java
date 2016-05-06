/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamMasterApi;
import com.jims.clinic.dao.ExamMasterDao;
import com.jims.clinic.entity.ExamMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检查主记录Service
 * @author zhangpeng
 * @version 2016-05-04
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamMasterServiceImpl extends CrudImplService<ExamMasterDao, ExamMaster> implements ExamMasterApi{


	@Override
	public List<ExamMaster> getExamAppionts(String patientId) {
		return null;
	}

	@Override
	public void saveExamAppionts(ExamMaster examMaster) {

	}

	@Override
	public Integer deleteExamAppionts(String id) {
		return null;
	}

	@Override
	public Integer getMaxExamNo() {
		return null;
	}

	@Override
	public int batchSave(ExamMaster examMaster) {
		return 0;
	}
}