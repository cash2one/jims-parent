package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.PatsInTransferring;
import org.apache.ibatis.annotations.Param;

/**
 * 转科病人记录DAO接口
 * @author CTQ
 * @version 2016-05-25
 */
@MyBatisDao
public interface PatsInTransferringDao extends CrudDao<PatsInTransferring> {

    /**
     * 根据patientId删除数据
     * @author CTQ
     * @date 2016-05-26 11:01:39
     * @param patientId
     * @return
     */
    public Integer deleteByPatientId(@Param("patientId")String patientId);
	
}