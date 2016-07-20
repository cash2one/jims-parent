package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.DrugPrescTempServiceApi;
import com.jims.clinic.entity.DrugPrescDetailTemp;
import com.jims.clinic.entity.DrugPrescMasterTemp;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 门诊待发药
 *
 * @author PangQian
 * @date2016/5/25 0025
 */
@Component
@Produces("application/json")
@Path("prescTemp")
public class DrugPrescTempRest {
    @Reference(version = "1.0.0")
    private DrugPrescTempServiceApi drugPrescTempServiceApi;

    /**
     * 拿到近一个月的处方列表
     * @param prescDate 处方日期
     * @param dispensary  发药局
     * @param dispensarySub 发药子药局
     * @return
     */
    @Path("getPrescMasterTemp")
    @GET
    public List<DrugPrescMasterTemp> getPrescMasterTemp(@QueryParam(value = "prescDate")String prescDate,
                                                        @QueryParam(value = "dispensary")String dispensary,
                                                        @QueryParam(value = "dispensarySub")String dispensarySub,
                                                        @QueryParam(value = "patientId")String patientId,
                                                        @QueryParam(value = "prescNo")int prescNo,
                                                        @QueryParam(value = "name")String name,@QueryParam(value="orgId") String orgId){
        DrugPrescMasterTemp drugPrescMasterTemp=new DrugPrescMasterTemp();
        drugPrescMasterTemp.setDispensary(dispensary);
        drugPrescMasterTemp.setDispensarySub(dispensarySub);
        drugPrescMasterTemp.setPatientId(patientId);
        drugPrescMasterTemp.setPrescNo(prescNo);
        drugPrescMasterTemp.setOrgId(orgId);
        drugPrescMasterTemp.setName(name);
        if(prescDate!=null&&!"".equals(prescDate)){
            try {
                drugPrescMasterTemp.setPrescDate(new SimpleDateFormat("yyyy-MM-dd").parse(prescDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return drugPrescTempServiceApi.getPrescMasterTemp(drugPrescMasterTemp);
    }

    /**
     * 通过主表Id拿到明细表信息
     * @param masterId
     * @return
     */
    @Path("getDetail")
    @GET
    public List<DrugPrescDetailTemp> getDetail(@QueryParam(value = "masterId")String masterId){
        return drugPrescTempServiceApi.getDetail(masterId);
    }

    /**
     * 确认发药
     * updated by chenxy
     * @param masterId  待发药主记录表主键
     * @param persionId 当前登陆人的persionId
     * @return
     */
    @Path("confirmDrug")
    @POST
    public StringData confirmDrug(@QueryParam(value="masterId") String masterId,@QueryParam(value="persionId") String persionId){
        String num = drugPrescTempServiceApi.confirmDrug(masterId,persionId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }
}
