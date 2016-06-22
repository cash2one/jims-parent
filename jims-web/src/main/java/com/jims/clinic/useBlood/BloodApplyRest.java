package com.jims.clinic.useBlood;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.blood.api.BloodApplyServiceApi;
import com.jims.blood.api.BloodCapacityServiceApi;
import com.jims.blood.api.BloodComponentServiceApi;
import com.jims.blood.entity.BloodApply;
import com.jims.blood.entity.BloodCapacity;
import com.jims.blood.entity.BloodComponent;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.StringUtils;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.patient.entity.PatVisit;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<BloodApply> page = bloodApplyServiceApi.findPage(new Page<BloodApply>(request, response), new BloodApply());
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 保存用血申请记录
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
     * 保存用血申请记录
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
     * 根据id查询手术申请表信息
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
}
