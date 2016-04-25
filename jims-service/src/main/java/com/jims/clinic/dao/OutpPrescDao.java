/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpPresc;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 处方医嘱明细记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpPrescDao extends CrudDao<OutpPresc> {

    /**
     * 根据就诊记录ID查询病人处方主记录
     * @param clinicId
     * @return
     */
    public List<OutpPresc> getOutpPresc(@Param("clinicId")String clinicId);
}