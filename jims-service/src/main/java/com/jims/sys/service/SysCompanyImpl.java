package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.entity.SysCompany;


import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Service(version = "1.0.0")
public class SysCompanyImpl extends CrudImplService<SysCompanyDao, SysCompany> implements SysCompanyApi {


    /**
     * 查询父机构名称
     * @return
     */
    @Override
    public List<SysCompany> findListByName() {
        return dao.findListByName();
    }

    /**
     * 查询组织名称是否存在
     * @param orgName
     * @return
     */
    @Override
    public SysCompany findByName(String orgName) {
        return dao.findByName(orgName);
    }

    /**
     * 保存方法（返回id）
     *
     * @param sysCompany
     */
    @Override
    public String insertReturnId(SysCompany sysCompany) {
        sysCompany.preInsert();//添加日期
        int i = dao.insertReturnId(sysCompany);
        String id = sysCompany.getId();
        return id;
    }


    public int update(SysCompany sysCompany) {
        sysCompany.preUpdate();
        int i = dao.update(sysCompany);
        return i;
    }
}
