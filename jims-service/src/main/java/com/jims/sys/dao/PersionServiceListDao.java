/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员拥有服务DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface PersionServiceListDao extends CrudDao<PersionServiceList> {

    /**
     * 根据persionId查询服务
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(@Param("persionId") String persionId);


    /**
     * 根据persionId查询服务
     * @param persionId
     * @return
     */
    public List<PersionServiceList> findServiceByPersionId(@Param("persionId") String persionId);
	
}