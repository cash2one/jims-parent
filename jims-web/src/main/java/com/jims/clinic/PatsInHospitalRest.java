package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.PatsInHospitalServiceApi;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.data.StringData;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.entity.BedRec;
import com.jims.patient.api.PatMasterIndexServiceApi;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * 病人在院记录
 * @author CTQ
 * @date 2016-06-06 09:36:49
 */
@Component
@Produces("application/json")
@Path("patsInHospital")
public class PatsInHospitalRest {
    @Reference(version = "1.0.0")
    PatMasterIndexServiceApi patMasterIndexServiceApi;
    @Reference(version = "1.0.0")
    PatsInHospitalServiceApi patsInHospitalServiceApi;
    @Path("patientlist")
    /**
     * @return java.util.List<com.jims.clinic.vo.ComeDeptVo>    返回类型
     * @throws
     * @Title: patientlist
     * @Description: (加载代入科病人列表)
     * @author CTQ
     * @date 2016/6/15
     */
    @GET
    public List<ComeDeptVo> patientlist(){

        List<ComeDeptVo> list = Lists.newArrayList();
        try {
            list = patMasterIndexServiceApi.findWaitFrom();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param       comeDeptVo      传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: save
     * @Description: (保存入科信息)
     * @author CTQ
     * @date 2016/6/15
     */
    @Path("save")
    @POST
    public StringData save(HttpServletRequest request,ComeDeptVo comeDeptVo){
        StringData stringData=new StringData();
        try {
            String data = patsInHospitalServiceApi.saveHospInfo(comeDeptVo);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @param       comeDeptVo      传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: turnOutDept
     * @Description: (病人转出)
     * @author CTQ
     * @date 2016/6/15
     */
    @Path("turnOutDept")
    @POST
    public StringData turnOutDept(HttpServletRequest request,ComeDeptVo comeDeptVo){
        StringData stringData=new StringData();
        try {
            String data = patsInHospitalServiceApi.turnOutDept(comeDeptVo);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @param         bedRec    传递参数
     * @return BaseDto    返回类型
     * @throws
     * @Title: searchInfoByParams
     * @Description: (根据床位号，病区查询病人信息--病人流转-出院)
     * @author CTQ
     * @date 2016/7/6
     */
    @Path("searchInfoByParams")
    @POST
    public BaseDto searchInfoByParams(BedRec bedRec){
        BaseDto baseDto = patsInHospitalServiceApi.searchInfoByParams(bedRec.getBedNo(),bedRec.getWardCode());
        return baseDto;
    }
    /**
     * @param         bedRec    传递参数
     * @return BaseDto    返回类型
     * @throws
     * @Title: searchTurnOutInfoByParams
     * @Description: (根据床位号，病区查询病人信息--病人流转-转出)
     * @author CTQ
     * @date 2016/7/6
     */
    @Path("searchTurnOutInfoByParams")
    @POST
    public BaseDto searchTurnOutInfoByParams(BedRec bedRec){
        BaseDto baseDto = patsInHospitalServiceApi.searchTurnOutInfoByParams(bedRec.getBedNo(), bedRec.getWardCode());
        return baseDto;
    }

    /**
     * 出院
     * @param request
     * @param comeDeptVo
     * @return
     */
    @Path("leaveHosp")
    @POST
    public StringData leaveHosp(HttpServletRequest request,ComeDeptVo comeDeptVo){
        StringData stringData=new StringData();
        try {
            String data = patsInHospitalServiceApi.turnOutDept(comeDeptVo);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @return com.jims.common.web.impl.BaseDto    返回类型
     * @throws
     * @Title: waitTurnOutList
     * @Description: (待转科病人列表)
     * @author CTQ
     * @date 2016/7/6
     */
    @Path("waitTurnOutList")
    @GET
    public List<BaseDto> waitTurnOutList(){

        List<BaseDto> baseDto = patsInHospitalServiceApi.waitTurnOutList();
        return baseDto;
    }

}
