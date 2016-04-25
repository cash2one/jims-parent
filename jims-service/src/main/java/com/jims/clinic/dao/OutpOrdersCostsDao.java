/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 门诊医生收费明细DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpOrdersCostsDao extends CrudDao<OutpOrdersCosts> {

    /**
     * 根据就诊ID和主记录ID查询明细信息
     * @param clinicId
     * @param masterId
     * @return
     */
    public List<OutpOrdersCosts> getOutpCosts(@Param("clinicId")String clinicId,@Param("masterId")String masterId);
}