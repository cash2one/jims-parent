package com.jims.register.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}