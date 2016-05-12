package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugClassDict;

import java.util.List;

/**
 * Created by zhu on 2016/5/10.
 */
@MyBatisDao
public interface DrugClassDictDao extends CrudDao<DrugClassDict> {

    /***
     *  根据诊疗机构ID获取药品类别
     * @param orgId 诊疗机构ID
     * @return 药品类别集合
     * @author zq
     */
    public List<DrugClassDict> listDrugClassDictByOrgId(String orgId);

    /***
     * 获取组织机构的某一大类的所有亚类
     * @param orgId 组织机构ID
     * @param parentId 大类ID
     * @return 返回某一大类的所有亚类
     * @author zq
     */
    public List<DrugClassDict> listSubClassDict(String orgId,String parentId);

}
