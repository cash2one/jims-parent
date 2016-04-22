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
    public List<OutpPresc> list(OutpPresc outpPresc){
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.findList(outpPresc);
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
