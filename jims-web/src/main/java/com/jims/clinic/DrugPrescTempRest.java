package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.DrugPrescTempServiceApi;
import com.jims.clinic.entity.DrugPrescDetailTemp;
import com.jims.clinic.entity.DrugPrescMasterTemp;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 门诊待发药
 *
 * @author PangQian
 * @date2016/5/25 0025
 */
@Component
@Produces("application/json")
@Path("prescTemp")
public class DrugPrescTempRest {
    @Reference(version = "1.0.0")
    private DrugPrescTempServiceApi drugPrescTempServiceApi;

    /**
     * 拿到近一个月的处方列表
     * @param prescDate
     * @param dispensary
     * @param dispensarySub
     * @return
     */
    @Path("getPrescMasterTemp")
    @GET
    public List<DrugPrescMasterTemp> getPrescMasterTemp(@QueryParam(value = "prescDate")String prescDate,@QueryParam(value = "dispensary")String dispensary,@QueryParam(value = "dispensarySub")String dispensarySub){
       return drugPrescTempServiceApi.getPrescMasterTemp(dispensary,"B01");
    }

    /**
     * 通过主表Id拿到明细表信息
     * @param masterId
     * @return
     */
    @Path("getDetail")
    @GET
    public List<DrugPrescDetailTemp> getDetail(@QueryParam(value = "masterId")String masterId){
        return drugPrescTempServiceApi.getDetail(masterId);
    }

    /**
     * 确认发药
     * @param id
     * @return
     */
    @Path("confirmDrug")
    @POST
    public StringData confirmDrug(String id){
        String num = drugPrescTempServiceApi.confirmDrug(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }
}
