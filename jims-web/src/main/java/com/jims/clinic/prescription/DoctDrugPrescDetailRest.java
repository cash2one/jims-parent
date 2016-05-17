package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.DoctDrugPrescDetailServiceApi;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.clinic.entity.DoctDrugPrescMaster;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 待发药住院处方记录明细Rest
 * @author CTQ
 * @date 2016年5月16日15:23:08
 */
@Component
@Produces("application/json")
@Path("doctDrugPrescDetail")
public class DoctDrugPrescDetailRest {

    @Reference(version = "1.0.0")
    DoctDrugPrescDetailServiceApi doctDrugPrescDetailServiceApi;

    /**
     * @param     prescMasterId        传递参数
     * @return java.util.List<com.jims.clinic.entity.DoctDrugPrescDetail>    返回类型
     * @throws
     * @Title: list
     * @Description: (根据处方ID查询记录明细)
     * @author CTQ
     * @date 2016/5/16
     */
    @Path("list")
    @GET
    public List<DoctDrugPrescDetail> list(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("prescMasterId") String prescMasterId){
        List<DoctDrugPrescDetail> list = Lists.newArrayList();
        try {
            list = doctDrugPrescDetailServiceApi.findListByPrescMasterId(prescMasterId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
