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

import java.util.ArrayList;
import java.util.List;

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
     * @return
     */
    public String saveService(List<OrgServiceList> serviceList){
        String result = "0";
        if(serviceList != null || serviceList.size() > 0){
            for(OrgServiceList orgService : serviceList){
                result = save(orgService);
            }
        }
        return result;
    }

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单
     * @return
     */
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList){
        String result = "0";
//        if(selfServiceList != null || selfServiceList.size() > 0){
//            for(OrgSelfServiceList orgSelfService : selfServiceList){
//                result = selfServiceDao.save(orgSelfService);
//            }
//        }
        return result;
    }

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgServiceList> findService(String orgId){
        OrgServiceList service = new OrgServiceList();
        service.setOrgId(orgId);
        return dao.findList(service);
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

}