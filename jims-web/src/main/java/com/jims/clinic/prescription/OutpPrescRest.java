package com.jims.clinic.prescription;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.api.OutpOrdersServiceApi;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.data.StringData;
import com.jims.sys.entity.Dict;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    OutpOrdersServiceApi outpOrdersServiceApi;

    @Reference(version = "1.0.0")
    OutpOrdersCostsServiceApi outpOrdersCostsServiceApi;

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
    public List<OutpPresc> list(){
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.getOutpPresc("1");
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
    public List<OutpPresc> sublist(){
        OutpPresc op = new OutpPresc();
        List<OutpPresc> list = Lists.newArrayList();
        try {
            list = outpPrescServiceApi.findList(op);
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
           stringData.setData(data.compareTo("0")>0?"success":"error");
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
     * @Desription: (处方删除药品)
     * @author CTQ
     * @date 2016年4月23日15:11:52
     */
    @Path("delete")
    @POST
    public StringData delete(String ids){
        StringData stringData=new StringData();
      /*  String num=dictService.delete(ids);
        stringData.setCode(num);
        stringData.setData("success");*/
        return stringData;
    }
    @Path("dictlist")
    @GET
    public List<Dict> dictlist(){
        List<Dict> list = Lists.newArrayList();
        Dict dict = new Dict();
        dict.setValue("1");
        dict.setLabel("一日一次");
        list.add(dict);
        return list;
    }

}
