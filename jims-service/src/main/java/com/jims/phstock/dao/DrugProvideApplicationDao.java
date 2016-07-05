package com.jims.phstock.dao;

import com.jims.phstock.entity.DrugProvideApplication;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 录入申请Dao
* @author yangruidong
* @version 2016-07-04
*/
@MyBatisDao
public interface DrugProvideApplicationDao extends CrudDao<DrugProvideApplication> {


    /**
     *查询去除重复的申请号
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findDocumentByDistinct(DrugProvideApplication entity);


}