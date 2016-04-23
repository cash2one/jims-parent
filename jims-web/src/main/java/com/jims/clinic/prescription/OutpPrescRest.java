package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.data.StringData;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by CTQ
 * 处方医嘱明细记录
 */
@Component
@Produces("application/json")
@Path("outppresc")
public class OutpPrescRest {
    @Reference(version = "1.0.0")
    OutpPrescServiceApi outpPrescServiceApi;

    /**根据当前处方信息查询处方医嘱明细记录**/

    @Path("list")
    @GET
    public List<OutpPresc> list(){
        OutpPresc op = new OutpPresc();
        String clinicMasterId ="1";
        List<OutpPresc> list = Lists.newArrayList();
        try {
            //list = outpPrescServiceApi.getOutpPresc(clinicMasterId);
            op.setId("1");
            op.setVisitDate(DateUtils.parseDate("2015-06-09 00:00:00", "yyyy-MM-dd HH:mm:ss"));
            op.setVisitNo(410);
            op.setSerialNo("2501263");
            op.setPrescNo(1094);
            op.setItemNo(1);
            op.setItemClass("A");
            op.setDrugCode("A0201KL00");
            op.setDrugName("补血颗粒");
            op.setDrugSpec("4mg*10");
            op.setFirmId("万达");
            op.setUnits("袋");
            op.setAmount(1.0000);
            op.setDosage(4.0000);
            op.setDosageUnits("mg");
            op.setAdministration("冲服");
            op.setFrequency("3/日");
            op.setProvidedIndicator(0);
            op.setCosts(24.5000);
            op.setCharges(24.5000);
            op.setChargeIndicator(0);
            op.setDispensary("150520");
            op.setRepetition(1);
            op.setOrderNo(4);
            op.setSubOrderNo(1);
            op.setGetdrugFlag(1);
            op.setPrescAttr("门诊处方");
            op.setAbidance(4);
            list.add(op);

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Path("save")
    @POST
    public StringData save(OutpPresc outpPresc){
        StringData stringData=new StringData();
       /* String num=dictService.save(dict);
        stringData.setCode(num);
        stringData.setData("success");*/
        return stringData;
    }
}
