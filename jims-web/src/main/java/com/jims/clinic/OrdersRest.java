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
     * pq
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
    public StringData issuedOrders(String id){
        StringData data = new StringData();
        String num=ordersServiceApi.issuedOrders(id);
        Orders orders=ordersServiceApi.get(id);

        data.setCode(num);
        if(Integer.parseInt(num)>0){
            data.setData("success");
        }else{
            data.setData("error");
        }
        return data;
    }

    /**
     * 删除医嘱
     * @param id
     * @return
     * pq
     */
    @Path("deleteOrdersNew")
    @POST
    public StringData deleteOrdersNew(String id){
        StringData data = new StringData();
        String num=ordersServiceApi.deleteOrdersNew(id);
        data.setCode(num);
        if(Integer.parseInt(num)>0){
            data.setData("success");
        }else{
            data.setData("error");
        }
        return data;
    }

    /**
     * 拿到最大的医嘱号、子医嘱号
     * @param orders
     * @return
     * pq
     */
    @Path("getMaxOrderNo")
    @POST
   public  Orders getMaxOrderNo(Orders orders){
        Orders orders1=new Orders();
        Integer num=ordersServiceApi.getMaxOrderNo(orders.getPatientId(),orders.getVisitId());
        Integer numSub = ordersServiceApi.getOrderSubNo(orders.getPatientId(),orders.getVisitId(),num);
        orders1.setOrderNo(num!=null?(num+1):1);
        orders1.setOrderSubNo(numSub != null ? (numSub + 1) : 1);
        return orders1;
   }

    /**
     * 查询子医嘱
     * @param orders
     * @return
     * pq
     */
    @Path("getSubOrders")
    @POST
    public List<Orders> getSubOrders(Orders orders){
           List<Orders> ordersList = ordersServiceApi.getSubOrders(orders);
        return ordersList;
    }
}
