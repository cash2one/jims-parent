/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysCompany;

/**
 * 组织结构DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface SysCompanyDao extends CrudDao<SysCompany> {
	
}