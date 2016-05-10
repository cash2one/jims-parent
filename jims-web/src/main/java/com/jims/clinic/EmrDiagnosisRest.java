package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.EmrDiagnosisServiceApi;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * EmrDiagnosisRest
 *
 * @author PangQian
 * @date2016/4/29 0029
 */
@Component
@Produces("application/json")
@Path("diagnosis")
public class EmrDiagnosisRest {
    @Reference(version = "1.0.0")
    private EmrDiagnosisServiceApi emrDiagnosisServiceApi;


    @Path("findList")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<EmrDiagnosis> page = emrDiagnosisServiceApi.findPage(new Page<EmrDiagnosis>(request,response), new EmrDiagnosis());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    @Path("save")
    @POST
    public StringData saveDiagnosis(List<EmrDiagnosis> emrDiagnosisList) {
        StringData data=new StringData();
        data.setCode(emrDiagnosisServiceApi.saveDiagnosis(emrDiagnosisList));
        return data;
    }
   @Path("delete")
   @POST
    public StringData delete(String id){
       StringData data=new StringData();
       data.setCode(emrDiagnosisServiceApi.delete(id));
       return data;
   }
}
