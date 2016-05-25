package com.jims.finance.api;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.web.impl.BaseDto;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;

import java.util.List;

/**
 * 划价收费
 *
 * @author zhangyao
 * @date2016/5/25
 */
public interface OutPatientCostServiceApi {

    public List<BaseDto> list(String orgId,String clinicNo);

}
