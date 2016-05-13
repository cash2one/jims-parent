package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugImportClassDict;

import java.util.List;

/**
 * 入库类别字典接口
 * Created by  ztq on 2016/5/10.
 */
public interface DrugImportClassDictApi {

    /**
     * 根据入库类别字典id获取入库类别列表
     * @param id 入库类别字典主键id
     * @return 入库类别字典的一条数据
     * @author luohk
     */
    public DrugImportClassDict get(String id);

    /**
     * 获取入库类别列表
     *
     * @return
     */
    public List<DrugImportClassDict> findAllList();

    /**
     * 根据入库类别字典获取入库类别列表
     * @param drugImportClassDict 实体类
     * @return 入库类别集合
     * @author luohk
     */
    public List<DrugImportClassDict> findList(DrugImportClassDict drugImportClassDict);

    /**
     * 获取入库类别字典分页功能
     * @param page 页数和条目数
     * @param drugImportClassDict 实体类
     * @return 入库类别的分页数据
     */
    public Page<DrugImportClassDict> findPage(Page<DrugImportClassDict> page, DrugImportClassDict drugImportClassDict);

    /**
     * 保存功能
     * @param drugImportClassDict
     */
    public String save(DrugImportClassDict drugImportClassDict);

    /**
     * 删除功能
     * @param drugImportClassDict
     */
    public String delete(DrugImportClassDict drugImportClassDict);

    /**
     * 删除功能
     * @param id
     * @return
     */
    public String delete(String id);

    /**
     * 根据适用单位查询所有的入库类型
     * @param storageType 适用单位
     * @return
     * @author ztq
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType) ;

}
