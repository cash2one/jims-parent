package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceListDao;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.dao.OrgServiceListDao;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.dao.ServiceVsMenuDao;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysCompany;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Service(version = "1.0.0")
public class SysCompanyImpl extends CrudImplService<SysCompanyDao, SysCompany> implements SysCompanyApi {
    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private OrgServiceListDao orgServiceListDao;        //服务
    @Autowired
    private ServiceVsMenuDao serviceVsMenuDao;      //服务于菜单对照
    @Autowired
    private OrgSelfServiceListDao orgSelfServiceListDao;    //自定义服务
    @Autowired
    private OrgSelfServiceVsMenuDao orgSelfServiceVsMenuDao;    //自定义服务与菜单对照
    @Autowired
    private SysServiceDao sysServiceDao;    //系统服务

    /**
     * 根据申请状态查询组织机构列表
     * @param applyStatus 申请状态
     * @return 组织机构list集合
     * @author fengyuguang
     */
    public List<SysCompany> findListByApplyStatus(String applyStatus){
        return dao.findListByApplyStatus(applyStatus);
    }

    /**
     * 查询父机构名称
     * @return
     */
    @Override
    public List<SysCompany> findListByName() {
        return dao.findListByName();
    }

    /**
     * 查询组织名称是否存在
     * @param orgName
     * @return
     */
    @Override
    public SysCompany findByName(String orgName) {
        return dao.findByName(orgName);
    }

    /**
     * 保存方法（返回id）
     *
     * @param sysCompany
     */
    @Override
    public String insertReturnId(SysCompany sysCompany) {
        sysCompany.preInsert();//添加日期
        int i = dao.insertReturnId(sysCompany);
        String id = sysCompany.getId();
        return id;
    }


    public int update(SysCompany sysCompany) {
        sysCompany.preUpdate();

        String orgId = sysCompany.getId();
        String serviceId;   //机构服务ID
        Date startDate;     //机构服务开始时间
        Date endDate;       //机构服务结束时间
        String serviceName; //服务名称
        String menuId;      //菜单ID
        String menuSort;        //菜单排序
        //查询机构服务列表
        List<OrgServiceList> lists = orgServiceListDao.findByOrgId(orgId);
        for (OrgServiceList list : lists) {
            serviceId = list.getServiceId();     //服务ID
            startDate = list.getServiceStartDate();    //服务开始时间
            endDate = list.getServiceEndDate();    //服务结束时间

            //查询系统服务根据服务ID获取服务名称
            serviceName = sysServiceDao.get(serviceId).getServiceName();

            //自定义服务
            OrgSelfServiceList orgSelfServiceList = new OrgSelfServiceList();
            orgSelfServiceList.preInsert();     //设置主键ID
            orgSelfServiceList.setOrgId(orgId);
            orgSelfServiceList.setServiceName(serviceName);
            orgSelfServiceListDao.insert(orgSelfServiceList);   //添加自定义服务

            //根据服务ID查询服务菜单对照表
            ServiceVsMenu serviceVsMenu = serviceVsMenuDao.findByServiceId(serviceId);
            menuId = serviceVsMenu.getMenuId();
            menuSort = serviceVsMenu.getMenuSort();
            //自定义服务于菜单对照
            OrgSelfServiceVsMenu orgSelfServiceVsMenu = new OrgSelfServiceVsMenu();
            orgSelfServiceVsMenu.preInsert();   //设置主键ID
            orgSelfServiceVsMenu.setSelfServiceId(serviceId);
            orgSelfServiceVsMenu.setMenuId(menuId);
            orgSelfServiceVsMenu.setMenuSort(menuSort);
            orgSelfServiceVsMenu.setMenuEndData(endDate);
            orgSelfServiceVsMenuDao.insert(orgSelfServiceVsMenu);   //添加自定义服务于菜单对照数据
        }

        int i = dao.update(sysCompany);
        return i;
    }
}
