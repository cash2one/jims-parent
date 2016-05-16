package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DoctDrugPrescDetailServiceApi;
import com.jims.clinic.dao.DoctDrugPrescDetailDao;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 待发药住院处方明细记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DoctDrugPrescDetailServiceImpl extends CrudImplService<DoctDrugPrescDetailDao, DoctDrugPrescDetail> implements DoctDrugPrescDetailServiceApi{
    /**
     * @param prescMasterId 传递参数
     * @return List    返回类型
     * @Title: findListByPrescMasterId
     * @Desription: (根据条件处方主记录ID查询明细列表)
     * @author CTQ
     * @date 2016/5/16
     */
    @Override
    public List<DoctDrugPrescDetail> findListByPrescMasterId(String prescMasterId){
        return dao.findListByPrescMasterId(prescMasterId);
    }


	
}