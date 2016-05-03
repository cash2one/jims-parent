/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
/**
 * 病人就诊记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface ClinicMasterDao extends CrudDao<ClinicMaster> {
}