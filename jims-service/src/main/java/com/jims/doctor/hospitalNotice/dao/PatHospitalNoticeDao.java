package com.jims.doctor.hospitalNotice.dao;

import com.jims.hospitalNotice.entity.PatHospitalNotice;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
/**
 * 住院通知单DAO接口
 * @author qinlongxin
 * @version 2016-06-01
 */
@MyBatisDao
public interface PatHospitalNoticeDao extends CrudDao<PatHospitalNotice> {

    /**
     * 取消登记更新出院通知单
     * @param patHospitalNotice
     * @author CTQ
     * @return
     */
    public int updateNotice(PatHospitalNotice patHospitalNotice);
	
}