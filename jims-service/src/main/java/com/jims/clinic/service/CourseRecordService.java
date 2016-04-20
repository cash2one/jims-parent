/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cms.dao.CourseRecordDao;
import com.thinkgem.jeesite.modules.cms.entity.CourseRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 病程主记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class CourseRecordService extends CrudService<CourseRecordDao, CourseRecord> {

	public CourseRecord get(String id) {
		return super.get(id);
	}
	
	public List<CourseRecord> findList(CourseRecord courseRecord) {
		return super.findList(courseRecord);
	}
	
	public Page<CourseRecord> findPage(Page<CourseRecord> page, CourseRecord courseRecord) {
		return super.findPage(page, courseRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseRecord courseRecord) {
		super.save(courseRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseRecord courseRecord) {
		super.delete(courseRecord);
	}
	
}