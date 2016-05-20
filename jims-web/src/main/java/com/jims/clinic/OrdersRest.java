package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.exam.api.OrdersServiceApi;
import com.jims.exam.entity.Orders;

import com.sun.jersey.api.core.HttpContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;


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
     * @return
     */
    @Path("getOrders")
    @GET
    public List<Orders> getOrders(@QueryParam("repeatIndicator")String repeatIndicator,@QueryParam("startDateTime")String startDateTime,@QueryParam("stopDateTime")String stopDateTime,@QueryParam("orderStatus")String orderStatus){
        Orders orders=new Orders();
        orders.setRepeatIndicator(repeatIndicator);
        orders.setOrderStatus(orderStatus);
        orders.setPatientId("15006135");
        orders.setVisitId("1");
        return ordersServiceApi.getPatientOrders(orders);
    }

    /**
     * 保存医嘱
     * @param ordersList
     * @return
     * pq
     */
    @Path("save")
    @POST
    public  StringData saveOrders(List<Orders> ordersList){
        String num = ordersServiceApi.saveOrdersNew(ordersList);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
      return stringData;
    }

    /**
     * 保存子医嘱
     * @param orders
     * @return
     * pq
     */
    @Path("saveSubOrder")
    @POST
    public String saveSubOrders(Orders orders){
        return ordersServiceApi.saveSubOrder(orders);
    }

    /**
     * 下达医嘱
     * @param id
     * @return
     * pq
     */
    @Path("issuedOrders")
    @POST
    public int issuedOrders(String id){
     return ordersServiceApi.issuedOrders(id);
    }
}
