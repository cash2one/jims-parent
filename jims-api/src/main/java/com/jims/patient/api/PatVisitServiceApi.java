package com.jims.patient.api;

import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;

import java.util.List;

/**
 * 病人住院记录信息api
 * @author Chefj
 * @version 2016-04-20
 */
public interface PatVisitServiceApi {
    /**
     * 新增\修改 病人住院记录信息
     * @param patVisit
     */
   public String  save(PatVisit patVisit );

    /**
     * 查询病人列表---住院(根据当前登录医生的科室查询 所在科室的病人)
     * @return
     * @author zhaoning
     *
     */
    public List<PatientListDto> getPatientList(String deptCode,String status,String patName,String startTime,String endTime);

    /**
     * 点击用血申请获取病人信息通过patient_id获得
     * @param patientId
     * @return
     * @author zhaoning
     */
    public PatVisit getPatientInformation(String patientId);

    /**
     * 查询 所有需要新建病历的病人信息
     * @return
     * @author zhaoning
     */
    public List<PatMasterIndex> getPatMaster(String deptCode);

    /**
     * 确认新建病历
     * @param patId
     * @return
     * @author zhaoning
     */
    public String confirmNewMr(String patId);

    /**
     * 移入病历
     * @param deptCode
     * @return
     * @author zhaoning
     */
    public List<PatMasterIndex> getPatMasterByIn(String deptCode);

    /**
     * 确认 移入病历
     * @param patId
     * @return
     * @author zhaoning
     */
    public String confirmMoveIn(String patId);

    /**
     * 移除病历
     * @param patId
     * @return
     * @author zhaoning
     */
    public String removMr(String patId);

   /**
    * 获取病人信息 --- 住院
    * @param patientId
    * @return
    * @author zhaoning
    */
    public PatMasterIndex getPatMasterIndex(String patientId);

 /**
  * 保存编辑病人信息--住院
  * @param patMasterIndex
  * @return
  * @author zhaoning
  */
   public String savePatInfo(PatMasterIndex patMasterIndex);
}
