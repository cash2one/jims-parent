package com.jims.clinic.dao;


import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.List;

/**
 * 待发药住院处方主记录DAO接口
 * @author CTQ
 * @version 2016-05-16
 */
@MyBatisDao
public interface DoctDrugPrescMasterDao extends CrudDao<DoctDrugPrescMaster> {
    /**
     * 根据参数查询列表
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
    public List<DoctDrugPrescMaster> findListByParams(DoctDrugPrescMaster doctDrugPrescMaster);
	
}