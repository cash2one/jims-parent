package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统服务DAO接口
 * @author txb
 * @version 2016-05-31
 */
@MyBatisDao
public interface SysServiceDao extends CrudDao<SysService> {
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass);

    /**
     * 检索不同人群的服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @return
     */
    public List<SysService> findServiceWithPrice(@Param("serviceClass")String serviceClass,@Param("serviceType")String serviceType);
}