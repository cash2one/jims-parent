package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import com.jims.sys.api.SysServiceApi;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;


/**
 * 系统服务rest层
 *
 * @author txb
 * @version 2016-05-31
 */
@Component
@Produces("application/json")
@Path("sys-service")
public class SysServiceRest {
    @Reference(version = "1.0.0")
    private SysServiceApi sysServiceApi;

    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    @Path("service-list-by-TC")
    @GET
    public List<SysService> serviceListByTC(@QueryParam("serviceType") String serviceType,@QueryParam("serviceClass") String serviceClass) {
        return sysServiceApi.serviceListByTC(serviceType,serviceClass);
    }
    /**
     * 查询服务明细全部列表
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Path("detail-list")
    @GET
    public List<SysServicePrice> detailList(@QueryParam("serviceId") String serviceId) {
        return sysServiceApi.findDetailList(serviceId);
    }
    /**
     * 查询服务全部列表
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Path("list")
    @GET
    public List<SysService> list() {
        return sysServiceApi.findList();
    }


    /**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Path("save-detail")
    @POST
    public StringData saveDetail(DrugCatalogChangeVo<SysServicePrice> priceBeanVo) {
        String num = sysServiceApi.saveDetail(priceBeanVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }/**
     * 修改保存服务
     * @param sysService
     * @return
     * @author txb
     * @version 2016-05-31
     */
    @Path("save")
    @POST
    public StringData save(SysService sysService) {
       String savePath =  SysServiceRest.class.getResource("/").getFile();


        String num = sysServiceApi.save(sysService,savePath);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }


    /**
     * 删除服务信息
     *
     * @param ids
     * @author txb
     * @version 2016-05-31
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = sysServiceApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
