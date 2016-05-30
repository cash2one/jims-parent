package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.DoctDrugPrescDetailServiceApi;
import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.clinic.entity.DoctDrugPrescMaster;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
}
