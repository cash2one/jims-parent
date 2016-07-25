package com.jims.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.finance.api.OutpBillItemsServiceApi;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 门诊病人诊疗收费项目明细
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Component
@Produces("application/json")
@Path("outpBillItems")
public class OutpBillItemsRest {
    @Reference(version = "1.0.0")
    private OutpBillItemsServiceApi outpBillItemsServiceApi;

    /**
     * 方法 findItems的功能描述
     * 门诊-收费结账-项目
     * @param visitDate
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    @Path("findItems")
    @GET
    public List<OutpBillItems> findItems(@QueryParam("visitDate")String visitDate,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        OutpRcptMaster outpRcptMaster=new OutpRcptMaster();
        outpRcptMaster.setOperatorNo(loginInfo.getPersionId());
       return outpBillItemsServiceApi.findItems(outpRcptMaster);
    }
}
