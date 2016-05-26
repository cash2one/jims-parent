package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescDetailTemp;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 待发处方门诊明细DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescDetailTempDao extends CrudDao<DrugPrescDetailTemp> {

    /**
     * 通过主表Id药品信息
     * @param masterId
     * @return
     */
    public List<DrugPrescDetailTemp> getDetail(@Param(value = "masterId")String masterId);

    /**
     * 确认发药
     * @param masterId
     * @return
     */
    public int deleteDetail(@Param(value = "masterId")String masterId);
}