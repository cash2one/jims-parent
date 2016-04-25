/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 会诊记录DAO接口
 * @author zhaoning
 * @version 2016-04-23
 */
@MyBatisDao
public interface ElectronGroupConsultationDao extends CrudDao<ElectronGroupConsultation> {
	
}