/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.examclassdict.entity.ExamClassDict;
import com.thinkgem.jeesite.modules.examclassdict.dao.ExamClassDictDao;

/**
 * ExamClassDictService
 * @author zhangpeng
 * @version 2016-04-26
 */
@Service
@Transactional(readOnly = true)
public class ExamClassDictServiceimpl extends CrudService<ExamClassDictDao, ExamClassDict> {

	public ExamClassDict get(String id) {
		return super.get(id);
	}
	
	public List<ExamClassDict> findList(ExamClassDict examClassDict) {
		return super.findList(examClassDict);
	}
	
	public Page<ExamClassDict> findPage(Page<ExamClassDict> page, ExamClassDict examClassDict) {
		return super.findPage(page, examClassDict);
	}
	
	@Transactional(readOnly = false)
	public void save(ExamClassDict examClassDict) {
		super.save(examClassDict);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExamClassDict examClassDict) {
		super.delete(examClassDict);
	}
	
}