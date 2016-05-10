package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.api.OutpOrdersServiceApi;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpPresc;
import com.jims.clinic.vo.OutpPrescListVo;
import com.jims.common.data.StringData;
import com.jims.sys.entity.Dict;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
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


    /**
     //     * @param             传递参数
     * @return java.util.List<com.jims.clinic.entity.OutpPresc>    返回类型
     * @throws
     * @Title: list
     * @Description: (根据病人ID查询处方主记录)
     * @author CTQ
     * @date 2016/4/25
     */
    @Path("list")
    @GET
    public List<OutpPresc> list(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("clinicId") String clinicId){
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.getOutpPresc(clinicId);
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
    public List<OutpPrescListVo> sublist(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("prescNo") Integer prescNo){
        OutpPresc op = new OutpPresc();
        op.setPrescNo(prescNo);
        List<OutpPrescListVo> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.findListByParams(op);
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



}
