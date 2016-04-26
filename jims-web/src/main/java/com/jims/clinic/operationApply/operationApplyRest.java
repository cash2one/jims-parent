package com.jims.clinic.operationApply;

import com.google.common.collect.Lists;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordEachdis;
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
 * Created by qinlongxin on 2016/4/25.
 */
@Component
@Produces("application/json")
@Path("operationApply")
public class operationApplyRest {
    @Path("list")
    @GET
    public List<OutpPresc> list(){

        OutpPresc op = new OutpPresc();
        List<OutpPresc> list = Lists.newArrayList();
        try {
//            list = outpPrescServiceApi.findList(outpPresc);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 保存每日病程
     */
    @Path("save")
    @POST
    public StringData save(CourseRecordEachdis courseRecordEachdis) {
        StringData data = new StringData();
        String num = data.getCode();
        data.setCode("1");
        data.setData("success");
        return data;
    }
}
