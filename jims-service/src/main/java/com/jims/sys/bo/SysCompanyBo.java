package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.*;
import com.jims.sys.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.processing.RoundEnvironment;
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
    private SysServiceDao sysServiceDao;    //系统服务
    @Autowired
    private OrgSelfServiceListDao orgSelfServiceListDao;    //自定义服务
    @Autowired
    private ServiceVsMenuDao serviceVsMenuDao;      //服务于菜单对照
    @Autowired
    private OrgSelfServiceVsMenuDao orgSelfServiceVsMenuDao;    //自定义服务与菜单对照
    @Autowired
    OrgRoleDao orgRoleDao;      //角色
    @Autowired
    OrgStaffDao staffDao;       //员工
    @Autowired
    StaffVsRoleDao staffVsRoleDao;      //员工对应角色
    @Autowired
    OrgRoleVsServiceDao roleVsServiceDao;   //角色对应服务
    @Autowired
    RoleServiceMenuDao roleServiceMenuDao;  //服务对应菜单

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

        dao.insert(company);
        String id = company.getId();
        OrgStaff orgStaff=new OrgStaff();
        orgStaff.preInsert();
        orgStaff.setPersionId(company.getOwner());
        orgStaff.setDelFlag("0");
        orgStaff.setOrgId(id);
        orgStaffDao.insert(orgStaff);

    }

    /**
     * 组织机构通过审核
     * @param sysCompany
     * @return
     * @author fengyuguang
     */
    public int update(SysCompany sysCompany) {
        sysCompany.preUpdate();

        String orgId = sysCompany.getId();
        String serviceId;   //机构服务ID
        Date startDate;     //机构服务开始时间
        Date endDate;       //机构服务结束时间
        String serviceName; //服务名称
        String menuId;      //菜单ID
        String menuSort;        //菜单排序
        //查询机构服务列表
        List<OrgServiceList> lists = serviceDao.findByOrgId(orgId);
        for (OrgServiceList list : lists) {
            serviceId = list.getServiceId();     //服务ID
            startDate = list.getServiceStartDate();    //服务开始时间
            endDate = list.getServiceEndDate();    //服务结束时间

            //查询系统服务根据服务ID获取服务名称
            serviceName = sysServiceDao.get(serviceId).getServiceName();

            //自定义服务
            OrgSelfServiceList orgSelfServiceList = new OrgSelfServiceList();
            orgSelfServiceList.preInsert();     //设置主键ID
            orgSelfServiceList.setOrgId(orgId);
            orgSelfServiceList.setServiceName(serviceName);
            orgSelfServiceListDao.insert(orgSelfServiceList);   //添加自定义服务

            //根据服务ID查询服务菜单对照列表
            List<ServiceVsMenu> sVmLists = serviceVsMenuDao.findByServiceId(serviceId);
            for (ServiceVsMenu serviceVsMenu : sVmLists) {
                menuId = serviceVsMenu.getMenuId();
                menuSort = serviceVsMenu.getMenuSort();

                //自定义服务于菜单对照
                OrgSelfServiceVsMenu orgSelfServiceVsMenu = new OrgSelfServiceVsMenu();
                orgSelfServiceVsMenu.preInsert();   //设置主键ID
                orgSelfServiceVsMenu.setSelfServiceId(serviceId);
                orgSelfServiceVsMenu.setMenuId(menuId);
                orgSelfServiceVsMenu.setMenuSort(menuSort);
                orgSelfServiceVsMenu.setMenuEndDate(endDate);
                orgSelfServiceVsMenuDao.insert(orgSelfServiceVsMenu);   //添加自定义服务于菜单对照数据
            }
        }
        //创建默认管理角色
        OrgRole role = new OrgRole(orgId,"超级管理员");
        role.preInsert();
        orgRoleDao.insert(role);
        //员工与角色对照表
        StaffVsRole staffVsRole = new StaffVsRole();
        staffVsRole.preInsert();
        staffVsRole.setRoleId(role.getId());
        String owner = sysCompany.getOwner();
        String staffId = staffDao.getByPersionId(owner).getId();
        staffVsRole.setStaffId(staffId);
        staffVsRoleDao.insert(staffVsRole);
        //角色对应服务
        for (OrgServiceList list : lists) {
            OrgRoleVsService roleVsService = new OrgRoleVsService();
            roleVsService.preInsert();
            roleVsService.setRoleId(role.getId());
            roleVsService.setServiceId(list.getServiceId());
            roleVsServiceDao.insert(roleVsService);
            //角色服务菜单
            RoleServiceMenu roleServiceMenu = new RoleServiceMenu();
            List<ServiceVsMenu> sVmLists = serviceVsMenuDao.findByServiceId(list.getServiceId());
            for (ServiceVsMenu sVmList : sVmLists) {
                roleServiceMenu.preInsert();
                roleServiceMenu.setRoleServiceId(roleVsService.getId());
                roleServiceMenu.setMenuId(sVmList.getMenuId());
                roleServiceMenu.setMenuOperate("1");
                roleServiceMenuDao.insert(roleServiceMenu);
            }
        }

        int i = dao.update(sysCompany);

        return i;
    }

    /**
     * 驳回组织机构审核
     * @param sysCompany
     * @return
     */
    public int failPass(SysCompany sysCompany){
        return dao.update(sysCompany);
    }
}