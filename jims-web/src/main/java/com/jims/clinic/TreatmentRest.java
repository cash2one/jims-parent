package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.TreatmentServiceApi;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * 处置治疗
 *
 * @author PangQian
 * @date2016/5/7 0007
 */
@Component
@Produces("application/json")
@Path("treatment")
public class TreatmentRest {
    @Reference(version = "1.0.0")
    private TreatmentServiceApi treatmentServiceApi;

    @Path("findList")
    @GET
    public PageData findList(){
        PageData pageData=new PageData();
        pageData.setRows(treatmentServiceApi.findTreatment(new OutpOrdersCosts()));
        return  pageData;
    }
 /*   @Path("save")
    @GET
    public StringData saveTreatment(){

    }*/

}
