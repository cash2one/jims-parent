package com.jims.clinic.clinicinspect;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ExamAppointsServiceApi;;
import com.jims.clinic.entity.ExamAppoints;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
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
 * Created by Administrator on 2016/4/25.
 */

@Component
@Produces("application/json")
@Path("clinicInspect")
public class ClinicInspectRest {

    @Reference(version = "1.0.0")
    private ExamAppointsServiceApi examAppointsServiceApi;

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
}
