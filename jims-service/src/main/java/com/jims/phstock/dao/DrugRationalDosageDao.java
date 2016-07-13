package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugRationalDosage;

import java.util.List;

/**
 * 药品用量信息dao
 * Created by fyg on 2016/7/12.
 */
@MyBatisDao
public interface DrugRationalDosageDao extends CrudDao<DrugRationalDosage> {

    /**
     * 查询所有药品用量信息情况
     * @return
     * @author fengyuguang
     */
    public List<DrugRationalDosage> findAll();
}
