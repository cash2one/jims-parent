/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.patient.entity.PatMasterIndex;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 病人主索引DAO接口
 * @author zhaoning
 * @version 2016-04-19
 */
@MyBatisDao
public interface PatMasterIndexDao extends CrudDao<PatMasterIndex> {

    /**
     * 根据patientId更新数据
     * @author CTQ
     * @date 2016-05-26 11:01:39
     * @param id
     * @return
     */
    public Integer updateInpno(@Param("id")String id);
	
}