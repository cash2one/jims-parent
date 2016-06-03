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
     * @return 0失败，1成功
     */
    public String saveService(List<OrgServiceList> serviceList){
        String result = "0";
        try{
            bo.saveService(serviceList);
            result = "1";
        } catch(Exception e){
        }
        return result;
    }

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单,
     *                        参数OrgSelfServiceList属性operateFlag
     *                        为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        为 2 时，属性id为药删除的自定义菜单Id,多个以‘,’隔开，
     *                                  menus属性为修改的菜单（只包含数据库中已有的自定义服务的菜单）
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，menus为添加的菜单。
     * @return 0失败，1成功
     */
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList){
        String result = "0";
        try{
            bo.saveSelfService(selfServiceList);
            result = "1";
        } catch(Exception e){
        }
        return result;
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