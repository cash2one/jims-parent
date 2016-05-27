package com.jims.phstock.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugPriceModify;

import java.util.List;

/**
 * 调价记录表DAO接口
 * @author txb
 * @version 2016-05-18
 */
@MyBatisDao
public interface DrugPriceModifyDao extends CrudDao<DrugPriceModify> {
    /**
     * 通过通知生效日期查询调价记录表
     * @param startDate 调价生效开始日期
     * @param endDate  调价生效结束日期
     * @author txb
     * @return
     */
    public List<DrugPriceModify> findModifyListByNoticeEfficientDate(String startDate , String endDate);
}