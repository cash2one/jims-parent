package com.jims.clinic.service;


import com.jims.clinic.api.DoctDrugPrescDetailServiceApi;
import com.jims.clinic.dao.DoctDrugPrescDetailDao;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 待发药住院处方明细记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service
@Transactional(readOnly = true)
public class DoctDrugPrescDetailServiceImpl extends CrudImplService<DoctDrugPrescDetailDao, DoctDrugPrescDetail> implements DoctDrugPrescDetailServiceApi{


	
}