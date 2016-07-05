package com.jims.doctor.prescription.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.prescription.api.DoctDrugPrescDetailServiceApi;
import com.jims.doctor.prescription.bo.DoctDrugPrescDetailBo;
import com.jims.prescription.entity.DoctDrugPrescDetail;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 待发药住院处方明细记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service(version = "1.0.0")

public class DoctDrugPrescDetailServiceImpl implements DoctDrugPrescDetailServiceApi{

    @Autowired
    private DoctDrugPrescDetailBo doctDrugPrescDetailBo;
    @Override
    public List<DoctDrugPrescDetail> findListByPrescMasterId(String prescMasterId) {
        return doctDrugPrescDetailBo.findListByPrescMasterId(prescMasterId);
    }
}