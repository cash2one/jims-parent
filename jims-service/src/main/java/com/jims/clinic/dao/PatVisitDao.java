/**
 *
 */
package com.jims.clinic.dao;


import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
	public List<PatientListDto> getPatientListInHos(@Param("deptCode")String deptCode,@Param("patName")String patName,@Param("startTime")String  startTime,@Param("endTime")String  endTime);

    /**
     * 根据出院科室和出院日期，查询出院病人列表（一周以内的出院病人）
     * @param deptDischargeFrom 出院科室
     * @return
     * @author zhaoning
     */
    public List<PatientListDto> getPatientListOutHos(@Param("deptDischargeFrom")String deptDischargeFrom,@Param("patName")String patName,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 点击用血申请获取病人信息通过patient_id获得
     * @param patientId
     * @return
     */
    public PatVisit getPatientInformation(String patientId);

    /**
     * @return a    返回类型
     * @Desription: (根据参数删除住院记录)
     * @author CTQ
     * @date 2016/5/30
     */
    public int delVisit(PatVisit patVisit);

    /**
     * 查询 所有需要新建病历的病人信息 根据 当前医生所在科室
     * @return
     * @author zhaoning
     */
    public List<PatMasterIndex> getPatMaster(@Param("deptCode")String deptCode);

    /**
     * 移入病人列表
     * @param deptCode
     * @return
     * @author zhaoning
     */
    public List<PatMasterIndex> getPatMasterByIn(@Param("deptCode")String deptCode);

}