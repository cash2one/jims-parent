package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;

import java.util.List;

/**
 * 入库记录单DAO接口
 * @author lgx
 * @version 2016-05-17
 */
@MyBatisDao
public interface DrugImportMasterDao extends CrudDao<DrugImportMaster> {

    /**
     * 查询入库记录
     * @return
     */
    public List<DrugImportMaster> findMasterList(String orgId,String subStorage, String supplier,String startImportDate,String stopImportDate,String storageCode);




}