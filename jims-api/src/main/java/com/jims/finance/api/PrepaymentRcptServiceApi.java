package com.jims.finance.api;

import com.jims.finance.entity.PrepaymentRcpt;

/**
 * 预交金记录Service
 * @author CTQ
 * @version 2016-05-25
 */

public interface PrepaymentRcptServiceApi{


    public PrepaymentRcpt findByPatientId(String patientId);
	
}