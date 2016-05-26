package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescMasterTemp;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 待发处方门诊主表DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescMasterTempDao extends CrudDao<DrugPrescMasterTemp> {


    /**
     * 查询门诊代发药主记录的列表（已收费的）
     * @param dispensary 发药药局
     * @param dispensarySub 发药子药局
     * @return
     */
    public List<DrugPrescMasterTemp> getPrescMasterTemp(@Param(value = "dispensary")String dispensary,@Param(value = "dispensarySub")String dispensarySub);
}