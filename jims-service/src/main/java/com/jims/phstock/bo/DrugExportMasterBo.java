package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugExportMasterDao;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugExportMasterBo extends CrudImplService<DrugExportMasterDao, DrugExportMaster> {
    @Autowired
    private DrugExportMasterDao drugExportMasterDao;


    /**
     * 查询入库记录
     * @param orgId
     * @return
     */
    public List<DrugExportMaster> findMasterList(String orgId,String subStorage,String startImportDate,String stopImportDate,String storageCode){
        return drugExportMasterDao.findMasterList(orgId, subStorage,  startImportDate, stopImportDate, storageCode);
    }


    public String update(DrugExportMaster drugExportMaster) {
        return dao.update(drugExportMaster)+"";
    }



}
