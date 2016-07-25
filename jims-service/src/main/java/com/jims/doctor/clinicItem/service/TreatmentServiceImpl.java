package com.jims.doctor.clinicItem.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.vo.LoginInfo;
import com.jims.doctor.clinicItem.api.TreatmentServiceApi;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.clinicItem.bo.TreatmentBo;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

/**
 * TreatmentServiceImpl
 *
 * @author PangQian
 * @date2016/5/11 0011
 */
@Service(version = "1.0.0")

public class TreatmentServiceImpl  implements TreatmentServiceApi {
    @Autowired
    private TreatmentBo treatmentBo;

    @Override
    public List<OutpTreatRec> findTreatment(String clinicId) {
        return treatmentBo.findTreatment(clinicId);
    }

    @Override
    public String saveClinicItem(List<OutpTreatRec> outpTreatRecs,LoginInfo loginInfo) {
        return treatmentBo.saveClinicItem(outpTreatRecs,loginInfo);
    }

    @Override
    public int deleteTreat(String id) {
        return treatmentBo.deleteTreat(id);
    }
}
