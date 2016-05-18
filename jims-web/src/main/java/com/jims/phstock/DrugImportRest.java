package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * 药品入库Rest
 * @author lgx
 * @version 2016/5/18
 */
@Component
@Produces("application/json")
@Path("drug-in")
public class DrugImportRest {

    @Reference(version = "1.0.0")
    private DrugImportServiceApi drugImportServiceApi;

    @POST
    @Path("save")
    public StringData save(DrugImportMaster master){
        if(master != null){
            StringData resultData = new StringData();
            List<DrugImportDetail> details = master.getDetailList();
            master.setDetailList(null);
            resultData.setCode("0");
            List<String> result = new ArrayList<String>();
            result.add(drugImportServiceApi.save(master));
            result.add(drugImportServiceApi.saveDetailBatch(details));
            resultData.setDatas(result);
            return resultData;
        }
        return null;

    }
}
