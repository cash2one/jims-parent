package com.jims.nurse.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.nurse.entity.Transfer;

/**
 * 病人在科记录DAO接口
 * @author CTQ
 * @version 2016-06-03
 */
@MyBatisDao
public interface TransferDao extends CrudDao<Transfer> {
    /**
     * 病人转科时更新
     * @param transfer
     * @return
     */
    public int updateInfo(Transfer transfer);
	
}