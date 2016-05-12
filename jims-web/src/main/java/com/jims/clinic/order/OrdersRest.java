package com.jims.clinic.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.exam.api.OrdersServiceApi;
import com.jims.exam.entity.ExamAppoints;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Administrator on 2016/5/11.
 */

@Component
@Produces("application/json")
@Path("orders")
public class OrdersRest {
    @Reference(version = "1.0.0")
    private OrdersServiceApi ordersServiceApi;

    @POST
    @Path("saveOrders")
    public StringData saveOrders(ExamAppoints examAppoints){
        StringData stringData=new StringData();
        String num =ordersServiceApi.saveOrders(examAppoints);
        stringData.setCode(num);
        return stringData;
    }
}
