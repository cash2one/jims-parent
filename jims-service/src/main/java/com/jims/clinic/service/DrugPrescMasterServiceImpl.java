package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DrugPrescServiceApi;
import com.jims.clinic.api.DrugPrescTempServiceApi;
import com.jims.clinic.dao.DrugPrescDetailDao;
import com.jims.clinic.dao.DrugPrescMasterDao;
import com.jims.clinic.dao.DrugPrescMasterTempDao;
import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.clinic.entity.DrugPrescMaster;
import com.jims.clinic.entity.DrugPrescMasterTemp;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品处方主表
 *
 * @author PangQian
 * @date2016/5/26 0026
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugPrescMasterServiceImpl extends CrudImplService<DrugPrescMasterDao, DrugPrescMaster> implements DrugPrescServiceApi {
@Autowired
private DrugPrescDetailDao drugPrescDetailDao;

    /**
     * 查询药品子表的列表
     * @param masterId
     * @return
     */
    public List<DrugPrescDetail> findDrugDetail(String masterId){
      return drugPrescDetailDao.findDrugDetail(masterId);
    }


}
