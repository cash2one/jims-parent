package com.jims.register.api;

import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;

import java.util.List;

/**
 * 机构选择服务管理API
 * @author lgx
 * @version 2016-05-31
 */
public interface OrgServiceManagerApi {

    /**
     * 保存选择的服务
     * @param serviceList 服务列表
     * @return
     */
    public String saveService(List<OrgServiceList> serviceList);

    /**
     * 保存机构自定义的服务
     * @param selfServiceList 自定义服务以及菜单,
     *                        参数OrgSelfServiceList属性operateFlag
     *                        为 1 时，属性id为药删除的自定义服务id,多个以‘,’隔开，
     *                        为 2 时，属性id为药删除的自定义菜单Id,多个以‘,’隔开，
     *                                  menus属性为修改的菜单（只包含数据库中已有的自定义服务的菜单）
     *                        其他值时，为修改的自定义服务，当为添加的自定义服务时，menus为添加的菜单。
     * @return
     */
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList);

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    public List<OrgServiceList> findService(String orgId);

    /**
     * 检索机构自定义的服务
     * @param orgId 机构Id
     * @return
     */
    public List<OrgSelfServiceList> findSelfService(String orgId);

    /**
     * 检索机构自定义菜单
     * @param selfServiceId 自定义服务Id
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenu(String selfServiceId);

}