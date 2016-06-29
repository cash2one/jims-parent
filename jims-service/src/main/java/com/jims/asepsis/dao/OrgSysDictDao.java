package com.jims.asepsis.dao;

import com.jims.asepsis.entity.OrgSysDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 包单位维护Dao
* @author yangruidong
* @version 2016-06-28
*/
@MyBatisDao
public interface OrgSysDictDao extends CrudDao<OrgSysDict> {

    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict> findUnits(OrgSysDict entity);
}