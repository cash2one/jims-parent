package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;

/**
 * 机构自定义服务对应菜单DAO接口
 * @author lgx
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgSelfServiceVsMenuDao extends CrudDao<OrgSelfServiceVsMenu> {

    /**
     * 根据自定义服务主键删除
     * @param selfServiceId
     * @return
     */
    public int deleteByServiceId(String selfServiceId);
}