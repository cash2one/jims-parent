package com.jims.clinic.dao;


import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 根据参数查询最大处方号+1
     * @param visitId
     * @author CTQ
     * @date 2016年5月17日14:25:04
     * @return
     */
    public Integer searchPrescNo(@Param("visitId")String visitId);


    /**
     * 查询住院待发处方
     * @param doctDrugPrescMaster
     * @author pq
     * @date date 2016/5/30 0030
     * @return
     */

   public List<DoctDrugPrescMaster> getDrugMasterList(DoctDrugPrescMaster doctDrugPrescMaster);
	
}