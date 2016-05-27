package com.jims.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.BaseData;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.OutPatientCostServiceApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 * 划价收费
 * @author zhangyao
 */
@Component
@Produces("application/json")
@Path("outPatientCost")
public class OutpatientCostRest {
    @Reference(version = "1.0.0")
    private OutPatientCostServiceApi outPatientCostApi;


    /**
     * 根据门诊号查询处方，处置治疗信息
     * @return
     */
    @Path("list")
    @GET
    public BaseData<BaseDto>  getPatientList(@QueryParam("clinicNo") String clinicNo,@QueryParam("orgId") String orgId){
        BaseData<BaseDto> baseData=outPatientCostApi.list(orgId, clinicNo);
        return baseData;
    }
}
