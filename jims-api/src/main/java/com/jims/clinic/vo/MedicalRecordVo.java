package com.jims.clinic.vo;

import com.jims.clinic.dto.InpBillDetailDto;

import com.jims.common.persistence.DataEntity;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.operation.entity.OperationSchedule;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 * 病案首页 VO
 * @author zhaoning
 */
public class MedicalRecordVo extends DataEntity<MedicalRecordVo> {
   private PatVisit patVisit;
   private PatMasterIndex patMasterIndex;
   private List<EmrDiagnosis> emrDiagnosises;
   private List<OperationSchedule> operationSchedules;
   private InpBillDetailDto inpBillDetailDto;

    public PatVisit getPatVisit() {
        return patVisit;
    }

    public void setPatVisit(PatVisit patVisit) {
        this.patVisit = patVisit;
    }

    public PatMasterIndex getPatMasterIndex() {
        return patMasterIndex;
    }

    public void setPatMasterIndex(PatMasterIndex patMasterIndex) {
        this.patMasterIndex = patMasterIndex;
    }

    public List<EmrDiagnosis> getEmrDiagnosises() {
        return emrDiagnosises;
    }

    public void setEmrDiagnosises(List<EmrDiagnosis> emrDiagnosises) {
        this.emrDiagnosises = emrDiagnosises;
    }

    public List<OperationSchedule> getOperationSchedules() {
        return operationSchedules;
    }

    public void setOperationSchedules(List<OperationSchedule> operationSchedules) {
        this.operationSchedules = operationSchedules;
    }

    public InpBillDetailDto getInpBillDetailDto() {
        return inpBillDetailDto;
    }

    public void setInpBillDetailDto(InpBillDetailDto inpBillDetailDto) {
        this.inpBillDetailDto = inpBillDetailDto;
    }
}
