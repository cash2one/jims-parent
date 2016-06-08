package com.jims.nurse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.exam.entity.Orders;
import com.jims.nurse.api.OrdersNurseServiceApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * OrdersNurseRest
 *
 * @author PangQian
 * @date2016/6/7 0007
 */
@Component
@Produces("application/json")
@Path("ordersNurse")
public class OrdersNurseRest {
    @Reference(version = "1.0.0")
    private OrdersNurseServiceApi ordersNurseServiceApi;

    /**
     * 护理端 - 医嘱页面
     * @param visitId
     * @param patientId
     * @param repeatIndicator
     * @param orderStatus
     * @return
     */
    @Path("getNurseOrders")
    @GET
    public List<Orders> getNurseOrders(@QueryParam("visitId")String visitId ,@QueryParam("patientId")String patientId,
            @QueryParam("repeatIndicator")String repeatIndicator ,@QueryParam("orderStatus")String orderStatus){
        Orders orders = new Orders();
        orders.setVisitId(visitId);
        orders.setPatientId(patientId);
        orders.setRepeatIndicator(repeatIndicator);
        orders.setOrderStatus(orderStatus);
       return  ordersNurseServiceApi.getNurseOrders(orders);
    }

    /**
     * 护理端 - 医嘱转抄
     * @param wardCode
     * @param repeatIndicator
     * @param orderStatus
     * @return
     */
    @Path("findOrdersCopied")
    @GET
    public List<Orders> findOrdersCopied(@QueryParam("wardCode")String wardCode, @QueryParam("repeatIndicator")String repeatIndicator ,@QueryParam("orderStatus")String orderStatus){
        Orders orders = new Orders();
        orders.setRepeatIndicator(repeatIndicator);
        orders.setOrderStatus(orderStatus);
        return ordersNurseServiceApi.ordersCopied(orders);

    }
}
