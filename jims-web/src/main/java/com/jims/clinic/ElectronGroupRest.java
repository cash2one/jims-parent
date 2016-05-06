package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ElectronGroupConsultationApi;
import com.jims.clinic.api.ElectronGroupConsultationIntoApi;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("group")
public class ElectronGroupRest {

    @Reference(version = "1.0.0")
    private ElectronGroupConsultationApi electronGroupConsultationApi ;

    @Reference(version = "1.0.0")
    private ElectronGroupConsultationIntoApi electronGroupConsultationIntoApi ;

    /**
     ** 保存或编辑
     * @param -ElectronGroupConsultation
     * @return
     */

    @Path("save")
    @POST
    public StringData save(ElectronGroupConsultation electronGroupConsultation){
        StringData data=new StringData();
        String num=data.getCode();
        if(electronGroupConsultation!=null){
            electronGroupConsultationApi.saveGroupConsulation(electronGroupConsultation);
        }
        data.setCode(num);
        data.setData("success");
        return data;
    }

    /**
     ** 保存或编辑
     * @param -ElectronGroupConsultation
     * @return
     */

    @Path("savemainonly")
    @POST
    public StringData saveMainOnly(ElectronGroupConsultation electronGroupConsultation){
        StringData data=new StringData();
        String num=data.getCode();
        electronGroupConsultationApi.saveMainOnly(electronGroupConsultation);
        data.setCode(num);
        data.setData("success");
        return data;
    }

    /**
     ** 保存或编辑
     * @param -ElectronGroupConsultation
     * @return
     */

    @Path("saveotheridea")
    @POST
    public StringData saveOtherIdea(ElectronGroupConsultationIn electronGroupConsultationIn){
        StringData data=new StringData();
        String num=data.getCode();
        electronGroupConsultationIntoApi.saveOtherIdea(electronGroupConsultationIn);
        data.setCode(num);
        data.setData("success");
        return data;
    }

    /**
     * 异步加载会诊信息表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<ElectronGroupConsultation> page = electronGroupConsultationApi.findPage(new Page<ElectronGroupConsultation>(request,response), new ElectronGroupConsultation());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 异步参与医生信息列表
     * @param-request
     * @param-response
     * @return
     */
    @Path("doctorlist")
    @POST
    public List<ElectronGroupConsultationIn> doctorList(String consulaionId){
        if(StringUtils.isBlank(consulaionId)){
            return null;
        }else{
            ElectronGroupConsultation entity = new ElectronGroupConsultation();
            int index = consulaionId.indexOf("=");
            entity.setId(consulaionId.substring(index+1));
            List<ElectronGroupConsultationIn> list=electronGroupConsultationIntoApi.getListByMain(entity);
            return list;
        }
    }
    /**
     * 异步参与医生信息列表
     * 查看所有的医生和意见（包括发布人）
     * @param-request
     * @param-response
     * @return
     */
    @Path("alldoctorlist")
    @POST
    public List<ElectronGroupConsultationIn> allDoctorList(String consulaionId){
        if(StringUtils.isBlank(consulaionId)){
            return null;
        }else{
            ElectronGroupConsultation entity = new ElectronGroupConsultation();
            int index = consulaionId.indexOf("=");
            entity.setId(consulaionId.substring(index+1));
            List<ElectronGroupConsultationIn> list=electronGroupConsultationIntoApi.getListByMain(entity);
            ElectronGroupConsultation electronGroupConsultation = electronGroupConsultationApi.get(entity.getId());
            if(electronGroupConsultation!=null){
                ElectronGroupConsultationIn creater =  new ElectronGroupConsultationIn();
                creater.setDoctorId(electronGroupConsultation.getDoctorId());
                creater.setInHuizhenyijian(electronGroupConsultation.getHuizhenyijian());
                list.add(creater);
            }
            return list;
        }
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=electronGroupConsultationApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     *
     * @param ids
     * @return
     */
    @Path("indel")
    @POST
    public StringData indel(String ids){
        String num=electronGroupConsultationIntoApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 获取单条数据
     * 根据ID查询记录
     * @param-id
     * @return
     */
    @Path("get")
    @POST
    public ElectronGroupConsultation get(String id){
        ElectronGroupConsultation entity=electronGroupConsultationApi.get(id);
        return entity;
    }

    /**
     * 获取参与会诊表单条数据
     * 根据ID查询记录
     * @param-id
     * @return
     */
    @Path("getgroupin")
    @POST
    public ElectronGroupConsultationIn getGroupInto(ElectronGroupConsultationIn electronGroupConsultationIn){
        //ElectronGroupConsultationIn electronGroupConsultationIn = new ElectronGroupConsultationIn();
        ElectronGroupConsultationIn entity=electronGroupConsultationIntoApi.getByMainIdAndDoctorId(electronGroupConsultationIn);
        return entity;
    }

    /**
     * 发布会诊主表方法
     * @param "ElectronGroupConsultation.id 会诊主表id"
     * @author xueyx
     * @version 2016-04-26
     */
    @Path("fabu")
    @POST
    public StringData fabu(String id){
        ElectronGroupConsultation entity = new ElectronGroupConsultation();
        entity.setId(id);
        electronGroupConsultationApi.fabu(entity);
        StringData stringData=new StringData();
        stringData.setData("success");
        return stringData;
    }
}
