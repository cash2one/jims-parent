package com.jims.phstock.api;

import com.jims.phstock.entity.DrugExportClassDict;

import java.util.List;

/**
 * 出库类型接口
 * Created by ztq on 2016/5/10.
 */
public interface DrugExportClassDictApi {

    //增删改查

    /**
     * 根据适用范围查询出库类型字典
     * @param storageType 出库类别
     * @return
     * @author ztq
     */
    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType ) ;
}
