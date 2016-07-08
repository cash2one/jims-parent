package com.jims.clinic.dto;

/**
 * Created by Administrator on 2016/7/7.
 * 病案首页 费用 DTO
 * @author zhaoning
 */
public class InpBillDetailDto {
    private Double generalMedicCosts;//一般医疗服务费
    private Double generalTreatOperCosts;//一般治疗操作费
    private Double nursingCosts;//护理费
    private Double otherCostsMedicServ;//综合医疗服务其他费用
    private Double pathologicalDiagCosts;//病历诊断费用
    private Double laboratoryDiagCosts;//实验室诊断费用
    private Double imagingDiagCosts;//影像学诊断费用
    private Double clinicalDiagCosts;//临床诊断费用
    private Double nonSurgicalTreatCosts;//非手术治疗（含临床物理治疗费）
    private Double llinicalPhysicalCosts;//临床物理治疗费
    private Double operationMedCosts;//手术治疗费用（含麻醉费，手术费）
    private Double anesthesiaCosts;//麻醉费用
    private Double operationCosts;//手术费
    private Double rehabilitationCosts;//康复费用
    private Double chinaMedTreatCosts;//中医治疗费用
    private Double westMedicCosts;//西医费用(含抗菌药物费用)
    private Double antimicrobialAgentsCOsts;//抗菌药物费用
    private Double chinaAgentCosts;//中成药费用
    private Double chinaHerbalCosts;//中草药费用
    private Double bloodCosts;//血费
    private Double albuminCosts;//白蛋白费用
    private Double globulinCosts;//球蛋白费用
    private Double clottingFactorCosts;//凝血因子费用
    private Double cytokinesCosts;//细胞因子费用
    private Double disposableMaterExamCosts;//检查一次性用料费用
    private Double disposableMaterTreatCosts;//治疗一次性用料费用
    private Double disposableMaterOpertCosts;//手术一次性用料费用
    private Double otherCosts;//其他费用
    private Double totalCosts;//总费用
    private String patientId;//病人Id
    private String visitId;//住院次数

    public Double getGeneralMedicCosts() {
        return generalMedicCosts;
    }

    public void setGeneralMedicCosts(Double generalMedicCosts) {
        this.generalMedicCosts = generalMedicCosts;
    }

    public Double getGeneralTreatOperCosts() {
        return generalTreatOperCosts;
    }

    public void setGeneralTreatOperCosts(Double generalTreatOperCosts) {
        this.generalTreatOperCosts = generalTreatOperCosts;
    }

    public Double getNursingCosts() {
        return nursingCosts;
    }

    public void setNursingCosts(Double nursingCosts) {
        this.nursingCosts = nursingCosts;
    }

    public Double getOtherCostsMedicServ() {
        return otherCostsMedicServ;
    }

    public void setOtherCostsMedicServ(Double otherCostsMedicServ) {
        this.otherCostsMedicServ = otherCostsMedicServ;
    }

    public Double getPathologicalDiagCosts() {
        return pathologicalDiagCosts;
    }

    public void setPathologicalDiagCosts(Double pathologicalDiagCosts) {
        this.pathologicalDiagCosts = pathologicalDiagCosts;
    }

    public Double getLaboratoryDiagCosts() {
        return laboratoryDiagCosts;
    }

    public void setLaboratoryDiagCosts(Double laboratoryDiagCosts) {
        this.laboratoryDiagCosts = laboratoryDiagCosts;
    }

    public Double getImagingDiagCosts() {
        return imagingDiagCosts;
    }

    public void setImagingDiagCosts(Double imagingDiagCosts) {
        this.imagingDiagCosts = imagingDiagCosts;
    }

    public Double getClinicalDiagCosts() {
        return clinicalDiagCosts;
    }

    public void setClinicalDiagCosts(Double clinicalDiagCosts) {
        this.clinicalDiagCosts = clinicalDiagCosts;
    }

    public Double getNonSurgicalTreatCosts() {
        return nonSurgicalTreatCosts;
    }

    public void setNonSurgicalTreatCosts(Double nonSurgicalTreatCosts) {
        this.nonSurgicalTreatCosts = nonSurgicalTreatCosts;
    }

