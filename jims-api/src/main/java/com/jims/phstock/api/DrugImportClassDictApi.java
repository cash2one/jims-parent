package com.jims.phstock.api;

import com.jims.phstock.entity.DrugImportClassDict;

import java.util.List;

/**
 * Created by  ztq on 2016/5/10.
 */
public interface DrugImportClassDictApi {

    //增删改查


    /**
     * 根据适用单位查询所有的入库类型
     * @param storageType 适用单位
     * @return
     * @author ztq
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType) ;

}
