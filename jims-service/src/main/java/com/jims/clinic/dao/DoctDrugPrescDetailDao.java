package com.jims.clinic.dao;

import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 待发药住院处方明细记录DAO接口
 * @author CTQ
 * @version 2016-05-16
 */
@MyBatisDao
public interface DoctDrugPrescDetailDao extends CrudDao<DoctDrugPrescDetail> {

    /**
     * 根据处方主记录ID查询处方明细列表
     * @param prescMasterId
     * @author CTQ
     * @date 2016年5月16日16:19:11
     * @return
     */
    public List<DoctDrugPrescDetail> findListByPrescMasterId(@Param("prescMasterId")String prescMasterId);

    /**
     * 根据处方主记录ID删除处方明细
     * @param prescMasterId
     * @author CTQ
     * @date 2016年5月16日16:19:11
     * @return
     */
    public Integer removeByMasterId(@Param("prescMasterId")String prescMasterId);
	
}