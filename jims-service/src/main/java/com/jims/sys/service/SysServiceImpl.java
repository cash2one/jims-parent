package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import com.jims.sys.api.SysServiceApi;
import com.jims.sys.bo.SysServiceBo;
import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 系统服务imp层
 *
 * @author txb
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class SysServiceImpl implements SysServiceApi {
    @Autowired
    private SysServiceBo sysServiceBo;


    /**
     * 保存修改
     * @param sysService
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Override
    public String save(SysService sysService) {

        return sysServiceBo.save(sysService);
    }
    /**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public String saveDetail(DrugCatalogChangeVo priceBeanVo){
        return sysServiceBo.saveDetail(priceBeanVo);
    }
    /**
     * 修改保存服务菜单
     * @param serviceVsMenus
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Override
    public String saveServiceVsMenu(List<ServiceVsMenu> serviceVsMenus) {
        return sysServiceBo.saveServiceVsMenu(serviceVsMenus);
    }

    /**
     * 删除
     * @param ids
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Override
    public String delete(String ids) {
        return sysServiceBo.delete(ids);
    }
    /**
     * 查询全部列表
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Override
    public List<SysService> findList() {
        return sysServiceBo.findList(new SysService());
    }
    /**
     * 查询服务明细全部列表
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId){
        return sysServiceBo.findDetailList(serviceId);
    }
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Override
    public List<SysService> serviceListByTC(String serviceType, String serviceClass) {
        return sysServiceBo.serviceListByTC(serviceType,serviceClass);
    }
    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Override
    public List<ServiceVsMenu> serviceVsMenuList(String serviceId) {
        return sysServiceBo.serviceVsMenuList(serviceId);
    }

    ;
}
