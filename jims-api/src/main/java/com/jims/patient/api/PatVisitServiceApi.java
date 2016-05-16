package com.jims.patient.api;

import com.jims.patient.Dto.PatientListDto;
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
    public List<PatientListDto> getPatientList(String deptCode);

}
