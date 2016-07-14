package com.jims.common.utils;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.service.ClinicMasterServiceImpl;

/**
 * 号码生成规则
 */
public class NumberUtils {
    private static ClinicMasterServiceImpl clinicMasterService = SpringContextHolder.getBean(ClinicMasterServiceImpl.class);


    /**
     * 获取门诊检查申请号
     * @param clinicId
     * @return
     */
    public static String getClinicInspect(String clinicId) {
        ClinicMaster clinicMaster=clinicMasterService.get(clinicId);
        String testNo="JC"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        return testNo;
    }
    /**
     * 获取门诊检验申请号
     * @param clinicId
     * @return
     */
    public static String getClinicLab(String clinicId) {
        ClinicMaster clinicMaster=clinicMasterService.get(clinicId);
        String testNo="JY"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        return testNo;
    }
    /**
     * 获取门诊处方申请号
     * @param clinicId
     * @return
     */
    public static String getClinicPrescription(String clinicId) {
        ClinicMaster clinicMaster=clinicMasterService.get(clinicId);
        String testNo="CP"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        return testNo;
    }

    /**
     * 获取门诊用血申请号
     * @param clinicId
     * @return
     */
    public static String getClinicUseBlood(String clinicId) {
        ClinicMaster clinicMaster=clinicMasterService.get(clinicId);
        String testNo="YX"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        return testNo;
    }

    /**
     * 获取门诊手术申请号
     * @param clinicId
     * @return
     */
    public static String getClinicOperation(String clinicId) {
        ClinicMaster clinicMaster=clinicMasterService.get(clinicId);
        String testNo="SS"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        return testNo;
    }
}
