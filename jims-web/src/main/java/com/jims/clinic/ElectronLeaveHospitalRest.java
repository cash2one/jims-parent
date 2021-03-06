package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ElectronLeaveHopitalServiceApi;
import com.jims.clinic.entity.ElectronLeaveHospital;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("electronleavehospital")
public class ElectronLeaveHospitalRest {

    @Reference(version = "1.0.0")
    private ElectronLeaveHopitalServiceApi electronLeaveHopitalServiceApi ;

     /**
     * 保存出院记录
     * @param -electronEnterHospital
     * @return
     */

    @Path("save")
    @POST
    public StringData save(ElectronLeaveHospital electronLeaveHospital,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        StringData data=new StringData();
        String num=data.getCode();
        if(electronLeaveHospital!=null){
            num= electronLeaveHopitalServiceApi.save(electronLeaveHospital,loginInfo);
        }
        data.setCode(num);
        if(!"".equals(num)&&!"0".equals(num)){
            data.setData("success");
        }else{
            data.setData("error");
        }

        return data;
    }

    /**
     * 获取单条数据
     * 根据住院ID查询出院记录
     * @param-patVisitId
     * @return
     */
    @Path("get")
    @POST
    public ElectronLeaveHospital get(ElectronLeaveHospital electronLeaveHospital){
        ElectronLeaveHospital entity=electronLeaveHopitalServiceApi.getLeaveByVisit(electronLeaveHospital);
        return entity;
    }
}
