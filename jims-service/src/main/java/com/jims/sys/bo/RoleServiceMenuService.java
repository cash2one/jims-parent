package com.jims.sys.bo;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.dao.MenuDictDao;
import com.jims.sys.dao.RoleServiceMenuDao;
import com.jims.sys.entity.MenuDict;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.MenuDictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Component
@Transactional(readOnly = true)
public class RoleServiceMenuService extends CrudImplService<RoleServiceMenuDao, RoleServiceMenu> {

    @Autowired
    private MenuDictDao menuDictDao;
    @Autowired
    private OrgSelfServiceVsMenuDao orgSelfServiceVsMenuDao;

    public String save(List<RoleServiceMenu> roleServiceMenus) {
        String result = "0";
        try {
            for (int i = 0, j = (roleServiceMenus != null ? roleServiceMenus.size() : 0); i < j; i++) {
                RoleServiceMenu roleServiceMenu = roleServiceMenus.get(i);
                RoleServiceMenu serviceMenu = dao.findRoleServiceIdAndMenuId(roleServiceMenu.getRoleServiceId(), roleServiceMenu.getMenuId());
                if (serviceMenu == null) {
                    RoleServiceMenu roleServiceMenu1 = new RoleServiceMenu();
                    roleServiceMenu1.setRoleServiceId(roleServiceMenu.getRoleServiceId());
                    roleServiceMenu1.setMenuId(roleServiceMenu.getMenuId());
                    roleServiceMenu1.setMenuOperate(roleServiceMenu.getMenuOperate());
                    save(roleServiceMenu1);
                } else {
                    RoleServiceMenu roleServiceMenu1 = new RoleServiceMenu();
                    roleServiceMenu1.setRoleServiceId(roleServiceMenu.getRoleServiceId());
                    roleServiceMenu1.setMenuId(roleServiceMenu.getMenuId());
                    roleServiceMenu1.setMenuOperate(roleServiceMenu.getMenuOperate());
                    dao.updateService(roleServiceMenu1);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MenuDictVo> findRoleServiceMenu(String selfServiceId) {

        List<RoleServiceMenu> roleServiceMenus = dao.findRoleServiceId(selfServiceId);
        List<MenuDictVo> menuDictVos = new ArrayList<MenuDictVo>();
        for (int j = 0; j < roleServiceMenus.size(); j++) {
            RoleServiceMenu orgSelfServiceVsMenu = roleServiceMenus.get(j);
            OrgSelfServiceVsMenu orgSelfServiceVsMenu1 =  orgSelfServiceVsMenuDao.get(orgSelfServiceVsMenu.getMenuId());
            MenuDict menuDict = menuDictDao.get(orgSelfServiceVsMenu1.getMenuId());
            MenuDictVo menuDictVo = new MenuDictVo();
            menuDictVo.setId(orgSelfServiceVsMenu.getId());
            menuDictVo.setMenuId(orgSelfServiceVsMenu.getMenuId());
            menuDictVo.setMenuName(menuDict.getMenuName());
            menuDictVo.setPid(orgSelfServiceVsMenu.getPid());
            menuDictVo.setMenuOperate(orgSelfServiceVsMenu.getMenuOperate());
            menuDictVos.add(menuDictVo);
        }
        return menuDictVos;
    }
}
