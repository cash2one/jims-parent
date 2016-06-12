package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.OrgStaffDao;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private OrgStaffDao orgStaffDao;

    @Autowired
    private SysServiceDao sysServiceDao;

    /**
     * 保存注册信息以及选择的服务
     * @param company
     *
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
        List<SysService> sysServices = sysServiceDao.findServiceWithPrice("3", "0");
        if(sysServices != null && sysServices.size() > 0){
            for(SysService service : sysServices){
                OrgServiceList orgService = new OrgServiceList();
                orgService.setServiceId(service.getId());
                orgService.setServiceStartDate(new Date());
                orgService.setOrgId(company.getId());
                orgService.preInsert();
                serviceDao.insert(orgService);
            }
        }
        int i = dao.insertReturnId(company);
        String id = company.getId();
        OrgStaff orgStaff=new OrgStaff();
        orgStaff.preInsert();
        orgStaff.setPersionId(company.getOwner());
        orgStaff.setDelFlag("0");
        orgStaff.setOrgId(id);
        orgStaffDao.insert(orgStaff);
    }
}