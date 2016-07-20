package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugImportMasterDao;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugImportMasterBo extends CrudImplService<DrugImportMasterDao, DrugImportMaster> {
    @Autowired
    private DrugImportMasterDao drugImportMasterDao;


    /**
     * 查询入库记录
     * @param orgId
     * @return
     */
    public List<DrugImportMaster> findMasterList(String orgId,String subStorage, String supplier,String startImportDate,String stopImportDate,String storageCode){
        return drugImportMasterDao.findMasterList(orgId, subStorage, supplier, startImportDate, stopImportDate, storageCode);
    }


    public String update(DrugImportMaster drugImportMaster) {
        return dao.update(drugImportMaster)+"";
    }



}
