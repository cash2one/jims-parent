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
     * 根据库存单位代码获取该库存单位下的子库存单位列表
     * @param orgId 所属组织机构ID
     * @param storageCode 库存单位代码
     * @return 子库存单位列表
     * @author fengyuguang
     */
    public List<DrugSubStorageDept> getListByStorageCode(String orgId,String storageCode);

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