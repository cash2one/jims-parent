package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugExportClassDict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * 出库类型接口
 * Created by ztq on 2016/5/10.
 */
public interface DrugExportClassDictApi {

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugExportClassDict> beanChangeVo);

    /**
     * 获取出库类型
     * @param id 出库类型主键ID
     * @return
     */
    public DrugExportClassDict get(String id);

    /**
     * 获取出库数据列表
     *
     * @return
     */
    public List<DrugExportClassDict> findAllList();

    /**
     * 根据出库类型获取出库类型集合
     * @param drugExportClassDict 出库类型实体类
     * @return
     */
    public List<DrugExportClassDict> findList(DrugExportClassDict drugExportClassDict);

    /**
     * 根据出库类型获取分页数据
     * @param page 页数和条目数
     * @param drugExportClassDict 实体类
     * @return
     */
    public Page<DrugExportClassDict> findPage(Page<DrugExportClassDict> page, DrugExportClassDict drugExportClassDict);

    /**
     * 保存功能
     * @param drugExportClassDict
     */
    public String save(DrugExportClassDict drugExportClassDict);

    /**
     * 删除功能
     * @param drugExportClassDict
     */
    public String delete(DrugExportClassDict drugExportClassDict);

    /**
     * 删除功能
     * @param id
     * @return
     */
    public String delete(String id);

    /**
     * 根据适用范围查询出库类型字典
     * @param storageType 出库类别
     * @return
     * @author ztq
     */
    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType ) ;
}
