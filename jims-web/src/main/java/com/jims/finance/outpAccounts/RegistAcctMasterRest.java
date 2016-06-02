package com.jims.finance.outpAccounts;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.finance.outpAccounts.api.RegistAcctMasterServiceApi;
import com.jims.finance.outpAccounts.entity.RegistAcctMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 挂号结账主记录
 * Created by  CTQ
 * DATE 2016-06-01 10:03:04
 */
@Component
@Produces("application/json")
@Path("registAcctMaster")
public class RegistAcctMasterRest {

    @Reference(version = "1.0.0")
    RegistAcctMasterServiceApi registAcctMasterServiceApi;
    @Path("save")
    @POST
    public StringData save(RegistAcctMaster registAcctMaster){
        StringData stringData=new StringData();
        try {
            String data = registAcctMasterServiceApi.saveMaster(registAcctMaster);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }

}
