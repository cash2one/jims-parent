package com.jims.clinic.clinicinspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.exam.api.ExamAppointsServiceApi;
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
public class ClinicInspectInRest {
    @Reference(version = "1.0.0")
    private OrdersServiceApi ordersServiceApi;
    @Reference(version = "1.0.0")
    private ExamAppointsServiceApi examAppointsServiceApi;

    @POST
    @Path("saveOrders")
    public StringData saveOrders(ExamAppoints examAppoints){
        StringData stringData=new StringData();
        String num =ordersServiceApi.saveOrders(examAppoints);
        stringData.setCode(num);
        return stringData;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public ExamAppoints get(String id) {
        ExamAppoints examAppoints = examAppointsServiceApi.get(id);
        return examAppoints;
    }

    @Path("del")
    @POST
    public StringData deleteExamAppionts(String id) {
        StringData stringData = new StringData();
        String num = ordersServiceApi.deleteOrders(id);
        stringData.setCode(num+"");
        stringData.setData("success");
        return stringData;
    }
}
