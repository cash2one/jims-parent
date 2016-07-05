package com.jims.doctor.prescription.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.prescription.api.DoctDrugPrescMasterServiceApi;
import com.jims.doctor.prescription.bo.DoctDrugPrescMasterBo;
import com.jims.prescription.entity.DoctDrugPrescMaster;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 待发药住院处方主记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service(version = "1.0.0")

public class DoctDrugPrescMasterServiceImpl  implements DoctDrugPrescMasterServiceApi {
    @Autowired
    private DoctDrugPrescMasterBo doctDrugPrescMasterBo;

    @Override
    public DoctDrugPrescMaster get(String id) {
        return doctDrugPrescMasterBo.get(id);
    }

    @Override
    public List<DoctDrugPrescMaster> findListByParams(DoctDrugPrescMaster doctDrugPrescMaster) {
        return doctDrugPrescMasterBo.findListByParams(doctDrugPrescMaster);
    }

    @Override
    public String savePresc(DoctDrugPrescMaster doctDrugPrescMaster) {
        return doctDrugPrescMasterBo.savePresc(doctDrugPrescMaster);
    }

    @Override
    public String deletePresc(String id) {
        return doctDrugPrescMasterBo.deletePresc(id);
    }

    @Override
    public Integer searchPrescNo(String visitId) {
        return doctDrugPrescMasterBo.searchPrescNo(visitId);
    }

    @Override
    public List<DoctDrugPrescMaster> getDrugMasterList(DoctDrugPrescMaster doctDrugPrescMaster) {
        return doctDrugPrescMasterBo.getDrugMasterList(doctDrugPrescMaster);
    }

    @Override
    public String confirmDoctDrugPresc(String id) {
        return doctDrugPrescMasterBo.confirmDoctDrugPresc(id);
    }
}