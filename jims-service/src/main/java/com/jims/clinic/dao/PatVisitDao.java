/**
 *
 */
package com.jims.clinic.dao;


import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.entity.PatVisit;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 病人住院记录信息DAO接口
 * @author che
 * @version 2016-04-20
 */
@MyBatisDao
public interface PatVisitDao extends CrudDao<PatVisit> {
    /**
     * 根据当前登录医生的所在科室ID 查询病人列表(在院)
     * @param deptCode
     * @return
     * @author zhaoning
     */
	public List<PatientListDto> getPatientListInHos(@Param("deptCode")String deptCode);

    /**
     * 根据出院科室和出院日期，查询出院病人列表（一周以内的出院病人）
     * @param deptDischargeFrom
     * @param dischargeDateTime
     * @return
     * @author zhaoning
     */
    public List<PatientListDto> getPatientListOutHos(@Param("deptDischargeFrom")String deptDischargeFrom,@Param("dischargeDateTime")String dischargeDateTime);
}