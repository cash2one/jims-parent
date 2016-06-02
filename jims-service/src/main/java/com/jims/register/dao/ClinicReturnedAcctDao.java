package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicReturnedAcct;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 退号DAO接口
 * @author 121
 * @version 2016-05-19
 */
@MyBatisDao
public interface ClinicReturnedAcctDao extends CrudDao<ClinicReturnedAcct> {

    /**
     * 根据退号员和退号日期查询退号数
     * @param returnedOperator
     * @param returnedDate
     * @author CTQ
     * @return
     */
    public Double getRetuNum(@Param("returnedOperator")String returnedOperator,@Param("returnedDate")String returnedDate);

}