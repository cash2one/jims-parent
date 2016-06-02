package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.DoctDrugPrescDetailServiceApi;
import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.api.DrugPrescInServiceApi;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 住院药品处方发药
 *
 * @author PangQian
 * @date2016/5/30 0030
 */
@Component
@Produces("application/json")
@Path("drugPrescIn")
public class DrugPrescInRest {

    @Reference(version = "1.0.0")
    private  DoctDrugPrescMasterServiceApi doctDrugPrescMasterServiceApi;

    @Reference(version = "1.0.0")
    private DoctDrugPrescDetailServiceApi doctDrugPrescDetailServiceApi;

    @Reference(version = "1.0.0")
    private DrugPrescInServiceApi drugPrescInServiceApi;

    /**
     * 查询发药的处方
     * @param visitId
     * @param prescNo
     * @return
     */
    @Path("list")
    @GET
    public List<DoctDrugPrescMaster> list(@QueryParam("visitId") String visitId,@QueryParam("prescNo")int prescNo,@QueryParam("dispensary")String dispensary){
        List<DoctDrugPrescMaster> list = Lists.newArrayList();
        DoctDrugPrescMaster ddpm = new DoctDrugPrescMaster();
        ddpm.setVisitId(visitId);
        ddpm.setPrescNo(prescNo);
        ddpm.setDispensary(dispensary);
        try {
            list = doctDrugPrescMasterServiceApi.getDrugMasterList(ddpm);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 方法 detailList的功能描述
     * 根据处方主表的ID找到明细记录
     * @param prescMasterId
     * @return
     * @throws 
     * @author pq
     * @date 2016/5/30 0030
     */
    @Path("detailList")
    @GET
    public List<DoctDrugPrescDetail> detailList(@QueryParam(value = "prescMasterId")String prescMasterId){
     return  doctDrugPrescDetailServiceApi.findListByPrescMasterId(prescMasterId);
    }

    /**
     * 方法 confirmInDrugPresc 的功能描述
     *  住院处方发药
     * @param masterId
     * @rturn
     * @thows
     * @author pq
     * @date 2016/5/30 0030
     */
    @Path("confirmInDrugPresc")
    @POST
    public StringData confirmInDrugPresc(String masterId){

      String code=    drugPrescInServiceApi.confirmInDrugPresc(masterId);
        StringData data = new StringData();
        data.setCode(code);
        if(code != "" && Integer.parseInt(code)<=0){
            data.setData("error");
        }else{
            data.setData("success");
        }
        return data;
    }

}