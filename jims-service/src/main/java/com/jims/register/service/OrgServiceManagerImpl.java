package com.jims.register.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.OrgServiceManagerApi;
import com.jims.register.bo.OrgServiceManagerBo;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 机构自定义服务Service
 * @author lgx
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class OrgServiceManagerImpl implements OrgServiceManagerApi {

    @Autowired
    private OrgServiceManagerBo bo;

    /**
     * 保存选择的服务
     * @param serviceList 服务列表
     * @return
     */
    public String saveService(List<OrgServiceList> serviceList){
        return bo.saveService(serviceList);
    }

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单
     * @return
     */
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList){
        return bo.saveSelfService(selfServiceList);
    }

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgServiceList> findService(String orgId){
        return bo.findService(orgId);
    }

    /**
     * 检索机构自定义的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgSelfServiceList> findSelfService(String orgId){
        return bo.findSelfService(orgId);
    }

    /**
     * 检索机构自定义菜单
     * @param selfServiceId 自定义服务Id
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(String selfServiceId){
        return bo.findSelfServiceVsMenu(selfServiceId);
    }


}