package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.data.StringData;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.utils.StringUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.finance.outpAccounts.entity.RegistAcctDetail;
import com.jims.finance.outpAccounts.entity.RegistAcctMoney;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.entity.DeptDict;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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

    @Reference(version = "1.0.0")
    private DeptDictApi deptDictApi;

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
     * 获取病人信息
     * @param id
     * @return
     * @author zhaoning
     */
      @Path("getPatInfo")
      @GET
    public ClinicMaster getPatInfo(@QueryParam("id") String id){
        ClinicMaster clinicMaster=  clinicMasterServiceApi.getPatInfo(id);
        return clinicMaster;
    }
    /**
     * 根据当前登录医生的 医生ID 查询 ClinicMaster(待诊)
     * @return
     */
    @Path("clinicMasterList")
    @GET
    public List<ClinicMaster> getClinicList(@QueryParam("deptId")String deptId,@Context HttpServletRequest request){
        LoginInfo loginInfo =LoginInfoUtils.getPersionInfo(request);
        loginInfo.setDeptId(deptId);
        DeptDict deptDict= deptDictApi.get(deptId);
        loginInfo.setDeptCode(deptDict.getDeptCode());
        loginInfo.setDeptName(deptDict.getDeptName());
        HttpSession session = request.getSession();
        //将科室信息存入缓存
        Cache cache = new Cache(session.getId(), loginInfo, 7200, true);
        CacheManager.putCache(session.getId(), cache);

        String  orgId=loginInfo.getOrgId();//当前登录医生所在的机构Id
        List<ClinicMaster> list= clinicMasterServiceApi.getClinicMasterList(deptId,orgId);
       return list;
    }

    /**
     * 根据当前登录医生ID 查询 病人列表（已诊）
     * @return
     */
    @Path("clinicMasterDiagnosed")
    @GET
    public List<ClinicMaster> getClinicMasterDiagnosed(@QueryParam("deptId")String deptId,@Context HttpServletRequest request){
        LoginInfo loginInfo =LoginInfoUtils.getPersionInfo(request);
        loginInfo.setDeptId(deptId);
        DeptDict deptDict= deptDictApi.get(deptId);
        loginInfo.setDeptCode(deptDict.getDeptCode());
        loginInfo.setDeptName(deptDict.getDeptName());
        HttpSession session = request.getSession();
        //将科室信息存入缓存
        Cache cache = new Cache(session.getId(), loginInfo, 7200, true);
        CacheManager.putCache(session.getId(), cache);
        String  orgId= LoginInfoUtils.getPersionInfo(request).getOrgId();//当前登录医生所在的机构Id
        List<ClinicMaster> list= clinicMasterServiceApi.getClinicMasterDiagnosed(deptId,orgId);//测试中----医生ID给的定值
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
        clinicMaster.setAcctNo(clinicMasterServiceApi.getMaxAcctNo());
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
    public ClinicMaster registerFeeForm(@Context HttpServletRequest request,String date){
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        ClinicMaster clinicMaster = new ClinicMaster();
        clinicMaster = clinicMasterServiceApi.findFeeForm(loginInfo.getPersionId(), !"".equals(date)?date:DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
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
    public List<RegistAcctDetail> feeItemList(@Context HttpServletRequest request,@QueryParam("date") String date){
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        List<RegistAcctDetail> list = Lists.newArrayList();
        ClinicMaster clinic = clinicMasterServiceApi.getCheckItem(loginInfo.getPersionId(), !"".equals(date) ? date : DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        RegistAcctDetail master = new RegistAcctDetail();
        if(clinic!=null){
            if (clinic.getRegistFee()!=null && clinic.getRegistFee()>0){
                master.setTallyFeeClass("挂号费用");
                master.setIncome(clinic.getRegistFee());
                master.setCosts(clinic.getRegistFee());
                master.preInsert();
                list.add(master);
            }
            if (clinic.getClinicFee()!=null&&clinic.getClinicFee()>0){
                master = new RegistAcctDetail();
                master.setTallyFeeClass("治疗费用");
                master.setIncome(clinic.getClinicFee());
                master.setCosts(clinic.getClinicFee());
                master.preInsert();
                list.add(master);
            }
            if (clinic.getOtherFee()!=null && clinic.getOtherFee()>0){
                master = new RegistAcctDetail();
                master.setTallyFeeClass("其他费用");
                master.setIncome(clinic.getOtherFee());
                master.setCosts(clinic.getOtherFee());
                master.preInsert();
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
    public List<RegistAcctMoney> payWayList(@Context HttpServletRequest request,@QueryParam("date") String date){
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        List<RegistAcctMoney> moneyList = Lists.newArrayList();
        List<ClinicMaster> list = Lists.newArrayList();
        list = clinicMasterServiceApi.getGroupData(loginInfo.getPersionId(), !"".equals(date)?date:DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:mm:ss"));
        RegistAcctMoney money = new RegistAcctMoney();
        if(list!=null&&list.size()>0){
            for (ClinicMaster mo : list){
                money = new RegistAcctMoney();
                money.preInsert();
                money.setIncomeAmount(mo.getClinicCharge());
                money.setRefundedAmount(mo.getRefundAmount());
                money.setTotalFee(mo.getTotalCosts());
                money.setMoneyType(mo.getPayWay());
            }
            moneyList.add(money);
        }
        return moneyList;
    }

    /**
     * 保存 病人信息
     * @param clinicMaster
     * @return
     */
    @POST
    @Path("savePatInfo")
    public StringData savePatInfo(ClinicMaster clinicMaster){
       StringData data = new StringData();
       data.setCode(clinicMasterServiceApi.updatePatInfo(clinicMaster));
        return data;
    }
}
