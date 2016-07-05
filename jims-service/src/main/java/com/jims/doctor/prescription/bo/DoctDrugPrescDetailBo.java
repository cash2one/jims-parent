package com.jims.doctor.prescription.bo;


import com.jims.prescription.entity.DoctDrugPrescDetail;
import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.prescription.dao.DoctDrugPrescDetailDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 待发药住院处方明细记录Bo
 * @author zhangyao
 * @version 2016-07-5
 */
@Service
@Transactional(readOnly = false)
public class DoctDrugPrescDetailBo extends CrudImplService<DoctDrugPrescDetailDao, DoctDrugPrescDetail>{
    /**
     * @param prescMasterId 传递参数
     * @return List    返回类型
     * @Title: findListByPrescMasterId
     * @Desription: (根据条件处方主记录ID查询明细列表)
     * @author CTQ
     * @date 2016/5/16
     */
    public List<DoctDrugPrescDetail> findListByPrescMasterId(String prescMasterId){
        return dao.findListByPrescMasterId(prescMasterId);
    }


	
}