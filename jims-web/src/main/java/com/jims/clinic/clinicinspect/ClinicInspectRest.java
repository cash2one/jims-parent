package com.jims.clinic.clinicinspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ExamAppointsServiceApi;;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.api.OutpTreatRecServiceApi;
import com.jims.clinic.entity.*;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.vo.ExamAppointsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/25.
 */

@Component
@Produces("application/json")
@Path("clinicInspect")
public class ClinicInspectRest {

    @Reference(version = "1.0.0")
    private ExamAppointsServiceApi examAppointsServiceApi;
    @Reference(version = "1.0.0")
    private OutpOrdersCostsServiceApi outpOrdersCostsServiceApi;
    @Reference(version = "1.0.0")
    private OutpTreatRecServiceApi outpTreatRecServiceApi;

    @Path("list")
    @GET
    public PageData findList(@Context HttpServletRequest request, @Context HttpServletResponse response){
        Page<ExamAppoints> page=examAppointsServiceApi.findPage(new Page<ExamAppoints>(request,response),new ExamAppoints());
        PageData<ExamAppoints> pageData=new PageData<ExamAppoints>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
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

    @POST
    @Path("saveExamAppoints")
    public StringData saveExamAppoints(ExamAppoints examAppoints){
        StringData stringData=new StringData();
        int num= examAppointsServiceApi.batchSave(examAppoints);
        stringData.setCode(num+"");
        return stringData;
    }

    @Path("del")
    @POST
    public StringData deleteExamAppionts(String id) {
        StringData stringData = new StringData();
        int num = examAppointsServiceApi.deleteExamAppionts(id);
            stringData.setCode(num+"");
            stringData.setData("success");
            return stringData;

    }
}
