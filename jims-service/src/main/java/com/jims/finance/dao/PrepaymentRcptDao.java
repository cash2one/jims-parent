package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.PrepaymentRcpt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预交金记录DAO接口
 * @author CTQ
 * @version 2016-05-25
 */
@MyBatisDao
public interface PrepaymentRcptDao extends CrudDao<PrepaymentRcpt> {
    /**
     * 根据参数查询预交金列表
     * @param patientId
     * @author CTQ
     * @date 2016-05-30 14:49:19
     * @return
     */
    public List<PrepaymentRcpt> findByPatientId(@Param("patientId") String patientId);
	
}