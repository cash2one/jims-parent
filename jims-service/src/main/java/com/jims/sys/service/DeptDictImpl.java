package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.SysCompany;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DeptDictImpl extends CrudImplService<DeptDictDao, DeptDict> implements DeptDictApi {

    public List<DeptDict> findAllList() {
        return dao.findAll();
    }

    @Override
    public int add(DeptDict deptDict) {

        deptDict.preInsert();
        int i = dao.insert(deptDict);
        return i;
    }

    @Override
    public List<DeptDict> findProperty() {
        return dao.findProperty();
    }

    public int update(DeptDict deptDict) {
        deptDict.preUpdate();
        int i = dao.update(deptDict);
        return i;
    }

    @Override
    public List<DeptDict> findParent() {
        return dao.findParent();
    }
}
