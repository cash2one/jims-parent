package com.jims.sys.bo;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.OrgRoleVsServiceDao;
import com.jims.sys.dao.RoleServiceMenuDao;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
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

    public List<OrgRoleVsService> findAll(){
        return dao.findAll();
    }

    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService) {
        String result = "0";
        try {
            for (int i = 0, j = (orgRoleVsService != null ? orgRoleVsService.size() : 0); i < j; i++) {
                OrgRoleVsService  orgRoleVsService1 = orgRoleVsService.get(i);
                List<OrgRoleVsService> orgRoleVsServices = orgRoleVsServiceDao.findRoleId(orgRoleVsService1.getRoleId());
                if(orgRoleVsServices.size() == 0){
                    orgRoleVsService1.preInsert();
                    orgRoleVsServiceDao.insert(orgRoleVsService1);
                }
                OrgRoleVsService  roleVsService = orgRoleVsServiceDao.findRoleIdAndServiceId(orgRoleVsService1.getRoleId(), orgRoleVsService1.getServiceId());
                if(roleVsService == null){
                    orgRoleVsService1.preInsert();
                    orgRoleVsServiceDao.insert(orgRoleVsService1);
                }
               /* for (int k = 0; k < orgRoleVsServices.size(); k++){
                    OrgRoleVsService orgRoleVsService2 = orgRoleVsServices.get(k);
                    if(orgRoleVsService2.getServiceId().contains(orgRoleVsService1.getServiceId())){

                    }
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<RoleServiceMenu> find(String id){
        List<RoleServiceMenu> roleServiceMenuss = new ArrayList<RoleServiceMenu>();
        List<OrgRoleVsService> orgRoleVsServices = orgRoleVsServiceDao.findRoleId(id);
        for (int i = 0, j = (orgRoleVsServices != null ? orgRoleVsServices.size() : 0); i < j; i++) {
            OrgRoleVsService orgRoleVsService = orgRoleVsServices.get(i);
            List<RoleServiceMenu> roleServiceMenus = roleServiceMenuDao.findRoleServiceId(orgRoleVsService.getServiceId());
            RoleServiceMenu roleServiceMenu1 = new RoleServiceMenu();
            roleServiceMenu1.setRoleServiceId(orgRoleVsService.getServiceId());
            String menuId = "";
            for (int k=0; k < roleServiceMenus.size(); k++){
                RoleServiceMenu  roleServiceMenu = roleServiceMenus.get(k);
                menuId += roleServiceMenu.getMenuId() + ',';
            }
            roleServiceMenu1.setMenuId(menuId);
            roleServiceMenuss.add(roleServiceMenu1);
        }
        return roleServiceMenuss;
    }
}
