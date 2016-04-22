package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.entity.SysCompany;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Service(version = "1.0.0")
@Transactional
public class SysCompanyImpl extends CrudImplService<SysCompanyDao, SysCompany> implements SysCompanyApi {
    /**
     * 创建组织机构
     * @param sysCompany
     * @return
     */
    @Override
    public int createCompany(SysCompany sysCompany) {
        sysCompany.setId(UUID.randomUUID()+"");
        sysCompany.setCreateDate(new Date());
        if(sysCompany.getParentId()==null)
        {
            sysCompany.setParentId(null);
        }
        sysCompany.setLinkMan(null);
        sysCompany.setUpdateDate(new Date());
        sysCompany.setApplyStatus("0");

        int company=dao.insert(sysCompany);
        return company;
    }

    /* *查看组织机构信息
     *
     * @return
     */


    @Override
    public List<SysCompany> findAllList() {
        return null;
    }

    /**
     * 查询父机构名称
     * @return
     */
    @Override
    public List<SysCompany> findListByName() {
        return dao.findListByName();
    }
}
