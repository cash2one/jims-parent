package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugImportClassDict;

import java.util.List;

/**
 * 入库类别Dao接口
 * Created by luohk on 2016/5/10.
 */
@MyBatisDao
public interface DrugImportClassDictDao extends CrudDao<DrugImportClassDict> {

    /**
     * 根据适用单位查询所有的入库类型
     * @param storageType 适用单位
     * @return
     * @author luohk
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType);

    /**
     * 获取入库类别列表
     * @return
     */
    public List<DrugImportClassDict> findAll();
}
