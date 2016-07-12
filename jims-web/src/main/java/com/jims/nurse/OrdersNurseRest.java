package com.jims.nurse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.orders.entity.Orders;
import com.jims.nurse.api.OrdersNurseServiceApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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

    /**
     * 护理端 - 转抄(医生提交 - 新开)
     * @param ordersList
     * @author pq
     * @return
     */
    @Path("operationCopied")
    @POST
    public StringData operationCopied(List<Orders> ordersList){
        StringData stringData = new StringData();

        String code = ordersNurseServiceApi.operationCopied(ordersList);
        stringData.setCode(code);
        if(!"0".equals(code)&& !"".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 护理端 - 医嘱校验（执行状态）
     * @param ordersList
     * @author pq
     * @return
     */
    @Path("proofOrders")
    @POST
    public StringData proofOrders(List<Orders> ordersList){
        StringData stringData = new StringData();
        String code = ordersNurseServiceApi.proofOrders(ordersList);
        stringData.setCode(code);
        if(!"0".equals(code)&& !"".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }
    /**
     * 护理端 - 医嘱执行
     * @param ordersList
     * @author pq
     * @return
     */
    @Path("executeOrders")
    @POST
    public StringData executeOrders(List<Orders> ordersList){
        StringData stringData = new StringData();
        String code = ordersNurseServiceApi.executeOrders(ordersList);
        stringData.setCode(code);
        if(!"0".equals(code)&& !"".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 护理端 - 医嘱停止
     * @param orders
     * @author pq
     * @return
     */
    @Path("stopOrders")
    @POST
    public StringData executeOrders(Orders orders){
        StringData stringData = new StringData();
        String code = ordersNurseServiceApi.nurseStopOrders(orders);
        stringData.setCode(code);
        if(!"0".equals(code)&& !"".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }
}
