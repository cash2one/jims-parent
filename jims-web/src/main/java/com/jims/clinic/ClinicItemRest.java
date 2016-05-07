package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ClinicItemApi;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.clinic.vo.ClinicItemPriceVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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

    @POST
    @Path("findList")
    public List<ClinicItemDict> findList(ClinicItemDict clinicItemDict){
        return clinicItemApi.findList(clinicItemDict);
    }

    @POST
    @Path("findNameList")
    public List<ClinicItemNameDict> findNameList(ClinicItemDict clinicItemDict){
        return clinicItemApi.findNameList(clinicItemDict);
    };

    @POST
    @Path("findVsList")
    public List<ClinicVsCharge> findVsList(ClinicItemDict clinicItemDict){
        return clinicItemApi.findVsList(clinicItemDict);
    }

    @POST
    @Path("saveItem")
    public String saveItem(List<ClinicItemDict> list){
        return clinicItemApi.save(list);
    }

    @POST
    @Path("saveName")
    public String saveName(List<ClinicItemNameDict> list){
        return clinicItemApi.saveNameList(list);
    }

    @POST
    @Path("saveVs")
    public String saveVs(List<ClinicVsCharge> list){
        return clinicItemApi.saveVsList(list);
    }

    @POST
    @Path("deleteItem")
    public String deleteItem(@QueryParam("ids") String ids){
        return clinicItemApi.deleteCascade(ids);
    }

    @POST
    @Path("deleteName")
    public String deleteName(@QueryParam("ids") String ids){
        return clinicItemApi.delete(ids);
    }

    @POST
    @Path("deleteVs")
    public String deleteVs(@QueryParam("ids") String ids){
        return clinicItemApi.delete(ids);
    }

    @GET
    @Path("get")
    public ClinicItemDict get( @QueryParam("id")String id){
        return clinicItemApi.get(id);
    }

    @POST
    @Path("save")
    public String save(List<ClinicItemDict> list){
        int delResult = 0;
        int saveResult = 0;
        int saveNameResult = 0;
        int saveVsResult = 0;
        int delNameResult = 0;
        int delVsResult = 0;
        try {
            for (int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
                ClinicItemDict itemObj = list.get(i);
                if ("1".equals(itemObj.getDelFlag())) {
                    delResult += Integer.valueOf(clinicItemApi.deleteCascade(itemObj.getId()));
                } else if ("1".equals(itemObj.getUpdateFlag())) {
                    saveNameResult += Integer.valueOf(clinicItemApi.saveNameList(itemObj.getSaveNameList()));
                    saveVsResult += Integer.valueOf(clinicItemApi.saveVsList(itemObj.getSaveVsList()));
                    delNameResult += Integer.valueOf(clinicItemApi.deleteName(itemObj.getDelNameIds()));
                    delVsResult += Integer.valueOf(clinicItemApi.deleteVs(itemObj.getDelVsIds()));
                } else {
                    String result = clinicItemApi.save(itemObj);
                    saveResult += Integer.valueOf(result);
                    if (itemObj.getId() == null) {
                        if ("1".equals(result)) {
                            saveNameResult += Integer.valueOf(clinicItemApi.saveNameList(itemObj.getSaveNameList()));
                            saveVsResult += Integer.valueOf(clinicItemApi.saveVsList(itemObj.getSaveVsList()));
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        String resultMsg = "{\"success\":\"0\",\"msg\":\"" + "成功保存诊疗项目" + saveResult + "条，别名" + saveNameResult + "条，对照" + saveVsResult
                + "条。成功删除诊疗项目" + delResult + "条以及关联别名和对照，其他别名" + delNameResult + "条，其他对照" + delVsResult + "条。\"}";
        return resultMsg;
    }

    /**
     * 获取检查项目价格列表
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("itemListByOrgId")
    public List<ClinicItemPriceVo> itemListByOrgId(@Context HttpServletRequest request, @Context HttpServletResponse response){
        return clinicItemApi.itemListByOrgId(request.getParameter("orgId"));
    };
}
