/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.dao.DictDao;
import com.jims.sys.dao.OrgDeptPropertyDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgDeptPropertyDict;


import java.util.List;


/**
 * 科室属性Service
 * @author yangruidongr
 * @version 2016-04-23
 */
@Service(version = "1.0.0")

public class OrgDeptPropertyDictImpl extends CrudImplService<OrgDeptPropertyDictDao, OrgDeptPropertyDict> implements DeptPropertyDictApi {
    @Override
    public int add(OrgDeptPropertyDict orgDeptPropertyDict) {
        orgDeptPropertyDict.preInsert();
        int i = dao.insert(orgDeptPropertyDict);
        return i;
    }

    /**
     * 根据属性类型查询属性名称
     * @param
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findNameByType(String propertyType,String orgId) {
        return dao.findNameByType(propertyType,orgId);
    }

    /**
     * 根据属性类型和属性值查询属性名称
     * @param propertyType
     * @param propertyValue
     * @return
     */
    @Override
    public OrgDeptPropertyDict findNameByTypeAndValue(String propertyType, String propertyValue) {
        return dao.findNameByTypeAndValue(propertyType,propertyValue);
    }

    /**
     *  查询所有的属性类型
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findProperty(String orgId) {
        return dao.findProperty(orgId);
    }

    /**
     * 查询所有的属性信息
     * @return
     */
   /* @Override
    public List<OrgDeptPropertyDict> findList() {
        return dao.findList();
    }*/

    /**
     * 根据条件查询所有的属性信息
     * @param orgDeptPropertyDict
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict) {

        return dao.findByCondition(orgDeptPropertyDict);
    }

    /**
     * 查询属性的名称
     * @param propertyType
     * @return
     */
    @Override
    public List<OrgDeptPropertyDict> findName(String propertyType,String orgId) {
        return dao.findName(propertyType,orgId);
    }

    /**
     * 查询最大的排序值
     * @return
     */
    @Override
    public OrgDeptPropertyDict findSort(String orgId) {
        return dao.findSort(orgId);
    }


}
