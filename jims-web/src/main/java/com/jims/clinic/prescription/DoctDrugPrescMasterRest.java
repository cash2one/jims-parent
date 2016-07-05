package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.prescription.api.DoctDrugPrescMasterServiceApi;
import com.jims.prescription.entity.DoctDrugPrescMaster;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.Date;
import java.util.List;

/**
 * 待发药住院处方主记录Rest
 * @author CTQ
 * @date 2016年5月16日15:23:08
 */
@Component
@Produces("application/json")
@Path("doctDrugPrescMaster")
public class DoctDrugPrescMasterRest {

    @Reference(version = "1.0.0")
    DoctDrugPrescMasterServiceApi doctDrugPrescMasterServiceApi;

    /**
     * @param        patientId     传递参数
     * @return java.util.List<com.jims.prescription.entity.DoctDrugPrescMaster>    返回类型
     * @throws
     * @Title: list
     * @Description: (查询处方下达左侧处方列表)
     * @author CTQ
     * @date 2016/5/16
     */
    @Path("list")
    @GET
    public List<DoctDrugPrescMaster> list(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("patientId") String patientId){
        List<DoctDrugPrescMaster> list = Lists.newArrayList();
        DoctDrugPrescMaster ddpm = new DoctDrugPrescMaster();
        try {
            list = doctDrugPrescMasterServiceApi.findListByParams(ddpm);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @param   doctDrugPrescMaster          传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: save
     * @Description: (保存住院处方及相关信息)
     * @author CTQ
     * @date 2016/5/16
     */
    @Path("save")
    @POST
    public StringData save(DoctDrugPrescMaster doctDrugPrescMaster){
        StringData stringData=new StringData();
        try {
            String data = doctDrugPrescMasterServiceApi.savePresc(doctDrugPrescMaster);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @param   ids          传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: delete
     * @Description: (毁方-删除住院处方及相关信息)
     * @author CTQ
     * @date 2016/5/16
     */
    @Path("delete")
    @POST
    public StringData delete(String ids){
        StringData stringData= null;
        try {
            stringData = new StringData();
            String num=doctDrugPrescMasterServiceApi.deletePresc(ids);
            stringData.setCode(num);
            stringData.setData("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @return com.jims.prescription.entity.DoctDrugPrescMaster    返回类型
     * @throws
     * @Title: getPrescMaster
     * @Description: (处理新方处方号和处方日期)
     * @author CTQ
     * @date 2016/5/18
     */
    @Path("getPrescMaster")
    @POST
    public DoctDrugPrescMaster getPrescMaster() {
        DoctDrugPrescMaster doctDrugPrescMaster = new DoctDrugPrescMaster();
        try {
//            doctDrugPrescMaster = doctDrugPrescMasterServiceApi.get(id);
            //查询该医院下处方的处方号
            Integer prescNo = doctDrugPrescMasterServiceApi.searchPrescNo(""/*doctDrugPrescMaster.getVisitId()*/);
            doctDrugPrescMaster.setPrescDate(new Date());
            doctDrugPrescMaster.setPrescNo(prescNo!=null?prescNo:1);
            doctDrugPrescMaster.setPrescStatus(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctDrugPrescMaster;
    }
}
