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
    private RoleServiceMenuDao roleServiceMenuDao;
    @Autowired
    private OrgSelfServiceListDao orgSelfServiceListDao;
    @Autowired
    private OrgSelfServiceVsMenuDao orgSelfServiceVsMenuDao;

    public List<OrgRoleVsService> findAll() {
        return dao.findAll();
    }

    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService) {
        int result = 0;
        for (int i = 0, j = (orgRoleVsService != null ? orgRoleVsService.size() : 0); i < j; i++) {
            OrgRoleVsService roleVsService = orgRoleVsService.get(i);
            if(roleVsService.getServiceId() != null && roleVsService.getRoleId() != null && roleVsService.getMenuId() != null){
                OrgRoleVsService roleVsService1 = dao.find(roleVsService.getServiceId(), roleVsService.getRoleId(), roleVsService.getMenuId());
                if (roleVsService1 == null) {
                    if (null != roleVsService.getMenuOperate() || roleVsService.getMenuOperate() == "0" || roleVsService.getMenuOperate() == "1") {
                        roleVsService.preInsert();
                        result = dao.insert(roleVsService);
                        result++;
                    }
                } else {
                    roleVsService.setId(roleVsService1.getId());
                    result = dao.update(roleVsService);
                    result++;
                }
            }
        }

        return result + "";
    }

    /**
     * 保存角色新添加的自定义服务
     * @param orgRoleVsServices
     * @return
     * @author fengyuguang
     */
    public String saveService(List<OrgRoleVsService> orgRoleVsServices){
        String result = "0";
        try {
            for (int i = 0, j = (orgRoleVsServices != null ? orgRoleVsServices.size() : 0); i < j; i++) {
                OrgRoleVsService orgRoleVsService = orgRoleVsServices.get(i);
                String serviceIds = orgRoleVsService.getServiceId();
                String[] id = serviceIds.split(",");
                for (int k = 0; k < id.length; k++) {
                    List<OrgRoleVsService> lists = dao.findRoleIdAndServiceId(orgRoleVsService.getRoleId(), id[k]);//查看该角色是否已经有了该服务
                    if (lists.size() == 0) {    //该角色没有此服务
                        List<OrgSelfServiceVsMenu> menus = orgSelfServiceVsMenuDao.findServiceId(id[k]);    //查询该自定义服务的菜单
                        for (OrgSelfServiceVsMenu menu : menus) {
                            OrgRoleVsService orgRoleVsService1 = new OrgRoleVsService();
                            orgRoleVsService1.preInsert();
                            orgRoleVsService1.setRoleId(orgRoleVsService.getRoleId());
                            orgRoleVsService1.setServiceId(id[k]);
                            orgRoleVsService1.setMenuId(menu.getMenuId());
                            dao.insert(orgRoleVsService1);
                        }
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
        List<OrgRoleVsService> orgRoleVsServices = dao.findRoleId(id);
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

        List<OrgRoleVsService> orgRoleVsServices = dao.findRoleId(roleid);
        for (int i = 0, j = (orgRoleVsServices != null ? orgRoleVsServices.size() : 0); i < j; i++) {
            OrgRoleVsService orgRoleVsService = orgRoleVsServices.get(i);
            OrgSelfServiceList orgSelfServiceList = orgSelfServiceListDao.get(orgRoleVsService.getServiceId());
            orgRoleVsService.setServiceName(orgSelfServiceList.getServiceName());
        }
        return orgRoleVsServices;
    }

    /**
     * 删除角色的自定义服务
     * @param serviceId
     * @param roleId
     * @return
     * @author fengyuguang
     */
    public String delete(String serviceId,String roleId){
        List<OrgRoleVsService> lists = dao.findRoleIdAndServiceId(roleId, serviceId);
        for (OrgRoleVsService list : lists) {
            String delete = super.delete(list.getId());
            System.out.println(delete);
        }
        return "1";
    }

    @Override
    public String delete(String id){
        String result = "";
        OrgRoleVsService orgRoleVsService = dao.get(id);
        if(orgRoleVsService != null){
            result = String.valueOf(super.delete(id));
            List<RoleServiceMenu> roleServiceMenus =roleServiceMenuDao.findRoleServiceId(id);
            if(roleServiceMenus.size() > 0){
                roleServiceMenuDao.deleteService(id);
            }
        }
        return result;
    }
}
