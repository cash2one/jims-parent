/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.SysCompany;

import java.util.List;

/**
 * 组织结构DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface OrgDeptPropertyDictDao extends CrudDao<OrgDeptPropertyDict> {


    /**
     * 查询组织机构的名称
     * @return
     */
    public List<OrgDeptPropertyDict> findListByName();


    public List<OrgDeptPropertyDict> findNameByType();


    public List<OrgDeptPropertyDict> findProperty();

}