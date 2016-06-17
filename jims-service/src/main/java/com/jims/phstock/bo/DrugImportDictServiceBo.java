package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugImportClassDictDao;
import com.jims.phstock.entity.DrugImportClassDict;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugImportDictServiceBo extends CrudImplService<DrugImportClassDictDao, DrugImportClassDict> {

    /**
     * 根据适用单位查询所有的入库类型
     *
     * @param storageType 适用单位
     * @return
     * @author luohk
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType) {
        return dao.listDrugImportByStorageType(storageType);
    }

    /**
     * 获取入库类别列表
     *
     * @return
     */
    public List<DrugImportClassDict> findAllList() {
        return dao.findAll();
    }
}
