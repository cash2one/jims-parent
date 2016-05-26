package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugExportServiceApi;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 药品出库Rest
 * @author lgx
 * @version 2016/5/23
 */
@Component
@Produces("application/json")
@Path("drug-out")
public class DrugExportRest {

    @Reference(version = "1.0.0")
    private DrugExportServiceApi api;

    @POST
    @Path("save")
    public String save(DrugExportMaster master){
        return api.saveMasterAndDetail(master);
    }
}
