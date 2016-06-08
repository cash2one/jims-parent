package com.jims.sys.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.sys.api.OrgRoleVsServiceApi;
import com.jims.sys.bo.OrgRoleVsServiceService;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 角色服务权限Service
 * @author luohk
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class OrgRoleVsServiceServiceImpl implements OrgRoleVsServiceApi {

    @Autowired
    private OrgRoleVsServiceService orgRoleVsServiceService;

    public OrgRoleVsService get(String id){
         return orgRoleVsServiceService.get(id);
    }

    public List<OrgRoleVsService> findList(OrgRoleVsService orgRoleVsService){
        return orgRoleVsServiceService.findList(orgRoleVsService);
    }

    public Page<OrgRoleVsService> findPage(Page<OrgRoleVsService> page, OrgRoleVsService orgRoleVsService){
        return orgRoleVsServiceService.findPage(page,orgRoleVsService);
    }

    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService){
        return orgRoleVsServiceService.OrgRoleVsServiceSave(orgRoleVsService);
    }

    public String delete(OrgRoleVsService orgRoleVsService){
        return orgRoleVsServiceService.delete(orgRoleVsService);
    }

    public List<OrgRoleVsService> findAll(){
        return orgRoleVsServiceService.findAll();
    }

    public List<RoleServiceMenu> find(String id){
        return orgRoleVsServiceService.find(id);
    }
	
}