    public Double getLlinicalPhysicalCosts() {
        return llinicalPhysicalCosts;
    }

    public void setLlinicalPhysicalCosts(Double llinicalPhysicalCosts) {
        this.llinicalPhysicalCosts = llinicalPhysicalCosts;
    }

    public Double getOperationMedCosts() {
        return operationMedCosts;
    }

    public void setOperationMedCosts(Double operationMedCosts) {
        this.operationMedCosts = operationMedCosts;
    }

    public Double getAnesthesiaCosts() {
        return anesthesiaCosts;
    }

    public void setAnesthesiaCosts(Double anesthesiaCosts) {
        this.anesthesiaCosts = anesthesiaCosts;
    }

    public Double getOperationCosts() {
        return operationCosts;
    }

    public void setOperationCosts(Double operationCosts) {
        this.operationCosts = operationCosts;
    }

    public Double getRehabilitationCosts() {
        return rehabilitationCosts;
    }

    public void setRehabilitationCosts(Double rehabilitationCosts) {
        this.rehabilitationCosts = rehabilitationCosts;
    }

    public Double getChinaMedTreatCosts() {
        return chinaMedTreatCosts;
    }

    public void setChinaMedTreatCosts(Double chinaMedTreatCosts) {
        this.chinaMedTreatCosts = chinaMedTreatCosts;
    }

    public Double getWestMedicCosts() {
        return westMedicCosts;
    }

    public void setWestMedicCosts(Double westMedicCosts) {
        this.westMedicCosts = westMedicCosts;
    }

    public Double getAntimicrobialAgentsCOsts() {
        return antimicrobialAgentsCOsts;
    }

    public void setAntimicrobialAgentsCOsts(Double antimicrobialAgentsCOsts) {
        this.antimicrobialAgentsCOsts = antimicrobialAgentsCOsts;
    }

    public Double getChinaAgentCosts() {
        return chinaAgentCosts;
    }

    public void setChinaAgentCosts(Double chinaAgentCosts) {
        this.chinaAgentCosts = chinaAgentCosts;
    }

    public Double getChinaHerbalCosts() {
        return chinaHerbalCosts;
    }

    public void setChinaHerbalCosts(Double chinaHerbalCosts) {
        this.chinaHerbalCosts = chinaHerbalCosts;
    }

    public Double getBloodCosts() {
        return bloodCosts;
    }

    public void setBloodCosts(Double bloodCosts) {
        this.bloodCosts = bloodCosts;
    }

    public Double getAlbuminCosts() {
        return albuminCosts;
    }

    public void setAlbuminCosts(Double albuminCosts) {
        this.albuminCosts = albuminCosts;
    }

    public Double getGlobulinCosts() {
        return globulinCosts;
    }

    public void setGlobulinCosts(Double globulinCosts) {
        this.globulinCosts = globulinCosts;
    }

    public Double getClottingFactorCosts() {
        return clottingFactorCosts;
    }

    public void setClottingFactorCosts(Double clottingFactorCosts) {
        this.clottingFactorCosts = clottingFactorCosts;
    }

    public Double getCytokinesCosts() {
        return cytokinesCosts;
    }

    public void setCytokinesCosts(Double cytokinesCosts) {
        this.cytokinesCosts = cytokinesCosts;
    }

    public Double getDisposableMaterExamCosts() {
        return disposableMaterExamCosts;
    }

    public void setDisposableMaterExamCosts(Double disposableMaterExamCosts) {
        this.disposableMaterExamCosts = disposableMaterExamCosts;
    }

    public Double getDisposableMaterTreatCosts() {
        return disposableMaterTreatCosts;
    }

    public void setDisposableMaterTreatCosts(Double disposableMaterTreatCosts) {
        this.disposableMaterTreatCosts = disposableMaterTreatCosts;
    }

    public Double getDisposableMaterOpertCosts() {
        return disposableMaterOpertCosts;
    }

    public void setDisposableMaterOpertCosts(Double disposableMaterOpertCosts) {
        this.disposableMaterOpertCosts = disposableMaterOpertCosts;
    }

    public Double getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(Double otherCosts) {
        this.otherCosts = otherCosts;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }
}
