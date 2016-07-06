package com.jims.doctor.useBlood;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.blood.api.BloodApplyServiceApi;
import com.jims.blood.api.BloodCapacityServiceApi;
import com.jims.blood.api.BloodComponentServiceApi;
import com.jims.blood.entity.BloodApply;
import com.jims.blood.entity.BloodCapacity;
import com.jims.blood.entity.BloodComponent;
import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.StringUtils;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.patient.entity.PatVisit;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 用血申请
 * Created by qinlongxin on 2016/4/29.
 */
@Component
@Produces("application/json")
@Path("bloodApply")
public class BloodApplyRest {
    @Reference(version = "1.0.0")
    private BloodApplyServiceApi bloodApplyServiceApi;
    @Reference(version = "1.0.0")
    private BloodComponentServiceApi bloodComponentServiceApi;
    @Reference(version = "1.0.0")
    private BloodCapacityServiceApi bloodCapacityServiceApi;
    @Reference(version = "1.0.0")
    private PatVisitServiceApi patVisitServiceApi;
    @Reference(version = "1.0.0")
    private ClinicMasterServiceApi clinicMasterServiceApi;



    /**
     * 点击用血申请获取病人信息通过patient_id获得
     * @return
     */
    @Path("getPatientInformation")
    @GET
    public StringData getPatientInformation(){
        StringData stringData = new StringData();
        String patientId="15006135";
        PatVisit patVisit = patVisitServiceApi.getPatientInformation(patientId);
        return stringData;
    }

    /**
     * 获得血液成分列表
     * @return
     */
    @Path("getBloodComponent")
    @GET
    public List<BloodComponent> getBloodComponent() {
        List<BloodComponent> list = bloodComponentServiceApi.getBloodComponent();
        return list;
    }

    /**
     * 门诊用血申请保存
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("clinicId")String clinicId) {
        BloodApply bloodApply = new BloodApply();
        bloodApply.setClinicId(clinicId);
        Page<BloodApply> page = bloodApplyServiceApi.findPage(new Page<BloodApply>(request, response),bloodApply);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 住院用血申请
     *
     * @param request
     * @param response
     * @return
     */
    @Path("listHos")
    @GET
    public PageData listHos(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("visitId")String visitId) {
        BloodApply bloodApply = new BloodApply();
        bloodApply.setVisitId(visitId);
        Page<BloodApply> page = bloodApplyServiceApi.findPage(new Page<BloodApply>(request, response),bloodApply);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 门诊用血申请保存
     */
    @Path("save")
    @POST
    public StringData save(BloodApply bloodApply) {
        StringData data = new StringData();
        String num = data.getCode();
        if (bloodApply != null) {
            num = bloodApplyServiceApi.saveBloodApply(bloodApply);
        }
        data.setCode(num);
        data.setData("success");
        return data;
    }
    /**
     * 住院用血申请保存
     */
    @Path("saveHos")
    @POST
    public StringData saveHos(BloodApply bloodApply) {
        StringData data = new StringData();
        String num = data.getCode();
        if (bloodApply != null) {
            num = bloodApplyServiceApi.saveHosBloodApply(bloodApply);
        }
        data.setCode(num);
        data.setData("success");
        return data;
    }

    /**
     * 门诊用血申请记录删除
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        StringData stringData = new StringData();
        String num = bloodApplyServiceApi.delete(ids);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 根据id查询手术申请表信息
     */
    @Path("getBloodApply")
    @POST
    public BloodApply getBloodApply(String id) {
        BloodApply bloodApply = bloodApplyServiceApi.get(id);
        return bloodApply;
    }

    /**
     * 根据id查询用血申请表信息
     */
    @Path("getBloodCapacityList")
    @POST
    public List<BloodCapacity> getBloodCapacityList(String applyNum) {
        BloodCapacity bloodCapacity = new BloodCapacity();
        if (StringUtils.isNotBlank(applyNum)) {
            int index = applyNum.indexOf("=");
            bloodCapacity.setApplyNum(applyNum.substring(index + 1));
            List<BloodCapacity> bloodCapacityList = bloodCapacityServiceApi.getBloodCapacityList(bloodCapacity);
            return bloodCapacityList;
        }
        return null;
    }

    @Path("getPatient")
    @GET
    public ClinicMaster getPatient(@QueryParam("id")String id){
        ClinicMaster clinicMaster=clinicMasterServiceApi.getPatient(id);
        return clinicMaster;
    }
}
