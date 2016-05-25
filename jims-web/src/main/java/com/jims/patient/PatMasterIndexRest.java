package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.common.data.StringData;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
    /**
     * @param        patientId     传递参数
     * @return java.util.List<com.jims.patient.entity.PatMasterIndex>    返回类型
     * @throws
     * @Title: getPatientList
     * @Description: (根据条件查询病人列表)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("list")
    @GET
    public List<PatMasterIndex> getPatientList(@QueryParam(value = "patientId")String patientId){
        PatMasterIndex patMasterIndex = new PatMasterIndex();
        List<PatMasterIndex> list = Lists.newArrayList();
        try {
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
     * @Title: save
     * @Description: (保存病人信息及关联数据信息)
     * @author CTQ
     * @date 2016/5/25
     */
    @Path("save")
    @POST
    public StringData save(PatMasterIndex patMasterIndex){
        StringData stringData=new StringData();
        try {
            String data = patMasterIndexServiceApi.saveMasterIndex(patMasterIndex);
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
        String num=patMasterIndexServiceApi.deleteMasterIndex(ids);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
