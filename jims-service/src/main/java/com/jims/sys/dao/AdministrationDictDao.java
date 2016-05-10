/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 给药途径字典DAO接口
 * @author yangruidong
 *
 */
@MyBatisDao
public interface AdministrationDictDao extends CrudDao<AdministrationDict> {


	
}
