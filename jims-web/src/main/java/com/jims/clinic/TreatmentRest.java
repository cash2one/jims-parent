package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.api.OutpTreatRecServiceApi;
import com.jims.clinic.api.TreatmentServiceApi;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    @Reference(version = "1.0.0")
    private  OutpTreatRecServiceApi outpTreatRecServiceApi;

    /**
     * 查询治疗处置项目
     * pq
     * @return
     */
    @Path("findList")
    @GET
    public PageData findList(){
        PageData pageData=new PageData();
        pageData.setRows(treatmentServiceApi.findTreatment("15001159"));
        return  pageData;
    }

    /**
     * 保存治疗处置项目
     * @param outpOrdersCostses
     * @param clinicId
     * @return
     */
    @Path("save")
    @POST
    public StringData saveTreatment(List<OutpTreatRec> outpOrdersCostses,String clinicId){
        StringData data=new StringData();
        String code=treatmentServiceApi.saveClinicItem(outpOrdersCostses,clinicId);
        data.setCode(code);
        return  data;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Path("delete")
    @POST
    public int delete(String id){
       OutpTreatRec outpTreatRec= outpTreatRecServiceApi.get(id);
        return  treatmentServiceApi.deleteTreat(outpTreatRec);


    }
}
