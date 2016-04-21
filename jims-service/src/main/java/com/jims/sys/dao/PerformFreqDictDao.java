package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PerformFreqDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@MyBatisDao
public interface PerformFreqDictDao extends CrudDao<PerformFreqDict>{
    public List<String> findTypeList(PerformFreqDict performFreqDict);
}
