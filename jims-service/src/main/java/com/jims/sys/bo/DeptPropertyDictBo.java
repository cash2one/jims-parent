/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.data.StringData;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.dao.OrgDeptPropertyDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author   yangruidong
 * @version 2016-06-16
 */
@Service
@Component
@Transactional(readOnly = false)
public class DeptPropertyDictBo extends CrudImplService<OrgDeptPropertyDictDao, OrgDeptPropertyDict> {

    @Autowired
    private DeptDictDao  deptDictDao;

    @Autowired
    private OrgDeptPropertyDictDao orgDeptPropertyDictDao;

    public StringData add(OrgDeptPropertyDict orgDeptPropertyDict) {
        List<OrgDeptPropertyDict> list = orgDeptPropertyDictDao.findName(orgDeptPropertyDict.getPropertyType(), orgDeptPropertyDict.getOrgId());
        //给插入的科室属性进行排序
        OrgDeptPropertyDict sort = orgDeptPropertyDictDao.findSort(orgDeptPropertyDict.getOrgId());
        if (list.size() > 0) {
            orgDeptPropertyDict.setSort(null);
        } else {
            if (sort.getSort() == null) {
                orgDeptPropertyDict.setSort(0L);
            } else {
                orgDeptPropertyDict.setSort(sort.getSort() + 1);
            }

        }
        if (list.size() == 0) {
            int num = orgDeptPropertyDictDao.insert(orgDeptPropertyDict);
            if (num != 0) {
                StringData stringData = new StringData();
                stringData.setData("success");
                return stringData;
            }
        } else {
            boolean insert = true;
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.equalsIgnoreCase(orgDeptPropertyDict.getPropertyValue(), list.get(i).getPropertyValue())) {
                    insert = false;
                    break;
                }
            }
            if (insert) {
                int num = orgDeptPropertyDictDao.insert(orgDeptPropertyDict);
                if (num != 0) {
                    StringData stringData = new StringData();
                    stringData.setData("success");
                    return stringData;
                }
            }
            if (insert==false) {
                StringData stringData = new StringData();
                stringData.setData("fail");
                return stringData;
            }
        }
        return null;
    }

    /**
     * 根据属性类型查询属性名称
     * @param
     * @return
     */
    public List<OrgDeptPropertyDict> findNameByType(String propertyType,String orgId) {
        return dao.findNameByType(propertyType,orgId);
    }

    /**
     * 根据属性类型和属性值查询属性名称
     * @param propertyType
     * @param propertyValue
     * @return
     */
    public OrgDeptPropertyDict findNameByTypeAndValue(String propertyType, String propertyValue,String orgId) {
        return dao.findNameByTypeAndValue(propertyType,propertyValue,orgId);
    }

    /**
     *  查询所有的属性类型
     * @return
     */
    public List<OrgDeptPropertyDict> findProperty(String orgId) {
        return dao.findProperty(orgId);
    }

    /**
     * 根据条件查询所有的属性信息
     * @param orgDeptPropertyDict
     * @return
     */
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict) {

        return dao.findByCondition(orgDeptPropertyDict);
    }

    /**
     * 查询属性的名称
     * @param propertyType
     * @return
     */
    public List<OrgDeptPropertyDict> findName(String propertyType,String orgId) {
        return dao.findName(propertyType,orgId);
    }

    /**
     * 查询最大的排序值
     * @return
     */
    public OrgDeptPropertyDict findSort(String orgId) {
        return dao.findSort(orgId);
    }


}