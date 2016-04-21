package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.OutpOrders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by CTQ
 * 门诊医嘱主记录
 */
@Component
@Produces("application/json")
@Path("outporders")
public class OutpOrdersRest {
 /*   @Reference(version = "1.0.0")
    private OutpOrdersService outpOrdersService ;*/
    /**1.点击前台处方-新方（根据患者信息查询）**/

    /**查询处方门诊医嘱主记录所有数据**/
   /* @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OutpOrders> list(@Context HttpServletRequest request,@Context HttpServletResponse response){
     Page<OutpOrders> page = outpOrdersService.findPage(new Page<OutpOrders>(request,response), new OutpOrders());
        return page.getList();
    }*/

    /****/
}
