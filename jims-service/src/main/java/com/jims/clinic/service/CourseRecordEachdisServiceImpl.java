/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.dao.CourseRecordEachdisDao;
import com.jims.clinic.entity.CourseRecordEachdis;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 病程记录---每日病程记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class CourseRecordEachdisServiceImpl extends CrudImplService<CourseRecordEachdisDao, CourseRecordEachdis> {

}