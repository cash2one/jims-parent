/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 参与会诊信息DAO接口
 * @author zhaoning
 * @version 2016-04-23
 */
@MyBatisDao
public interface ElectronGroupConsultationInDao extends CrudDao<ElectronGroupConsultationIn> {
    /**
     * 根据会诊ID 查询参与会诊信息
     * @param consulaionId
     * @return
     */
    public ElectronGroupConsultationIn getConsultByMain(@Param("consulaionId")String consulaionId);
	
}