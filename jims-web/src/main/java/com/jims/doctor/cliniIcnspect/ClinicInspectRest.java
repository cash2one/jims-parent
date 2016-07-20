package com.jims.doctor.cliniIcnspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.diagnosis.api.EmrDiagnosisServiceApi;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.exam.api.ExamAppointsServiceApi;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.exam.api.ExamItemsServiceApi;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 * 检查申请（门诊、住院）
 */

@Component
@Produces("application/json")
@Path("clinicInspect")

public class ClinicInspectRest {

    @Reference(version = "1.0.0")
    private ExamAppointsServiceApi examAppointsServiceApi;
    @Reference(version = "1.0.0")
    private EmrDiagnosisServiceApi emrDiagnosisServiceApi;
    @Reference(version = "1.0.0")
    private ExamItemsServiceApi examItemsServiceApi;

    /**
     * 查看住院检查列表
     * @param request
     * @param response
     * @param visitId
     * @return
     */
    @Path("listHos")
    @GET
    public PageData listHos(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("visitId")String visitId,
                            @QueryParam("patientId")String patientId){
        ExamAppoints examAppoints= new ExamAppoints();
        examAppoints.setVisitId(visitId);
        examAppoints.setPatientId(patientId);
        Page<ExamAppoints> page=examAppointsServiceApi.findPage(new Page<ExamAppoints>(request,response),examAppoints);
        PageData<ExamAppoints> pageData=new PageData<ExamAppoints>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查看门诊检查列表
     * @param request
     * @param response
     * @param clinicId
     * @return
     */
    @Path("list")
    @GET
    public PageData findList(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("clinicId")String clinicId){
        ExamAppoints examAppoints= new ExamAppoints();
        examAppoints.setClinicId(clinicId);
        Page<ExamAppoints> page=examAppointsServiceApi.findPage(new Page<ExamAppoints>(request,response),examAppoints);
        PageData<ExamAppoints> pageData=new PageData<ExamAppoints>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }


    @Path("getDescription")
    @GET
    public EmrDiagnosis getDescription(@QueryParam("clinicIds") String clinicIds,@QueryParam("visitIds") String visitIds){
       EmrDiagnosis emrDiagnosis= emrDiagnosisServiceApi.getDescription(clinicIds,visitIds);
       return emrDiagnosis;
    }
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public ExamAppoints get(String id) {
        ExamAppoints examAppoints = examAppointsServiceApi.get(id);
        return examAppoints;
    }

    /***
     * 门诊检查申请保存
     * @param examAppoints
     * @return
     */
    @POST
    @Path("saveExamAppoints")
    public StringData saveExamAppoints(ExamAppoints examAppoints,@Context HttpServletRequest request, @Context HttpServletResponse response){
        StringData stringData=new StringData();
//        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
//        examAppoints.setReqPhysician(loginInfo.getPersionId());
//        examAppoints.setReqDept(loginInfo.getDeptId());
//        examAppoints.setDoctorUser(loginInfo.getUserName());
        int num= examAppointsServiceApi.batchSave(examAppoints);
        stringData.setCode(num+"");
        return stringData;
    }

    /**
     * 住院保存
     * @param examAppoints
     * @return
     */
    @POST
    @Path("saveHospitalInspect")
    public StringData saveHospitalInspect(ExamAppoints examAppoints){
        StringData stringData=new StringData();
        int num = examAppointsServiceApi.saveHospitalInspect(examAppoints);
        stringData.setCode(num+"");
        return stringData;
    }

    @Path("del")
    @POST
    public StringData deleteExamAppionts(String id) {
        StringData stringData = new StringData();
        String num = examAppointsServiceApi.deleteExamAppionts(id);
            stringData.setCode(num+"");
            stringData.setData("success");
            return stringData;

    }

    @Path("delHos")
    @POST
    public StringData delHos(String id) {
        StringData stringData = new StringData();
        String num = examAppointsServiceApi.delectHosExamAppionts(id);
        stringData.setCode(num+"");
        stringData.setData("success");
        return stringData;

    }

    /**
     * 通过主记录id获取检查子项
     * @param appointsId
     * @return
     */
    @Path("getItemName")
    @POST
    public List<ExamItems> getItemName (String appointsId){
        List<ExamItems> examItemsList = examItemsServiceApi.getItemName(appointsId);
        return examItemsList;
    }
}
