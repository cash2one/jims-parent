package com.jims.finance.outpAccounts;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.finance.api.OutpAcctMasterServiceApi;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 门诊收费结帐主记录
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Component
@Produces("application/json")
@Path("outpAcctMaster")
public class OutpAcctMasterRest {
    @Reference(version = "1.0.0")
    private OutpAcctMasterServiceApi outpAcctMasterServiceApi;

    @Path("save")
    @POST
    public StringData save(OutpRcptMaster outpRcptMaster){
      String code =  outpAcctMasterServiceApi.saveOutpAcct(outpRcptMaster);
        StringData stringData=new StringData();
        stringData.setCode(code);
        if(!"0".equals(code)||!"".equals(code)){
            stringData.setData("sucess");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    @Path("getMaxAcctNo")
    @GET
    public String getMaxAcctNo(){
      String acctNo =  outpAcctMasterServiceApi.getMaxAcctNo();
       return acctNo;
    }
}
