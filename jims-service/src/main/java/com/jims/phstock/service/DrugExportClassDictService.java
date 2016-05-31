package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugExportClassDictApi;
import com.jims.phstock.dao.DrugExportClassDictDao;
import com.jims.phstock.entity.DrugExportClassDict;


import java.util.List;

/**
 * 出库分类字典生成Service
 *
 * @author luohk
 * @version 2016-05-10
 */
@Service(version = "1.0.0")

public class DrugExportClassDictService extends CrudImplService<DrugExportClassDictDao, DrugExportClassDict> implements DrugExportClassDictApi{

    /**
     * 根据适用范围查询出库类型字典
     * @param storageType 出库类别
     * @return
     * @author luohk
     */
    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType){
           return  dao.listDrugExportClassDictByStorageType(storageType);
    }

    /**
     * 获取出库数据列表
     *
     * @return
     */
    public List<DrugExportClassDict> findAllList(){
        return  dao.findAll();
    }
}
