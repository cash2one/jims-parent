package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.dao.DoctDrugPrescDetailDao;
import com.jims.clinic.dao.DoctDrugPrescMasterDao;
import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 待发药住院处方主记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DoctDrugPrescMasterServiceImpl extends CrudImplService<DoctDrugPrescMasterDao, DoctDrugPrescMaster> implements DoctDrugPrescMasterServiceApi {
    @Autowired
    DoctDrugPrescDetailDao doctDrugPrescDetailDao;
    /**
     * 根据参数查询列表
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
    @Override
    public List<DoctDrugPrescMaster> findListByParams(DoctDrugPrescMaster doctDrugPrescMaster) {
        return dao.findListByParams(doctDrugPrescMaster);
    }

    /**
     * 保存处方信息及处方明细
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日16:19:11
     * @return
     */
    @Override
    public String savePresc(DoctDrugPrescMaster doctDrugPrescMaster) {
        try {
            //保存处方主记录

            //保存处方记录明细
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(dao.insert(doctDrugPrescMaster));
    }
    /**
     * 根据处方主记录ID删除处方明细
     * @param id
     * @author CTQ
     * @date 2016年5月16日16:19:11
     * @return
     */
    @Override
    public String deletePresc(String id) {
        int num = 0;
        try {
            doctDrugPrescDetailDao.removeByMasterId(id);
            num = dao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(num);
    }
    /**
     * 根据参数查询最大处方号+1
     * @param visitId
     * @author CTQ
     * @date 2016年5月17日14:25:04
     * @return
     */
    @Override
    public Integer searchPrescNo(String visitId) {
        return dao.searchPrescNo(visitId);
    }
}