/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.StaffVsRole;
import com.jims.sys.vo.OrgStaffVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员角色表DAO接口
 * @author yangruidong
 * @version 2016-05-31
 */
@MyBatisDao
public interface StaffVsRoleDao extends CrudDao<StaffVsRole> {


    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<OrgRole> getRole(@Param("staffId") String staffId);


    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<StaffVsRole> findRole(@Param("staffId") String staffId);
	
}