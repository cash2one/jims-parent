package com.jims.sys.api;



import com.jims.phstock.vo.DrugCatalogChangeVo;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;

import java.util.List;

/**
 * 系统服务api层
 *
 * @author txb
 * @version 2016-05-31
 */
public interface SysServiceApi {


    /**
     * 保存或修改
     * @param sysService
     * @param savePath
     * @author txb
     * @version 2016-05-31
     * @return
     */
    public  String save(SysService sysService,String savePath);

    /**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public String saveDetail(DrugCatalogChangeVo priceBeanVo);

    /**
     * 删除
     * @param ids
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public String delete(String ids);
    /**
     * 查询服务全部列表
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public List<SysService> findList();
    /**
     * 查询服务明细全部列表
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId);
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass);
}
