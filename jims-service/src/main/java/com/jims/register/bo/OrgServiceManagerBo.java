package com.jims.register.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.TreeUtils;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.dao.MenuDictDao;
import com.jims.sys.entity.MenuDict;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.MenuDictVo;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 机构服务事务管理层
 * @author lgx
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrgServiceManagerBo extends CrudImplService<OrgServiceListDao, OrgServiceList> {

    @Autowired
    private OrgSelfServiceListDao selfServiceDao;
    @Autowired
    private OrgSelfServiceVsMenuDao selfServiceVsMenuDao;
    @Autowired
    private MenuDictDao menuDictDao;

    /**
     * 保存选择的服务
     * @param serviceList 服务列表
     */
    public void saveService(List<OrgServiceList> serviceList){
        if(serviceList != null || serviceList.size() > 0){
            for(OrgServiceList orgService : serviceList){
                save(orgService);
            }
        }
    }

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单,
     *                        参数OrgSelfServiceList属性中
     *                        delFlag 为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        id不为空，orgId为空时，属性menus为服务(id)对应的菜单数据(树形结构)
     *
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，menus为添加的菜单。
     */
    public void saveSelfService(List<OrgSelfServiceList> selfServiceList){
        if(selfServiceList != null || selfServiceList.size() > 0){
            for(OrgSelfServiceList service : selfServiceList){
                if("1".equals(service.getDelFlag())){
                    String[] ids = service.getId().split(",");
                    for (int i = 0; i < ids.length; i++){
                        selfServiceDao.delete(ids[i]);
                        selfServiceVsMenuDao.deleteByServiceId(ids[i]);
                    }
                    continue;
                }
                if(service.getId() != null && service.getOrgId() == null){
                    selfServiceVsMenuDao.deleteByServiceId(service.getId());
                    saveSelfServiceVsMenu(service.getMenus(),service.getId(),null);
                    continue;
                }
                if (service.getIsNewRecord()){
                    service.preInsert();
                    selfServiceDao.insert(service);
                    saveSelfServiceVsMenu(service.getMenus(),service.getId(),null);
                }else{
                    service.preUpdate();
                    selfServiceDao.update(service);
                }
            }
        }
    }

    /**
     * 保存自定义服务对应菜单
     * @param menus 菜单序列
     * @param selfServiceID 服务ID
     * @param parentId 菜单父节点
     */
    private void saveSelfServiceVsMenu(List<OrgSelfServiceVsMenu> menus,String selfServiceID,String parentId){
        if(menus != null && menus.size() > 0){
            for(OrgSelfServiceVsMenu menu : menus){
                menu.setSelfServiceId(selfServiceID);
                menu.setPid(parentId);
                if(menu.getIsNewRecord()){
                    menu.preInsert();
                    selfServiceVsMenuDao.insert(menu);
                }else{
                    menu.preUpdate();
                    selfServiceVsMenuDao.update(menu);
                }
                saveSelfServiceVsMenu(menu.getChildren(),selfServiceID,menu.getId());
            }
        }
    }

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgServiceList> findService(String orgId){
        OrgServiceList serviceParam = new OrgServiceList();
        serviceParam.setOrgId(orgId);
        List<OrgServiceList> services = dao.findList(serviceParam);
        if(services != null && services.size() > 0){
            for(OrgServiceList service : services){
                service.setMenus(TreeUtils.handleTreeList(service.getMenus()));
            }
        }
        return services;
    }

    /**
     * 检索机构自定义的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgSelfServiceList> findSelfService(String orgId){
        OrgSelfServiceList selfService = new OrgSelfServiceList();
        selfService.setOrgId(orgId);
        return selfServiceDao.findList(selfService);
    }

    /**
     * 检索机构自定义菜单
     * @param selfServiceId 自定义服务Id
     * @param isTree 是否为树形结构
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(String selfServiceId,boolean isTree){
        OrgSelfServiceVsMenu selfServiceVsMenu = new OrgSelfServiceVsMenu();
        selfServiceVsMenu.setSelfServiceId(selfServiceId);
        List<OrgSelfServiceVsMenu> menus = selfServiceVsMenuDao.findList(selfServiceVsMenu);
        if(isTree && menus != null && menus.size() > 1){
            return TreeUtils.handleTreeList(menus);
        }
        return menus;
    }

    public List<MenuDictVo> findSelfServiceMenu(String selfServiceId, String roleServiceId) {

        List<OrgSelfServiceVsMenu> orgSelfServiceVsMenus = selfServiceVsMenuDao.findSelfServiceId(selfServiceId, roleServiceId);
        List<MenuDictVo> menuDictVos = new ArrayList<MenuDictVo>();
        for (int j = 0; j < orgSelfServiceVsMenus.size(); j++) {
            OrgSelfServiceVsMenu orgSelfServiceVsMenu = orgSelfServiceVsMenus.get(j);
            MenuDict menuDict = menuDictDao.get(orgSelfServiceVsMenu.getMenuId());
            MenuDictVo menuDictVo = new MenuDictVo();
            menuDictVo.setId(orgSelfServiceVsMenu.getId());
            menuDictVo.setMenuId(menuDict.getId());
            menuDictVo.setMenuName(menuDict.getMenuName());
            menuDictVo.setPid(orgSelfServiceVsMenu.getPid());
            if (orgSelfServiceVsMenu.getMenuOperate() == null) {
                menuDictVo.setMenuOperate("0");
            } else {
                menuDictVo.setMenuOperate(orgSelfServiceVsMenu.getMenuOperate());
            }
            menuDictVos.add(menuDictVo);
        }
        return menuDictVos;
    }
}