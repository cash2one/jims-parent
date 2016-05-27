package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.DrugPrescServiceApi;
import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.clinic.entity.DrugPrescMaster;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 药品处方
 *
 * @author PangQian
 * @date2016/5/26 0026
 */
@Component
@Produces("application/json")
@Path("drugPresc")
public class DrugPrescRest {
    @Reference(version = "1.0.0")
    private DrugPrescServiceApi drugPrescServiceApi;

    /**
     * 查询主表列表
     * @param request
     * @param response
     * @param drugPrescMaster
     * @return
     */
    @Path("findMaster")
    @GET
    public PageData findMaster(@Context HttpServletRequest request,@Context HttpServletResponse response,@QueryParam(value = "drugPrescMaster")DrugPrescMaster drugPrescMaster){
        Page<DrugPrescMaster> prescMasterPage=new Page<DrugPrescMaster>(request,response);
        prescMasterPage=drugPrescServiceApi.findPage(prescMasterPage,new DrugPrescMaster());
        PageData pageData=new PageData();
        pageData.setRows(prescMasterPage.getList());
        pageData.setTotal(prescMasterPage.getCount());
        return pageData;
    }

    /**
     * 查询药品处方明细记录
     * @param masterId
     * @return
     */
    @Path("getDetail")
    @GET
    public List<DrugPrescDetail> getDetail(@QueryParam(value = "masterId")String masterId){
      return   drugPrescServiceApi.findDrugDetail(masterId);
    }
}
