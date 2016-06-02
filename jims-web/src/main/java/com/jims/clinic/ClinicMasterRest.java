package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.StringUtils;
import com.jims.finance.outpAccounts.entity.RegistAcctDetail;
import com.jims.finance.outpAccounts.entity.RegistAcctMoney;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
    /**
     * @param          date   传递参数
     * @return com.jims.clinic.entity.ClinicMaster    返回类型
     * @throws
     * @Title: northFrom
     * @Description: (回填截至日期与结账序号)
     * @author CTQ
     * @date 2016/6/1
     */
    @Path("northFrom")
    @GET
    public ClinicMaster northFrom(@QueryParam("date") String date){

        ClinicMaster clinicMaster = new ClinicMaster();
        clinicMaster.setAcctDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        clinicMaster.setAcctNo("1");
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
    @POST
    public ClinicMaster registerFeeForm(String date){
        ClinicMaster clinicMaster = new ClinicMaster();
        clinicMaster = clinicMasterServiceApi.findFeeForm("1", !"".equals(date)?date:DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return clinicMaster;
    }
    /**
     * @param       date      传递参数
     * @return java.util.List<com.jims.clinic.entity.ClinicMaster>    返回类型
     * @throws
     * @Title: feeItem
     * @Description: (查询会计科目信息)
     * @author CTQ
     * @date 2016/6/1
     */
    @Path("feeItemList")
    @GET
    public List<RegistAcctDetail> feeItemList(@QueryParam("date") String date){

        List<RegistAcctDetail> list = Lists.newArrayList();
        ClinicMaster clinic = clinicMasterServiceApi.getCheckItem("1", !"".equals(date) ? date : DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        RegistAcctDetail master = new RegistAcctDetail();
        if(clinic!=null){
            if (clinic.getRegistFee()!=null && clinic.getRegistFee()>0){
                master.setTallyFeeClass("挂号费用");
                master.setIncome(clinic.getRegistFee());
                master.setCosts(clinic.getRegistFee());
                list.add(master);
            }
            if (clinic.getClinicFee()!=null&&clinic.getClinicFee()>0){
                master = new RegistAcctDetail();
                master.setTallyFeeClass("治疗费用");
                master.setIncome(clinic.getClinicFee());
                master.setCosts(clinic.getClinicFee());
                list.add(master);
            }
            if (clinic.getOtherFee()!=null && clinic.getOtherFee()>0){
                master = new RegistAcctDetail();
                master.setTallyFeeClass("其他费用");
                master.setIncome(clinic.getOtherFee());
                master.setCosts(clinic.getOtherFee());
                list.add(master);
            }
        }

        return list;
    }
    /**
     * @param       date      传递参数
     * @return java.util.List<com.jims.clinic.entity.ClinicMaster>    返回类型
     * @throws
     * @Title: payWayList
     * @Description: (根据支付方式分组查询数据)
     * @author CTQ
     * @date 2016/6/1
     */
    @Path("payWayList")
    @GET
    public List<RegistAcctMoney> payWayList(@QueryParam("date") String date){

        List<RegistAcctMoney> moneyList = Lists.newArrayList();
        List<ClinicMaster> list = Lists.newArrayList();
        list = clinicMasterServiceApi.getGroupData("1", !"".equals(date)?date:DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:mm:ss"));
        RegistAcctMoney money = new RegistAcctMoney();
        if(list!=null&&list.size()>0){
            for (ClinicMaster mo : list){
                money = new RegistAcctMoney();
                money.setIncomeAmount(mo.getClinicCharge());
                money.setRefundedAmount(mo.getRefundAmount());
                money.setTotalFee(mo.getTotalCosts());
                money.setMoneyType(mo.getPayWay());
            }
            moneyList.add(money);
        }
        return moneyList;
    }
}
