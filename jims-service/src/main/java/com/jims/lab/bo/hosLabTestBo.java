package com.jims.lab.bo;

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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
@Service
@Transactional(readOnly = false)
public class HosLabTestBo extends CrudImplService<LabTestMasterDao,LabTestMaster> {

    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private LabTestMasterDao labTestMasterDao;

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
        labTestMasterDao.insert(labTestMaster);
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
                Integer orderNo = ordersDao.getOrderNo(labTestMaster.getPatientId(),labTestMaster.getVisitId(),"");
                if(orderNo !=null){
                    orders.setOrderNo(orderNo+1);
                    orders.setOrderSubNo(orderNo+1);
                }else {
                    orders.setOrderNo(1);
                    orders.setOrderSubNo(1);
                }
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

    public String newDate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("MM-dd");
        Date date=new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }

}
