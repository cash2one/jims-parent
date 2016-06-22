/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.sys.dao.*;
import com.jims.sys.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 个人服务BAO层
 *
 * @author yangruidong
 * @version 2016-06-02
 */
@Service
@Component
@Transactional(readOnly = false)
public class PersionServiceListBo extends CrudImplService<PersionServiceListDao, PersionServiceList> {

    @Autowired
    private PersionServiceListDao persionServiceListDao;
    @Autowired
    private OrgStaffDao orgStaffDao;
    @Autowired
    private StaffVsRoleDao staffVsRoleDao;
    @Autowired
    private OrgRoleVsServiceDao orgRoleVsServiceDao;
    @Autowired
    private SysServiceDao sysServiceDao;
    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据persionId查询服务
     *
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(String persionId) {
        List<SysService> services = persionServiceListDao.findListByFlag(persionId);
      /*  //根据persionId查询人员id和组织机构id
        List<OrgStaff> orgStaffs = orgStaffDao.findListByPersionId(persionId);
        for (int i = 0; i < orgStaffs.size(); i++) {
            String orgId = orgStaffs.get(i).getOrgId();
            String staffId = orgStaffs.get(i).getId();

            //根据staffId查询角色id
            List<StaffVsRole> staffVsRoles = staffVsRoleDao.findRole(staffId);

            for (int j = 0; j < staffVsRoles.size(); j++) {
                String roleId = staffVsRoles.get(j).getRoleId();

                //根据roleId查询服务id
                List<OrgRoleVsService> orgRoleVsServices = orgRoleVsServiceDao.findRoleId(roleId);

                for (int x = 0; x < orgRoleVsServices.size(); x++) {
                    String serviceId = orgRoleVsServices.get(x).getServiceId();

                    //根据serviceId查询服务名称
                    List<SysService> sysServices = sysServiceDao.findServiceById(serviceId);

                    services.addAll(sysServices);
                }
            }
        }*/


        List<OrgStaff> orgStaffss = orgStaffDao.findListByPersionId(persionId);
        for (int i = 0; i < orgStaffss.size(); i++) {
            //组织机构id
            String id = orgStaffss.get(i).getOrgId();

            //根据orgId查询组织机构名称
            SysCompany sysCompany = sysCompanyDao.get(id);
            SysService sysService = new SysService();
            sysService.setServiceName(sysCompany.getOrgName());
            sysService.setId(id);
            services.add(sysService);
        }
        return services;
    }

    /**
     * 保存个人购买的服务
     *
     * @param persionServiceList
     * @return 1 成功 ,0 失败
     */
    public void saveService(PersionServiceList persionServiceList) {
        List<PersionServiceList> services = persionServiceList.getServiceList();
        if (services != null && services.size() > 0) {
            for (PersionServiceList service : services) {
                service.preInsert();
                persionServiceListDao.insert(service);
            }
        }
    }

    /**
     * 根据组织机构id查询信息
     *
     * @param orgName
     * @return
     */
    public SysCompany getOrgName(String orgName) {
        return sysCompanyDao.getOrgName(orgName);
    }


    /**
     * 根据人员Id 查询免费的服务 并保存
     *
     * @param persionId
     * @return
     */
    public void save(String persionId) {
        String serviceType = "0";
        String serviceClass = "1";
        List<PersionServiceList> persionServiceLists = persionServiceListDao.findServiceByPersionId(persionId);
        List<SysService> sysServices = sysServiceDao.serviceListByTC(serviceType, serviceClass);

        Set<String> set = new HashSet<String>();

        for(int i=0,j=(sysServices==null ? 0 : sysServices.size());i<j;i++){
            SysService service = sysServices.get(i);
            set.add(service.getId());
            boolean insert = true;
            for(int x=0,y=(persionServiceLists==null ? 0 : persionServiceLists.size());x<y;x++) {
                 if(persionServiceLists.get(x).getServiceId().equals(service.getId())){
                     insert = false;
                     break;
                 }
            }
            if(insert){
                PersionServiceList persionServiceList = new PersionServiceList();
                persionServiceList.preInsert();
                persionServiceList.setPersionId(persionId);
                persionServiceList.setServiceId(service.getId());
                persionServiceList.setFlag("0");
                persionServiceList.setServiceStartDate(new Date());
                persionServiceList.setServiceEndDate(null);
                persionServiceListDao.insert(persionServiceList);
            }
        }

        for(int i=0,j=(persionServiceLists==null ? 0 : persionServiceLists.size());i<j;i++)  {
            if(!set.contains(persionServiceLists.get(i).getServiceId())){
                 persionServiceListDao.delete(persionServiceLists.get(i));
            }
        }
    }

    public SysCompany findNameByOwner(String loginName) {
        SysCompany sysCompany = sysCompanyDao.findNameByOwner(loginName);
        return sysCompany;
    }


    /**
     * 与数据库中的用户名比对，是否正确
     *
     * @param loginName
     * @return
     */
    public SysUser selectLoginName(String loginName) {
        if (StringUtils.isNotBlank(loginName)){
            SysUser user = sysUserDao.selectLoginName(loginName);
            return user;
        }
        return null;
    }

    /**
     * 与数据库中的密码比对，是否正确
     *
     * @param loginName
     * @return
     */
    public SysUser selectPassword(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            SysUser user = sysUserDao.selectPasswrod(loginName);
            return user;
        }
        return null;
    }
}