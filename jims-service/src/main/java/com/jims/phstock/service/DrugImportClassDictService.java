package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.phstock.api.DrugImportClassDictApi;
import com.jims.phstock.bo.DrugImportDictServiceBo;
import com.jims.phstock.entity.DrugImportClassDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 入库分类字典生成Service
 *
 * @author luohk
 * @version 2016-05-10
 */
@Service(version = "1.0.0")
public class DrugImportClassDictService implements DrugImportClassDictApi {

    @Autowired
    private DrugImportDictServiceBo drugImportClassDictServiceBo;


    /**
     * 根据入库类别字典id获取入库类别列表
     *
     * @param id 入库类别字典主键id
     * @return 入库类别字典的一条数据
     * @author luohk
     */
    public DrugImportClassDict get(String id){
        return drugImportClassDictServiceBo.get(id);
    }

    /**
     * 根据适用单位查询所有的入库类型
     *
     * @param storageType 适用单位
     * @return
     * @author luohk
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType) {
        return drugImportClassDictServiceBo.listDrugImportByStorageType(storageType);
    }

    /**
     * 获取入库类别列表
     *
     * @return
     */
    public List<DrugImportClassDict> findAllList() {
        return drugImportClassDictServiceBo.findAllList();
    }

    /**
     * 根据入库类别字典获取入库类别列表
     *
     * @param drugImportClassDict 实体类
     * @return 入库类别集合
     * @author luohk
     */
    public List<DrugImportClassDict> findList(DrugImportClassDict drugImportClassDict){
        return drugImportClassDictServiceBo.findList(drugImportClassDict);
    }

    /**
     * 获取入库类别字典分页功能
     *
     * @param page                页数和条目数
     * @param drugImportClassDict 实体类
     * @return 入库类别的分页数据
     */
    public Page<DrugImportClassDict> findPage(Page<DrugImportClassDict> page, DrugImportClassDict drugImportClassDict){
        return drugImportClassDictServiceBo.findPage(page, drugImportClassDict);
    }

    /**
     * 保存功能
     *
     * @param drugImportClassDict
     */
    public String save(DrugImportClassDict drugImportClassDict){
        return drugImportClassDictServiceBo.save(drugImportClassDict);
    }

    /**
     * 删除功能
     *
     * @param drugImportClassDict
     */
    public String delete(DrugImportClassDict drugImportClassDict){
        return drugImportClassDictServiceBo.delete(drugImportClassDict);
    }

    /**
     * 删除功能
     *
     * @param id
     * @return
     */
    public String delete(String id){
        return drugImportClassDictServiceBo.delete(id);
    }
}
