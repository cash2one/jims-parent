package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.dao.DrugImportDetailDao;
import com.jims.phstock.dao.DrugImportMasterDao;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品入库明细单据Service
 * @author lgx
 * @version 2016-05-17
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugImportDetailService extends CrudImplService<DrugImportMasterDao, DrugImportMaster> implements DrugImportServiceApi{

    @Autowired
    private DrugImportDetailDao detailDao;

    /**
     * 保存药品入库单详单
     * @param detail
     * @return
     */
    @Override
    public String saveDetail(DrugImportDetail detail){
        int i=0;
        try{
            if (detail.getIsNewRecord()){
                detail.preInsert();
                i=detailDao.insert(detail);
            }else{
                detail.preUpdate();
                i=detailDao.update(detail);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    /**
     * 批量保存药品入库单详单
     * @param details
     * @return
     */
    @Override
    public String saveDetailBatch(List<DrugImportDetail> details) {
        int successNum = 0;
        if(details != null && details.size() > 0){
            for(DrugImportDetail detail: details){
                successNum += Integer.valueOf(saveDetail(detail));
            }
        }
        return String.valueOf(successNum);
    }


}