package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.PatsInHospitalServiceApi;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.data.StringData;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.finance.api.PrepaymentRcptServiceApi;
import com.jims.finance.entity.PrepaymentRcpt;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 病人主索引
 * @Created by CTQ
 * @DATE 2016/5/25.
 */
@Component
@Produces("application/json")
@Path("patMasterIndex")
public class PatMasterIndexRest {

    @Reference(version = "1.0.0")
    PatMasterIndexServiceApi patMasterIndexServiceApi;
    @Reference(version = "1.0.0")
    PrepaymentRcptServiceApi prepaymentRcptServiceApi;
    @Reference(version = "1.0.0")
    PatsInHospitalServiceApi patsInHospitalServiceApi;
    /**
     * @return java.util.List<com.jims.patient.entity.PatMasterIndex>    返回类型
     * @throws
     * @Title: getPatientList
     * @Description: (根据条件查询病人列表)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("list")
    @GET
    public List<PatMasterIndex> getPatientList(@Context HttpServletRequest request,PatMasterIndex patMasterIndex){
        List<PatMasterIndex> list = Lists.newArrayList();
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        try {
            patMasterIndex.setOrgId(loginInfo.getOrgId());
            list = patMasterIndexServiceApi.findList(patMasterIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @param         patMasterIndex    传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: validIfInHosp
     * @Description: (查询该病人是否为在院病人)
     * @author CTQ
     * @date 2016-7-25 14:46:40
     */
    @Path("validateIdCard")
    @GET
    public StringData validateIdCard(@Context HttpServletRequest request,PatMasterIndex patMasterIndex){
        StringData stringData=new StringData();
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        patMasterIndex.setOrgId(loginInfo.getOrgId());
        List<PatMasterIndex> patMasterIndexes = patMasterIndexServiceApi.searchByIdCard(patMasterIndex);
        if(patMasterIndexes!=null&&patMasterIndexes.size()>0){
        }else{
            stringData.setData("1");//非医院病人，请进行登记
        }
        return stringData;
    }

    /**
     * @param         id    传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: validIfInHosp
     * @Description: (查询该病人是否为在院病人)
     * @author CTQ
     * @date 2016-7-25 14:46:40
     */
    @Path("validIfInHosp")
    @GET
    public StringData validIfInHosp(String id){
        StringData stringData=new StringData();
       /* if(patMasterIndex.getId()!=null&&!"".equals(patMasterIndex.getId())){*/
            PatsInHospital patsInHospital = patsInHospitalServiceApi.findByPatientId(id);
            if(patsInHospital!=null){
                stringData.setData("1");//该病人已在院，不能进行入院登记
            }else{
                stringData.setData("2");//非在院病人，可进行登记
            }
        /*}*/
        return stringData;
    }

    /**
     * @param         patMasterIndex    传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: save
     * @Description: (保存病人信息及关联数据信息)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("save")
    @POST
    public StringData save(@Context HttpServletRequest request,PatMasterIndex patMasterIndex){
        StringData stringData=new StringData();
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        try {
            String data = patMasterIndexServiceApi.saveMasterIndex(patMasterIndex,loginInfo);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }
    /**
     * @param       ids      传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: delete
     * @Description: (取消登记-删除)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("delete")
    @POST
    public StringData delete(String ids){
        StringData stringData=new StringData();
        String num = "";
        //判断是否缴纳预交金，如果没有，执行取消登记
        List<PrepaymentRcpt> list = Lists.newArrayList();
        if(ids!=null&&!"".equals(ids)){
            //判断选中病人是否是已入院病人
            PatsInHospital patsInHospital = patsInHospitalServiceApi.findByPatientId(ids);
            if(patsInHospital!=null){
                list =  prepaymentRcptServiceApi.findByPatientId(ids);
                if(list!=null&&list.size()>0){
                    stringData.setData("warning");
                }else{
                    num=patMasterIndexServiceApi.deleteMasterIndex(ids);
                    stringData.setCode(num);
                    stringData.setData(num.compareTo("0")>0?"success":"error");
                }
            }else {
                stringData.setData("warn");
            }

        }
        return stringData;
    }

}
