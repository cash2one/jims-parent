package com.jims.clinic.dao;

import com.jims.clinic.entity.PatHospitalNotice;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
/**
 * 住院通知单DAO接口
 * @author qinlongxin
 * @version 2016-06-01
 */
@MyBatisDao
public interface PatHospitalNoticeDao extends CrudDao<PatHospitalNotice> {
	
}