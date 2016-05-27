package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 药品处方明细表DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescDetailDao extends CrudDao<DrugPrescDetail> {
    /**
     * 查询药品处方明细表的列表信息
     * @param masterId
     * @return
     */
    public List<DrugPrescDetail> findDrugDetail(@Param(value = "masterId")String masterId);
}