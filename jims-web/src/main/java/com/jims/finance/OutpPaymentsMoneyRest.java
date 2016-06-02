package com.jims.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.DateUtils;
import com.jims.finance.api.OutpPaymentsMoneyServiceApi;
import com.jims.finance.entity.OutpPaymentsMoney;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 门诊病人支付方式记录
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Component
@Produces("application/json")
@Path("outpPaymentsMoney")
public class OutpPaymentsMoneyRest {
    @Reference(version = "1.0.0")
    private OutpPaymentsMoneyServiceApi outpPaymentsMoneyServiceApi;

    /**
     * 方法 findMaoneyPayment 的功能描述
     * 门诊结算 - 收费结账
     * @param
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    @Path("findMaoneyPayment")
    @GET
    public List<OutpPaymentsMoney> findMaoneyPayment(@QueryParam("visitDate")String visitDate, @QueryParam("operatorNo")String operatorNo){

     //   SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        OutpRcptMaster outpRcptMaster=new OutpRcptMaster();
            outpRcptMaster.setVisitDate(DateUtils.parseDate(visitDate));
        outpRcptMaster.setOperatorNo(operatorNo);
        return outpPaymentsMoneyServiceApi.findMaoneyPayment(outpRcptMaster);
    }
}
