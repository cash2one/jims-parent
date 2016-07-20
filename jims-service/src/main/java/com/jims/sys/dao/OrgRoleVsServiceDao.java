package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgRoleVsService;

import java.util.List;

/**
 * 角色服务权限DAO接口
 * @author luohk
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgRoleVsServiceDao extends CrudDao<OrgRoleVsService> {
    public OrgRoleVsService find(String serviceId, String roleId, String menuId);

    public List<OrgRoleVsService> findAll();

    public List<OrgRoleVsService> findRoleId(String roleId);

    public List<OrgRoleVsService> findRoleIdAndServiceId(String roleId, String serviceId);

    public List<OrgRoleVsService> del(OrgRoleVsService orgRoleVsService);

    public int deletes(String id);
}