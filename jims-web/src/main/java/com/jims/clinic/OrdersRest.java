package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.exam.api.OrdersServiceApi;
import com.jims.exam.entity.Orders;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * 住院医嘱
 *
 * @author PangQian
 * @date2016/5/18 0018
 */
@Component
@Produces("application/json")
@Path("inOrders")
public class OrdersRest {
    @Reference(version = "1.0.0")
    private OrdersServiceApi ordersServiceApi ;

    /**
     * 根据病人Id和住院Id查询病人的医嘱列表
     * @param orders
     * @return
     */
    @Path("getOrders")
    @GET
    public List<Orders> getOrders(Orders orders){
        return ordersServiceApi.getPatientOrders(orders);
    }
}
