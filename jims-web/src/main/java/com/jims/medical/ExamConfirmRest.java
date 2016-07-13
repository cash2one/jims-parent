package com.jims.medical;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.exam.api.ExamConfirmServiceApi;
import com.jims.exam.entity.ExamAppoints;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 检查确认Controller
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("examConfirm")
public class ExamConfirmRest {
    @Reference(version = "1.0.0")
    private ExamConfirmServiceApi examConfirmServiceApi;

    /**
     * 检查确认 列表
     * @return
     * @author zhaoning
     */
    @GET
    @Path("getExamAppoints")
    public List<ExamAppoints> getExamAppoints(@QueryParam("outOrIn")String outOrIn){
        String performedBy="240101";//当前登录 人执行科室人
        return examConfirmServiceApi.getExamAppointses(performedBy,outOrIn);
    }

    /**
     * 确认检查
     * @param examAppoints
     * @return
     */
    @POST
    @Path("confirmExam")
    public StringData confirmExam(ExamAppoints examAppoints)throws Exception{
     StringData data=new StringData();
        data.setCode(examConfirmServiceApi.confrimExam(examAppoints));
        return data;
    }
}
