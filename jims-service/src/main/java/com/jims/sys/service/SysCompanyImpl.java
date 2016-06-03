package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.bo.SysCompanyBo;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Service(version = "1.0.0")
public class SysCompanyImpl extends CrudImplService<SysCompanyDao, SysCompany> implements SysCompanyApi {

    @Autowired
    private SysCompanyBo bo;
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

    /**
     * 保存注册信息以及选择的服务
     * @param company
     * @return 1 成功 ,0 失败
     */
    public String saveCompanyAndService(SysCompany company){
        String result = "0";
        try{
            bo.saveCompanyAndService(company);
            result = "1";
        } catch (Exception e){
        }
        return result;
    }
}
