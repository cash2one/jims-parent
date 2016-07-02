package com.jims.clinic.bo;

import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import com.jims.lab.dao.LabTestItemsDao;
import com.jims.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
@Service
@Transactional(readOnly = false)
public class LabTestBo  extends CrudImplService<LabTestMasterDao,LabTestMaster> {


    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    @Autowired
    private LabTestMasterDao labTestMasterDao;

    /***
     * 问诊检验保存
     * @param labTestMaster
     * @return
     */
    public String saveAll(LabTestMaster labTestMaster){
        int  num;
        if(true){//labTestMaster!=null && labTestMaster.getId()!=null
            String a ="";
            //patientId病人标识号页面有公共值
            //labTestMaster.setPatientId("");
            labTestMaster.preInsert();
            //todo(userid)申请医生
            labTestMaster.setOrderingProvider("");
            //todo(clinicId)申请科室
            labTestMaster.setOrderingDept("");
            //结果状态
            labTestMaster.setResultStatus("1");
            labTestMaster.setDelFlag("0");
            //申请序号
            labTestMaster.setTestNo(creatTestNo());
            labTestMaster.setBillingIndicator(0);
            labTestMaster.setPrintIndicator(0);
            List<ClinicItemDict> clinicItemDictList=new ArrayList<ClinicItemDict>();
            List<LabTestItems> labTestItemsList = labTestMaster.getList();
            if(labTestItemsList.size()>0){
                for (int i= 0; i < labTestItemsList.size()-1;i++){
                    ClinicItemDict clinicItemDict=new ClinicItemDict();
                    LabTestItems labTestItems=labTestItemsList.get(i);
                    clinicItemDict.setItemCode(labTestItems.getItemCode());
                    clinicItemDict.setOrgId(labTestMaster.getOrgId());
                    labTestItems.setDelFlag("0");
                    labTestItems.setItemNo(i+1);
                    labTestItems.setTestNo(labTestMaster.getTestNo());
                    labTestItems.preInsert();
                    labTestItemsDao.insert(labTestItems);

                    OutpTreatRec outpTreatRec = new OutpTreatRec();/*门诊医嘱明细记录*/
                    outpTreatRec.preInsert();
                    outpTreatRec.setCosts(labTestItems.getPrice());
                    outpTreatRec.setCharges(labTestItems.getPrice());
//                    todo(clinicId) 就诊序号
//                    outpTreatRec.setVisitDate();
//                    todo(clinicId) 就诊序号
//                    outpTreatRec.setVisitNo(1);
//                    outpTreatRec.setSerialNo(outpOrders.getSerialNo());
//                    todo (?)
//                    outpTreatRec.setItemNo(1);
                    outpTreatRec.setItemClass("C");
                    outpTreatRec.setItemName(labTestItems.getItemName());
                    outpTreatRec.setItemCode(labTestItems.getItemCode());
                    outpTreatRec.setAmount(Double.valueOf(1));
                    outpTreatRec.setPerformedBy(labTestMaster.getPerformedBy());
                    outpTreatRec.setChargeIndicator(0);
                    outpTreatRec.setAppointNo(labTestMaster.getTestNo());
                    outpTreatRec.setAppointItemNo(labTestItems.getItemNo());
                    outpTreatRecDao.saveTreatRec(outpTreatRec);
                    clinicItemDictList.add(clinicItemDict);
                }
                costOrdersUtilsService.save(labTestMaster.getClinicId(),clinicItemDictList,labTestMaster.getId());
                num = labTestMasterDao.insert(labTestMaster);
                return  num+"";
            }
        }
        return "0";
    }

    /**
     * 住院保存
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */

    public String saveAllIn(LabTestMaster labTestMaster){
        //本次住院标识对门诊病人为空
        //patientId病人标识号页面有公共值
        //labTestMaster.setPatientId("");
        //todo(userid)申请医生
        labTestMaster.setOrderingProvider("");
        //todo(clinicId)申请科室
        labTestMaster.setOrderingDept("");
        //结果状态
        labTestMaster.setResultStatus("0");
        labTestMaster.setDelFlag("0");
        //申请序号
        labTestMaster.setTestNo(creatTestNo());
        labTestMaster.setBillingIndicator(0);
        labTestMaster.setPrintIndicator(0);
        save(labTestMaster);
        List<LabTestItems> list = labTestMaster.getList();
        if(list.size()>0){
            for (int i= 0; i < list.size()-1;i++){
                LabTestItems labTestItems=list.get(i);
                /*检验项目*/
                labTestItems.setDelFlag("0");
                labTestItems.setItemNo(i+1);
                labTestItems.setTestNo(labTestMaster.getTestNo());
                labTestItems.preInsert();
                labTestItemsDao.insert(labTestItems);
                //Orders
                Orders orders = new  Orders();
                orders.setPatientId(labTestMaster.getPatientId());
                orders.setVisitId(labTestMaster.getVisitId().toString());
                orders.setOrderNo(ordersDao.creeatOrderNo(orders));
                orders.setOrderSubNo(1);
                orders.setStartDateTime(labTestMaster.getRequestedDateTime());
                orders.setRepeatIndicator("0");
                orders.setOrderClass("C");
                orders.setOrderText(labTestItems.getItemName());
                orders.setOrderCode(labTestItems.getItemCode());
                orders.setOrderStatus("6");
                orders.setOrderingDept(labTestMaster.getOrderingDept());
                orders.setDoctor(labTestMaster.getOrderingProvider());
                //todo(userid)申请医生 ?
//                orders.setDoctorUser(Long.valueOf(1));
                //doctor_user:11=['000LJS']
                orders.setEnterDateTime(labTestMaster.getRequestedDateTime());
                //billing_attr:13=[3]
                //drug_billing_attr:14=[3]
                orders.setAppNo(labTestMaster.getTestNo());
                orders.setFreqDetail("");
                orders.preInsert();
                ordersDao.insert(orders);
            }
        }
        return "";
    }
    /**
     * 生成申请序号
     * @param主表 当前日期
     * @author xueyx
     * @version 2016/5/09
     */
    public String creatTestNo(){
        String no =dao.creatTestNo();
        Date dt=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String d1 =format.format(dt);
        String result="";
        if(no!=null){
            if(d1.equals(no.substring(0,6))){
                int temp = Integer.valueOf(no.substring(6));
                temp=temp+1;
                result = String.format("%4d", temp).replace(" ", "0");
                if(result.length()>4){
                    result = d1.concat("0000");
                }else{
                    result=d1.concat(result);
                }
            }else{
                result=d1.concat("0001");
            }
            return result;
        }
        return "000000";
    }

}
