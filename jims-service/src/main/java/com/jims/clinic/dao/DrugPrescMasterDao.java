package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.clinic.entity.DrugPrescMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 药品处方主记录DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescMasterDao extends CrudDao<DrugPrescMaster> {
    /**
     * 保存药品处方主表
     * @param drugPrescMaster
     * @return
     */
    public String saveDrugaster(DrugPrescMaster drugPrescMaster);

    /**
     * 保存药品处方明细表
     * @param drugPrescDetailList
     * @return
     */
    public String saveDrugDetail(List<DrugPrescDetail> drugPrescDetailList);
}