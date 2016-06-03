package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 组织机构事务层
 * @author lgx
 * @version 2016-6-3
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysCompanyBo extends CrudImplService<SysCompanyDao, SysCompany> {

    @Autowired
    private OrgServiceListDao serviceDao;

    /**
     * 保存注册信息以及选择的服务
     * @param company
     * @return 1 成功 ,0 失败
     */
    public void saveCompanyAndService(SysCompany company){
        company.preInsert();
        List<OrgServiceList> services = company.getServiceList();
        if(services != null && services.size() > 0){
            for(OrgServiceList service : services){
                service.setOrgId(company.getId());
                service.preInsert();
                serviceDao.insert(service);
            }
        }
        dao.insert(company);
    }
}