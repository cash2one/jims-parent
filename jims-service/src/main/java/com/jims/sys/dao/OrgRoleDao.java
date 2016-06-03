/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgRole;

import java.util.List;

/**
 * 角色表DAO接口
 * @author yangruidong
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgRoleDao extends CrudDao<OrgRole> {


    /**
     * 根据orgId获取所有的角色
     * @return
     */
    public List<OrgRole> findAllList(String orgId);

    /**
     * 根据角色名称模糊查询角色信息
     * @param roleName 角色名称
     * @return 角色信息列表
     * @author fengyuguang
     */
    public List<OrgRole> findByRoleName(String roleName);
	
}