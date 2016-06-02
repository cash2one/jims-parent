package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.entity.ClinicMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 * 门诊病人
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicMaster")
public class ClinicMasterRest {
    @Reference(version = "1.0.0")
    private ClinicMasterServiceApi clinicMasterServiceApi;




    /**
     *获取就诊记录
     * @return
     */
    @Path("get")
    @GET
    public ClinicMaster getClinicMasterList(@QueryParam("id") String id){
        ClinicMaster clinicMaster=  clinicMasterServiceApi.get(id);
        return clinicMaster;
    }
    /**
     * 根据当前登录医生的 医生ID 查询 ClinicMaster(待诊)
     * @return
     */
    @Path("clinicMasterList")
    @GET
    public List<ClinicMaster> getClinicMasterList(){
        List<ClinicMaster> list= new ArrayList<ClinicMaster>();
      /* User user =  UserUtils.getUser();
       if(user!=null && user.getId()!=null){
           list=clinicMasterServiceApi.getClinicMasterList(user.getId());
       }*/
        list= clinicMasterServiceApi.getClinicMasterList("174");
       return list;
    }

    /**
     * 根据当前登录医生ID 查询 病人列表（已诊）
     * @return
     */
    @Path("clinicMasterDiagnosed")
    @GET
    public List<ClinicMaster> getClinicMasterDiagnosed(){

        List<ClinicMaster> list= new ArrayList<ClinicMaster>();

       /* User user=  UserUtils.getUser(); //获取当前登录人
        if(user!=null && user.getId()!=null){
            list=clinicMasterServiceApi.getClinicMasterDiagnosed(user.getId());
        }*/
        list= clinicMasterServiceApi.getClinicMasterDiagnosed("174");//测试中----医生ID给的定值
        return list;
    }
    @Path("northFrom")
    @GET
    public ClinicMaster northFrom(){
        ClinicMaster clinicMaster = new ClinicMaster();
        clinicMaster.setRegisteringDate(new Date());
        clinicMaster.setAcctNo("");
        return clinicMaster;
    }
    /**
     * @return com.jims.clinic.entity.ClinicMaster    返回类型
     * @throws
     * @Title: findFeeForm
     * @Description: (查询当前操作员的挂号.退号及费用信息)
     * @author CTQ
     * @date 2016/5/31
     */
    @Path("registerFeeFrom")
    @GET
    public ClinicMaster registerFeeForm(){
        ClinicMaster clinicMaster = new ClinicMaster();
        clinicMaster = clinicMasterServiceApi.findFeeForm("1",new Date());
        return clinicMaster;
    }
    @Path("feeItemList")
    @GET
    public List<ClinicMaster> feeItem(){
        List<ClinicMaster> list = Lists.newArrayList();
        return list;
    }
    @Path("payWayList")
    @GET
    public List<ClinicMaster> registerFeeFrom(){
        List<ClinicMaster> list = Lists.newArrayList();
        return list;
    }
}
