package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.LabItemClassDictServiceApi;
import com.jims.sys.dao.LabItemClassDictDao;
import com.jims.sys.entity.LabItemClassDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 诊疗项目分类字典
 * @author xueyx
 * @version 2016-05-04
 */
@Service(version = "1.0.0")

public class LabItemClassDictServiceImpl extends CrudImplService<LabItemClassDictDao, LabItemClassDict> implements LabItemClassDictServiceApi {

    @Autowired
    private LabItemClassDictDao labItemClassDictDao;
    /**
     * 查询科室代码下的检验类别
     * @return
     */
    public List<LabItemClassDict> findListByDeptCode(String deptCode, String orgId) {
        return labItemClassDictDao.findListByDeptCode(deptCode,orgId);
    }

    /**
     * 查询全部
     * @return
     */
    public List<LabItemClassDict> findAllList() {
        return dao.findAllList();
    }

}
