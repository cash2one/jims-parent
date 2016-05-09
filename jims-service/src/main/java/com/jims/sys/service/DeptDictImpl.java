package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DeptDictImpl extends CrudImplService<DeptDictDao, DeptDict> implements DeptDictApi {


    @Autowired
    private DeptDictDao deptDictDao;
    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllList() {
        return dao.findAll();

    }

    /**
     * 查询所有的科室属性的类型
     * @return
     */
    //@Override
    public List<DeptDict> findProperty() {
        return dao.findProperty();
    }

    /**
     * 查询所有的上级科室
     * @return
     */
    //@Override
    public List<DeptDict> findParent() {
        return dao.findParent();
    }

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(String code){
        return deptDictDao.findListByCode(code);
    }
}
