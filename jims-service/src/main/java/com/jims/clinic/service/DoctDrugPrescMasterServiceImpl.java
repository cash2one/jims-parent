package com.jims.clinic.service;


import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.dao.DoctDrugPrescMasterDao;
import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 待发药住院处方主记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service
@Transactional(readOnly = true)
public class DoctDrugPrescMasterServiceImpl extends CrudImplService<DoctDrugPrescMasterDao, DoctDrugPrescMaster> implements DoctDrugPrescMasterServiceApi {

	
}