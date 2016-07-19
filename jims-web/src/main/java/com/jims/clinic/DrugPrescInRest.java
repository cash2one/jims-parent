package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.prescription.api.DoctDrugPrescDetailServiceApi;
import com.jims.prescription.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.api.DrugPrescInServiceApi;
import com.jims.prescription.entity.DoctDrugPrescDetail;
import com.jims.prescription.entity.DoctDrugPrescMaster;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<DoctDrugPrescMaster> list(@QueryParam("visitId") String visitId,@QueryParam("prescNo")int prescNo,@QueryParam("dispensary")String dispensary,@QueryParam("prescDate") String prescDate,@QueryParam("name") String name,@QueryParam("orgId") String orgId){
        List<DoctDrugPrescMaster> list = Lists.newArrayList();
        DoctDrugPrescMaster ddpm = new DoctDrugPrescMaster();
        ddpm.setVisitId(visitId);
        ddpm.setPrescNo(prescNo);
        ddpm.setName(name);
        ddpm.setDispensary(dispensary);
        ddpm.setOrgId(orgId);
        try {
             if(prescDate!=null&&!"".equals(prescDate)) {
             ddpm.setPrescDate(new SimpleDateFormat("yyyy-MM-dd").parse(prescDate));
             }
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
    public StringData confirmInDrugPresc(@QueryParam(value="masterId")String masterId,@QueryParam(value="persionId") String persionId,@QueryParam("deptName") String deptName){

       String code=drugPrescInServiceApi.confirmInDrugPresc(masterId,persionId,deptName);
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
