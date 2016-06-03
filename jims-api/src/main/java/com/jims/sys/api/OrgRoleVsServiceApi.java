package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface OrgRoleVsServiceApi {

    public OrgRoleVsService get(String id);

    public List<OrgRoleVsService> findList(OrgRoleVsService orgRoleVsService) ;

    public Page<OrgRoleVsService> findPage(Page<OrgRoleVsService> page, OrgRoleVsService orgRoleVsService) ;

    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService);

    public String delete(OrgRoleVsService orgRoleVsService) ;

    public List<OrgRoleVsService> findAll();

    public List<RoleServiceMenu> find(String id);
}
