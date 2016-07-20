package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugExportDetail;

import java.util.List;

/**
 * 药品出库明细记录DAO接口
 * @author lgx
 * @version 2016-05-23
 */
@MyBatisDao
public interface DrugExportDetailDao extends CrudDao<DrugExportDetail> {

    /**
     * 查询出库详情
     * @return
     */
    public List<DrugExportDetail> findDetailList(String documentNo);
	
}