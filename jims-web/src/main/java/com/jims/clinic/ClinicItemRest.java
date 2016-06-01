package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ClinicItemApi;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by lgx
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("clinicItem")
public class ClinicItemRest {

    @Reference(version = "1.0.0")
    private ClinicItemApi clinicItemApi ;

    /**
     * 检索诊疗项目
     * @param clinicItemDict
     * @return
     */
    @POST
    @Path("findList")
    public List<ClinicItemDict> findList(ClinicItemDict clinicItemDict){
        return clinicItemApi.findList(clinicItemDict);
    }

    /**
     * 检索诊疗项目编码名称是否已存在
     * @param clinicItemDict
     * @return
     */
    @POST
    @Path("codeOrNameHas")
    public String codeOrNameHas(ClinicItemDict clinicItemDict){
        return clinicItemApi.codeOrNameHas(clinicItemDict) ? "1" : "0";
    }

    /**
     * 检索诊疗项目别名
     * @param clinicItemDict
     * @return
     */
    @POST
    @Path("findNameList")
    public List<ClinicItemNameDict> findNameList(ClinicItemDict clinicItemDict){
        return clinicItemApi.findNameList(clinicItemDict);
    };

    /**
     * 检索诊疗项目对照
     * @param clinicItemDict
     * @return
     */
    @POST
    @Path("findVsList")
    public List<ClinicVsCharge> findVsList(ClinicItemDict clinicItemDict){
        return clinicItemApi.findVsList(clinicItemDict);
    }

    /**
     * 保存诊疗项目
     * @param list
     * @return
     */
    @POST
    @Path("saveItem")
    public String saveItem(List<ClinicItemDict> list){
        return clinicItemApi.save(list);
    }

    /**
     * 保存别名
     * @param list
     * @return
     */
    @POST
    @Path("saveName")
    public String saveName(List<ClinicItemNameDict> list){
        return clinicItemApi.saveNameList(list);
    }

    /**
     * 保存诊疗项目对照
     * @param list
     * @return
     */
    @POST
    @Path("saveVs")
    public String saveVs(List<ClinicVsCharge> list){
        return clinicItemApi.saveVsList(list);
    }

    /**
     * 删除诊疗项目
     * @param ids
     * @return
     */
    @POST
    @Path("deleteItem")
    public String deleteItem(@QueryParam("ids") String ids){
        return clinicItemApi.deleteCascade(ids);
    }

    /**
     * 删除诊疗项目别名
     * @param ids
     * @return
     */
    @POST
    @Path("deleteName")
    public String deleteName(@QueryParam("ids") String ids){
        return clinicItemApi.delete(ids);
    }

    /**
     * 删除诊疗项目对照
     * @param ids
     * @return
     */
    @POST
    @Path("deleteVs")
    public String deleteVs(@QueryParam("ids") String ids){
        return clinicItemApi.delete(ids);
    }

    /**
     * 查询诊疗项目
     * @param id
     * @return
     */
    @GET
    @Path("get")
    public ClinicItemDict get( @QueryParam("id")String id){
        return clinicItemApi.get(id);
    }

    /**
     * 保存诊疗项目、别名、对照
     * @param list
     * @return
     */
    @POST
    @Path("save")
    public String save(List<ClinicItemDict> list){
        return clinicItemApi.saveBatch(list);
    }

    /**
     * 根据诊疗项目类别 和组织机构获取本组织机构的所有诊疗项目
     * @param orgId
     * @param clinicClass
     * @return
     */
    @Path("list-by-class")
    @GET
    public List<ClinicItemDict> listClinicItemDictByOrgIdAndClass(@QueryParam("orgId")String orgId,@QueryParam("clinicClass")String clinicClass){
        return clinicItemApi.findListByOrgIdAndClinicClass(orgId,clinicClass) ;
    }



}
