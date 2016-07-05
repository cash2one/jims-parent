package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysServiceParam;

import java.util.List;

/**
 * Created by heren on 2016/7/4.
 */
@MyBatisDao
public interface SysServiceParamDao extends CrudDao<SysServiceParam>{
    /**
     *
     * 根据服务ID获取该服务对应的所有参数
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamDao(String serviceId);

    /**
     *
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams);


}
