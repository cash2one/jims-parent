package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugSubStorageDept;

import java.util.List;

/**
 * 药品库存子单位DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugSubStorageDeptDao extends CrudDao<DrugSubStorageDept> {
    /**
     * 通过唯一键获取子库房
     * @param storageCode 父库房编码
     * @param subStorageCode 子库房编码
     * @param orgId 组织机构
     * @return
     * @author txb
     */
    public List<DrugSubStorageDept> findByUnique(String storageCode,String subStorageCode,String orgId);
}