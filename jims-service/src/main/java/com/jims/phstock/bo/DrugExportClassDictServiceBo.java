package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugExportClassDictDao;
import com.jims.phstock.entity.DrugExportClassDict;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugExportClassDictServiceBo extends CrudImplService<DrugExportClassDictDao, DrugExportClassDict> {

    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType) {
        return dao.listDrugExportClassDictByStorageType(storageType);
    }

    /**
     * 获取出库数据列表
     *
     * @return
     */
    public List<DrugExportClassDict> findAllList() {
        return dao.findAll();
    }
}
