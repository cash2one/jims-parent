package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugExportClassDictApi;
import com.jims.phstock.bo.DrugExportClassDictServiceBo;
import com.jims.phstock.dao.DrugExportClassDictDao;
import com.jims.phstock.entity.DrugExportClassDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 出库分类字典生成Service
 *
 * @author luohk
 * @version 2016-05-10
 */
@Service(version = "1.0.0")
public class DrugExportClassDictService  implements DrugExportClassDictApi{

    @Autowired
    private DrugExportClassDictServiceBo drugExportClassDictServiceBo;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugExportClassDict> beanChangeVo){
        return drugExportClassDictServiceBo.merge(beanChangeVo);
    }

    /**
     * 获取出库类型
     *
     * @param id 出库类型主键ID
     * @return
     */
    public DrugExportClassDict get(String id){
        return drugExportClassDictServiceBo.get(id);
    }

    /**
     * 根据适用范围查询出库类型字典
     * @param storageType 出库类别
     * @return
     * @author luohk
     */
    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType){
           return drugExportClassDictServiceBo.listDrugExportClassDictByStorageType(storageType);
    }

    /**
     * 获取出库数据列表
     *
     * @return
     */
    public List<DrugExportClassDict> findAllList(){
        return drugExportClassDictServiceBo.findAllList();
    }

    /**
     * 根据出库类型获取出库类型集合
     *
     * @param drugExportClassDict 出库类型实体类
     * @return
     */
    public List<DrugExportClassDict> findList(DrugExportClassDict drugExportClassDict){
        return drugExportClassDictServiceBo.findList(drugExportClassDict);
    }

    /**
     * 根据出库类型获取分页数据
     *
     * @param page                页数和条目数
     * @param drugExportClassDict 实体类
     * @return
     */
    public Page<DrugExportClassDict> findPage(Page<DrugExportClassDict> page, DrugExportClassDict drugExportClassDict){
        return drugExportClassDictServiceBo.findPage(page,drugExportClassDict);
    }

    /**
     * 保存功能
     *
     * @param drugExportClassDict
     */
    public String save(DrugExportClassDict drugExportClassDict){
        return drugExportClassDictServiceBo.save(drugExportClassDict);
    }

    /**
     * 删除功能
     *
     * @param drugExportClassDict
     */
    public String delete(DrugExportClassDict drugExportClassDict){
        return drugExportClassDictServiceBo.delete(drugExportClassDict);
    }

    /**
     * 删除功能
     *
     * @param id
     * @return
     */
    public String delete(String id){
        return drugExportClassDictServiceBo.delete(id);
    }
}
