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
     * 查询父机构
     * @return
     */
    public List<SysCompany> findListByName();

    /**
     * 查询组织机构名称是否存在
     *
     * @param orgName
     * @return
     */
    public SysCompany findByName(String orgName);

    /**
     * 保存方法（返回id）
     *
     * @param sysCompany
     */
    public Integer insertReturnId(SysCompany sysCompany);
    /**
     * 创建组织机构
     *
     * @param sysCompany
     * @return
     */
    public String save(SysCompany sysCompany);

    /**
     * 根据创建人查询组织机构名称
     * @return
     */
    public SysCompany findNameByOwner(String loginName);

}