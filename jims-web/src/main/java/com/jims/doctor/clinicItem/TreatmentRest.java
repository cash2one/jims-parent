package com.jims.doctor.clinicItem;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.OutpTreatRecServiceApi;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.doctor.clinicItem.api.TreatmentServiceApi;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 处置治疗
 *
 * @author PangQian
 * @date2016/5/7 0007
 */
@Component
@Produces("application/json")
@Path("treatment")
public class TreatmentRest {

    @Reference(version = "1.0.0")
    private TreatmentServiceApi treatmentServiceApi;
    @Reference(version = "1.0.0")
    private  OutpTreatRecServiceApi outpTreatRecServiceApi;

    /**
     * 查询治疗处置项目
     * pq
     * @return
     */
    @Path("findList")
    @GET
    public PageData findList(@QueryParam("clinicId")String clinicId){
        PageData pageData=new PageData();
        pageData.setRows(treatmentServiceApi.findTreatment(clinicId));
        return  pageData;
    }

    /**
     * 保存治疗处置项目
     * @param outpTreatRecList
     * @return
     */
    @Path("save")
    @POST
    public StringData saveTreatment(List<OutpTreatRec> outpTreatRecList,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        StringData data=new StringData();
        String code=treatmentServiceApi.saveClinicItem(outpTreatRecList,loginInfo);
        data.setCode(code);
        return  data;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Path("delete")
    @POST
    public int delete(String id){
        int num =  treatmentServiceApi.deleteTreat(id);
        return num;
    }
}
