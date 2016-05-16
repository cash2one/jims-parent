/**
 *
 */
package com.jims.clinic.dao;


import com.jims.patient.entity.PatVisit;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 病人住院记录信息DAO接口
 * @author che
 * @version 2016-04-20
 */
@MyBatisDao
public interface PatVisitDao extends CrudDao<PatVisit> {
    /**
     * 点击用血申请获取病人信息通过patient_id获得
     * @param patientId
     * @return
     */
    public PatVisit getPatientInformation(String patientId);
	
}