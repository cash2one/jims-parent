package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by CTQ
 * 处方医嘱明细记录
 */
@Component
@Produces("application/json")
@Path("outppresc")
public class OutpPrescRest {

    @Reference(version = "1.0.0")
    OutpPrescServiceApi outpPrescServiceApi;
    @Reference(version = "1.0.0")
    ClinicMasterServiceApi clinicMasterServiceApi;
    @Reference(version = "1.0.0")
    OutpOrdersCostsServiceApi outpOrdersCostsServiceApi;


    /**
     * @param       orgId   clinicId   传递参数
     * @return java.util.List<com.jims.clinic.entity.OutpPresc>    返回类型
     * @throws
     * @Title: list
     * @Description: (根据病人ID查询处方主记录)
     * @author CTQ
     * @date 2016/4/25
     */
    @Path("list")
    @GET
    public List<OutpPresc> list(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("orgId") String orgId,@QueryParam("clinicId") String clinicId){
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.getOutpPresc(orgId,clinicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @return java.util.List<com.jims.clinic.entity.OutpPresc>    返回类型
     * @throws
     * @Title: list
     * @Description: (查询患者处方用药列表数据)
     * @author CTQ
     * @date 2016/4/23
     */
    @Path("sublist")
    @GET
    public List<OutpPresc> sublist(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("orgId") String orgId,@QueryParam("clinicId") String clinicId,@QueryParam("prescNo") Integer prescNo){
        OutpPresc op = new OutpPresc();
        op.setPrescNo(prescNo);
        op.setOrgId(orgId);
        op.setClinicId(clinicId);
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.findListByParams(op);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    @Path("subherballist")
    @POST
    public List<OutpPresc> subherballist(OutpPresc outpPresc){
/*        OutpPresc op = new OutpPresc();
        op.setPrescNo(prescNo);
//        op.setOrgId(orgId);
        op.setClinicId(clinicId);*/
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.findListByParams(outpPresc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @param          outpPresc   传递参数
     * @return com.jims.common.data.StringData    返回类型
     * @throws
     * @Title: save
     * @Description: (保存处方用药信息)
     * @author CTQ
     * @date 15:18
     */
    @Path("save")
    @POST
    public StringData save(OutpPresc outpPresc){
        StringData stringData=new StringData();
       try {
           String data = outpPrescServiceApi.save(outpPresc);
           stringData.setCode(data);
           stringData.setData(data.compareTo("0") > 0 ? "success":"error");
       }catch (Exception e){
           e.printStackTrace();
       }
        return stringData;
    }

    /**
     * @param ids 传递参数
     * @return StringData    返回类型
    * @throws
     * @Title: delete
     * @Desription: (处方删除药品,同时删除医嘱及计价项目)
     * @author CTQ
     * @date 2016年4月23日15:11:52
     */
    @Path("delete")
    @POST
    public StringData delete(String ids){
        StringData stringData=new StringData();
        String num=outpPrescServiceApi.deletePresc(ids);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * @param       id      传递参数
     * @return com.jims.clinic.entity.ClinicMaster    返回类型
     * @throws
     * @Title: getClinicMaster
     * @Description: (点击新方，获取当前病人的信息)
     * @author CTQ
     * @date 2016/5/12
     */
    @Path("getClinicMaster")
    @POST
    public ClinicMaster getClinicMaster(String id) {
        ClinicMaster clinicMaster = clinicMasterServiceApi.get(id);
        return clinicMaster;
    }


    @Path("dictlist")
    @GET
    public List<OutpPresc> dictlist(){
        List<OutpPresc> list = Lists.newArrayList();
        OutpPresc dict = new OutpPresc();
        dict.setDrugCode("1");
        dict.setDrugName("阿莫西林");
        dict.setDrugSpec("10g*2");
        dict.setFirmId("YS000023");
        dict.setDosage(Double.valueOf(1));
        dict.setDosageUnits("片");
        dict.setItemClass("A");
        list.add(dict);
        return list;
    }

    @Path("priceItem")
    @GET
    public List<OutpOrdersCosts> priceItem(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("orderNo") String orderNo,@QueryParam("clinicId") String clinicId){
        List<OutpOrdersCosts> list = null;
        try {
            list = Lists.newArrayList();
            list = outpOrdersCostsServiceApi.getOutpCosts(orderNo,clinicId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
