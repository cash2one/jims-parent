package com.jims.sys.bo;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.dao.OrgRoleVsServiceDao;
import com.jims.sys.dao.RoleServiceMenuDao;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgRoleVsServiceVo;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Component
@Transactional(readOnly = true)
public class OrgRoleVsServiceService extends CrudImplService<OrgRoleVsServiceDao, OrgRoleVsService> {

    @Autowired
    private OrgRoleVsServiceDao orgRoleVsServiceDao;
    @Autowired
    private RoleServiceMenuDao roleServiceMenuDao;
    @Autowired
    private OrgSelfServiceListDao orgSelfServiceListDao;
    @Autowired
    private OrgSelfServiceVsMenuDao orgSelfServiceVsMenuDao;

    public List<OrgRoleVsService> findAll() {
        return dao.findAll();
    }

    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService) {
        String result = "0";
        try {
            for (int i = 0, j = (orgRoleVsService != null ? orgRoleVsService.size() : 0); i < j; i++) {
                OrgRoleVsService orgRoleVsService1 = orgRoleVsService.get(i);
                String serviceId = orgRoleVsService1.getServiceId();
                String[] id = serviceId.split(",");
                for (int k = 0; k < id.length; k++) {
                    OrgRoleVsService roleVsService = dao.findRoleIdAndServiceId(orgRoleVsService1.getRoleId(), id[k]);
                    if (roleVsService == null) {
                        OrgRoleVsService orgRoleVsService2 = new OrgRoleVsService();
                        orgRoleVsService2.setRoleId(orgRoleVsService1.getRoleId());
                        orgRoleVsService2.setServiceId(id[k]);
                        save(orgRoleVsService2);
                    } else {
                        return "1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<RoleServiceMenu> find(String id) {
        List<RoleServiceMenu> roleServiceMenuss = new ArrayList<RoleServiceMenu>();
        List<OrgRoleVsService> orgRoleVsServices = orgRoleVsServiceDao.findRoleId(id);
        for (int i = 0, j = (orgRoleVsServices != null ? orgRoleVsServices.size() : 0); i < j; i++) {
            OrgRoleVsService orgRoleVsService = orgRoleVsServices.get(i);
            List<RoleServiceMenu> roleServiceMenus = roleServiceMenuDao.findRoleServiceId(orgRoleVsService.getServiceId());
            RoleServiceMenu roleServiceMenu1 = new RoleServiceMenu();
            roleServiceMenu1.setRoleServiceId(orgRoleVsService.getServiceId());
            String menuId = "";
            for (int k = 0; k < roleServiceMenus.size(); k++) {
                RoleServiceMenu roleServiceMenu = roleServiceMenus.get(k);
                menuId += roleServiceMenu.getMenuId() + ',';
            }
            roleServiceMenu1.setMenuId(menuId);
            roleServiceMenuss.add(roleServiceMenu1);
        }
        return roleServiceMenuss;
    }

    public List<OrgRoleVsService> findRole(String roleid) {

        List<OrgRoleVsService> orgRoleVsServices = orgRoleVsServiceDao.findRoleId(roleid);
        for (int i = 0, j = (orgRoleVsServices != null ? orgRoleVsServices.size() : 0); i < j; i++) {
            OrgRoleVsService orgRoleVsService = orgRoleVsServices.get(i);
            OrgSelfServiceList orgSelfServiceList = orgSelfServiceListDao.get(orgRoleVsService.getServiceId());
            orgRoleVsService.setServiceName(orgSelfServiceList.getServiceName());
        }
        return orgRoleVsServices;
    }
}
