package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * 病人主索引
 * @Created by CTQ
 * @DATE 2016/5/25.
 */
@Component
@Produces("application/json")
@Path("patMasterIndex")
public class PatMasterIndexRest {

    @Reference(version = "1.0.0")
    PatMasterIndexServiceApi patMasterIndexServiceApi;

    @Path("list")
    @GET
    public List<PatMasterIndex> getPatientList(@QueryParam(value = "patientId")String patientId){
        PatMasterIndex patMasterIndex = new PatMasterIndex();
        List<PatMasterIndex> list = Lists.newArrayList();
        try {
            list = patMasterIndexServiceApi.findList(patMasterIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
