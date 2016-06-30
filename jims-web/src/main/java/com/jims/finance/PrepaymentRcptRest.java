package com.jims.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.common.data.StringData;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.PrepaymentRcptServiceApi;
import com.jims.finance.entity.PrepaymentRcpt;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.Date;
import java.util.List;

/**
 * 预交金
 * Created by  CTQ
 * DATE 2016-05-30 10:11:55
 */
@Component
@Produces("application/json")
@Path("prepaymentRcpt")
public class PrepaymentRcptRest {
    @Reference(version = "1.0.0")
    PrepaymentRcptServiceApi prepaymentRcptServiceApi;
    @Path("list")
    @GET
    public List<PrepaymentRcpt> getPatientList(@QueryParam(value = "patientId")String patientId){
        PrepaymentRcpt prepaymentRcpt = new PrepaymentRcpt();
        List<PrepaymentRcpt> list = Lists.newArrayList();
        try {
            list = prepaymentRcptServiceApi.findList(prepaymentRcpt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @param        prepaymentRcpt     传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: save
     * @Description: (保存预交金)
     * @author CTQ
     * @date 2016/5/30
     */
    @Path("save")
    @POST
    public StringData save(PrepaymentRcpt prepaymentRcpt){

        StringData stringData=new StringData();
        try {
            prepaymentRcpt.setTransactDate(new Date());
            prepaymentRcpt.setTransactType("交款");
            prepaymentRcpt.setUsedFlag("0");
            prepaymentRcpt.setRcptNo("");//预交金收据号
            prepaymentRcpt.setOperatorNo("1");//收款员号
            String data = prepaymentRcptServiceApi.save(prepaymentRcpt);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @param      id       传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: update
     * @Description: (更新预交金状态)
     * @author CTQ
     * @date 2016/5/30
     */
    @Path("update")
    @POST
    public StringData update(String id){
        PrepaymentRcpt prepaymentRcpt = new PrepaymentRcpt();
        prepaymentRcpt = prepaymentRcptServiceApi.get(id);
        StringData stringData=new StringData();
        try {
            prepaymentRcpt.setTransactType("作废");
            String data = prepaymentRcptServiceApi.save(prepaymentRcpt);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }

    /**
     * @param      startDate,   endDate    传递参数
     * @return java.util.List<com.jims.finance.entity.PrepaymentRcpt>    返回类型
     * @throws
     * @Title: recordlist
     * @Description: (这里用一句话描述这个方法的作用)
     * @author JIMS
     * @date 2016/6/29
     */
    @Path("recordlist")
    @GET
    public List<BaseDto> recordlist(@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate,@QueryParam("transactType") String transactType){

        PrepaymentRcpt prepaymentRcpt = new PrepaymentRcpt();
        prepaymentRcpt.setStartDate(startDate);
        prepaymentRcpt.setEndDate(endDate);
        prepaymentRcpt.setTransactType(transactType);
        List<BaseDto> list = Lists.newArrayList();
        try {
            list = prepaymentRcptServiceApi.findRecordList(prepaymentRcpt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
