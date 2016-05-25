package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.api.DrugPrescTempServiceApi;
import com.jims.clinic.dao.DrugPrescMasterTempDao;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.clinic.entity.DrugPrescMasterTemp;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 待发药主记录
 *
 * @author PangQian
 * @date2016/5/24 0024
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugPrescTempServiceImpl extends CrudImplService<DrugPrescMasterTempDao, DrugPrescMasterTemp> implements DrugPrescTempServiceApi {

    /**
     * 拿到最近一个月的待发药主记录
     * @param dispensary 发药药局
     * @param dispensarySub 发药子药局
     * @return
     */
    public List<DrugPrescMasterTemp> getPrescMasterTemp(String dispensary,String dispensarySub){
        return dao.getPrescMasterTemp(dispensary,dispensarySub);
    }
}
