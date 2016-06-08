package com.jims.sys.bo;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.RoleServiceMenuDao;
import com.jims.sys.entity.RoleServiceMenu;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Component
@Transactional(readOnly = true)
public class RoleServiceMenuService extends CrudImplService<RoleServiceMenuDao, RoleServiceMenu> {

    public String save(List<RoleServiceMenu> roleServiceMenus){
        String result = "0";
        try {
            for (int i = 0, j = (roleServiceMenus != null ? roleServiceMenus.size() : 0); i < j; i++) {
                RoleServiceMenu roleServiceMenu = roleServiceMenus.get(i);
                String menuId = roleServiceMenu.getMenuId();
                String[] id = menuId.split(",");
                for (int k = 0; k < id.length; k++) {
                    RoleServiceMenu serviceMenu = dao.findRoleServiceIdAndMenuId(roleServiceMenu.getRoleServiceId(), id[k]);
                    if(serviceMenu == null){
                        RoleServiceMenu roleServiceMenu1 = new RoleServiceMenu();
                        roleServiceMenu1.setRoleServiceId(roleServiceMenu.getRoleServiceId());
                        roleServiceMenu1.setMenuId(id[k]);
                        roleServiceMenu1.preInsert();
                        dao.insert(roleServiceMenu1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
