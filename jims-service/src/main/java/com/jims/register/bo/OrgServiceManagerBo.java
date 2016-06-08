package com.jims.register.bo;

import com.jims.common.service.impl.CrudImplService;
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
     *                        参数OrgSelfServiceList属性operateFlag
     *                        为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        为 2 时，属性id为药删除的自定义菜单Id,多个以‘,’隔开，
     *                                  menus属性为修改的菜单（只包含数据库中已有的自定义服务的菜单）
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，menus为添加的菜单。
     */
    public void saveSelfService(List<OrgSelfServiceList> selfServiceList){
        if(selfServiceList != null || selfServiceList.size() > 0){
            for(OrgSelfServiceList service : selfServiceList){
                if("1".equals(service.getOperateFlag()) && !"".equals(service.getId())){
                    String[] ids = service.getId().split(",");
                    for (int i = 0; i < ids.length; i++){
                        selfServiceDao.delete(ids[i]);
                        selfServiceVsMenuDao.deleteByServiceId(ids[i]);
                    }
                } else if("2".equals(service.getOperateFlag())){
                    if(!"".equals(service.getId())){
                        String[] ids = service.getId().split(",");
                        for (int i = 0; i < ids.length; i++){
                            selfServiceVsMenuDao.delete(ids[i]);
                        }
                    }
                    if(service.getMenus() != null && service.getMenus().size() > 0){
                        List<OrgSelfServiceVsMenu> menus = service.getMenus();
                        for (int i = 0; i < menus.size(); i++){
                            OrgSelfServiceVsMenu menu = menus.get(i);
                            if (menu.getIsNewRecord()){
                                menu.preInsert();
                                selfServiceVsMenuDao.insert(menu);
                            }else{
                                menu.preUpdate();
                                selfServiceVsMenuDao.update(menu);
                            }
                        }
                    }
                } else {
                    if (service.getIsNewRecord()){
                        service.preInsert();
                        selfServiceDao.insert(service);
                        List<OrgSelfServiceVsMenu> menus = service.getMenus();
                        if(menus != null) {
                            for (int i = 0; i < menus.size(); i++) {
                                OrgSelfServiceVsMenu menu = menus.get(i);
                                menu.setSelfServiceId(service.getId());
                                menu.preInsert();
                                selfServiceVsMenuDao.insert(menu);
                            }
                        }
                    }else{
                        service.preUpdate();
                        selfServiceDao.update(service);
                    }
                }
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
                service.setMenus(handlerMenu(service.getMenus()));
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
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(String selfServiceId){
        OrgSelfServiceVsMenu selfServiceVsMenu = new OrgSelfServiceVsMenu();
        selfServiceVsMenu.setSelfServiceId(selfServiceId);
        return selfServiceVsMenuDao.findList(selfServiceVsMenu);
    }

    public List<OrgSelfServiceVsMenuVo> findSelfServiceMenu(String orgId){
        List<OrgSelfServiceVsMenuVo> orgSelfServiceVsMenuVos = new ArrayList<OrgSelfServiceVsMenuVo>();

        OrgSelfServiceList selfService = new OrgSelfServiceList();
        selfService.setOrgId(orgId);
        List<OrgSelfServiceList> orgSelfServiceLists = selfServiceDao.findList(selfService);
        for (int i = 0; i < orgSelfServiceLists.size(); i++) {
            OrgSelfServiceList orgSelfServiceList = orgSelfServiceLists.get(i);
            OrgSelfServiceVsMenu selfServiceVsMenu = new OrgSelfServiceVsMenu();
            selfServiceVsMenu.setSelfServiceId(orgSelfServiceList.getId());
            List<OrgSelfServiceVsMenu> orgSelfServiceVsMenus = selfServiceVsMenuDao.findList(selfServiceVsMenu);
            OrgSelfServiceVsMenuVo orgSelfServiceVsMenuVo = new OrgSelfServiceVsMenuVo();
            orgSelfServiceVsMenuVo.setId(orgSelfServiceList.getId());
            orgSelfServiceVsMenuVo.setText(orgSelfServiceList.getServiceName());
            List<MenuDictVo> menuDictVos = new ArrayList<MenuDictVo>();
            for(int j = 0; j < orgSelfServiceVsMenus.size(); j++){
                OrgSelfServiceVsMenu  orgSelfServiceVsMenu = orgSelfServiceVsMenus.get(j);
                MenuDict menuDict = menuDictDao.get(orgSelfServiceVsMenu.getMenuId());
                MenuDictVo menuDictVo = new MenuDictVo();
                menuDictVo.setId(menuDict.getId());
                menuDictVo.setText(menuDict.getMenuName());
                menuDictVos.add(menuDictVo);
                orgSelfServiceVsMenuVo.setChildren(menuDictVos);
            }
            orgSelfServiceVsMenuVos.add(orgSelfServiceVsMenuVo);
        }
        return orgSelfServiceVsMenuVos;
    }

    //处理树形数据
    private List<MenuDict> handlerMenu(List<MenuDict> menus){
        List<MenuDict> resultMenus = new ArrayList<MenuDict>();
        if(menus != null && menus.size() > 0){
            Map<String,MenuDict> menusMap = new HashMap<String, MenuDict>();
            List<String> roots = new ArrayList<String>();
            for(int i=0,j=menus.size();i<j;i++){
                handlerMenu(menus.get(i),menusMap,roots);
            }
            for(int i=0,j=roots.size();i<j;i++){
                resultMenus.add(menusMap.get(roots.get(i)));
            }
        }
        return resultMenus;
    }

    private void handlerMenu(MenuDict menu,Map<String,MenuDict> menusMap,List<String> roots){
        if(menu != null){
            if(menu.getPid() != null){
                if(menusMap.get(menu.getPid()) != null){
                    if(menusMap.get(menu.getPid()).getChildren() == null){
                        menusMap.get(menu.getPid()).setChildren(new ArrayList<MenuDict>());
                    }
                    menusMap.get(menu.getPid()).getChildren().add(menu);
                } else {
                    MenuDict parent = menuDictDao.get(menu.getPid());
                    if (parent == null) {
                        if(menusMap.get(menu.getId()) == null)
                            menusMap.put(menu.getId(), menu);
                        roots.add(menu.getId());
                    } else {
                        parent.setChildren(new ArrayList<MenuDict>());
                        parent.getChildren().add(menu);
                        menusMap.put(menu.getId(), menu);
                        handlerMenu(parent,menusMap,roots) ;
                    }
                }
            } else {
                if (menusMap.get(menu.getId()) == null)
                    menusMap.put(menu.getId(),menu);
                roots.add(menu.getId());
            }
        }
    }
}