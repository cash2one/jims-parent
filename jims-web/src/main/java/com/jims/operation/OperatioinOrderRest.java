package com.jims.operation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * OperatioinOrderRest
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("operatioinOrder")
public class OperatioinOrderRest {
    @Reference(version = "1.0.0")
    private OperatioinOrderServiceApi operatioinOrderServiceApi;

    /**
     * 通过科室Code查找病人列表
     * @param deptCode
     * @return
     */
    @Path("findPat")
    @GET
    public List<PatsInHospital> getOperatioin(String deptCode){
        deptCode="140102";
       return operatioinOrderServiceApi.getOperationin(deptCode);
    }

    /**
     * 保存手术名称 手术安排
     * @param operationSchedule
     * @return
     */
    @Path("save")
    @POST
    public String save(OperationSchedule operationSchedule){
      return   operatioinOrderServiceApi.saveOperation(operationSchedule);
    }



}
