/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysCompany;

import java.util.List;

/**
 * 组织结构DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface SysCompanyDao extends CrudDao<SysCompany> {

    /**
     * 创建组织机构
     * @param sysCompany
     * @return
     */
 //   public int createSysCompany(SysCompany sysCompany);

   /* public List<SysCompany> findAllList();*/

    public List<SysCompany> findListByName();


}