package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugImportClassDictApi;
import com.jims.phstock.dao.DrugImportClassDictDao;
import com.jims.phstock.entity.DrugImportClassDict;


import java.util.List;

/**
 * 入库分类字典生成Service
 *
 * @author luohk
 * @version 2016-05-10
 */
@Service(version = "1.0.0")

public class DrugImportClassDictService extends CrudImplService<DrugImportClassDictDao, DrugImportClassDict> implements DrugImportClassDictApi {

    /**
     * 根据适用单位查询所有的入库类型
     * @param storageType 适用单位
     * @return
     * @author luohk
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType){
        return dao.listDrugImportByStorageType(storageType);
    }

    /**
     * 获取入库类别列表
     *
     * @return
     */
    public List<DrugImportClassDict> findAllList(){
        return dao.findAll();
    }
}
