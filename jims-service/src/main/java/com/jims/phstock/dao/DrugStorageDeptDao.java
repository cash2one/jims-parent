package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugStorageDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 药品库存单位DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugStorageDeptDao extends CrudDao<DrugStorageDept> {

    /**
     * 根据等级的判断条件检索
     * @param condition 等级条件 例如： remarks>'1'
     * @param orgId
     * @param q 模糊检索
     * @return
     */
    public List<DrugStorageDept> findListByLevel(@Param("condition")String condition,
                                                 @Param("orgId")String orgId,
                                                 @Param("q")String q);
}