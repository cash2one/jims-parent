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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典Service
 * @author zhangyao
 * @version 2014-05-18
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
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
    public List<OrgDeptPropertyDict> findNameByType() {
        return dao.findNameByType();
    }
}
