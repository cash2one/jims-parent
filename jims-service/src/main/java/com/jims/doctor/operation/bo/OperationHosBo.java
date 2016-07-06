package com.jims.doctor.operation.bo;

import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.doctor.operation.dao.OperationScheduleDao;
import com.jims.doctor.operation.dao.ScheduledOperationNameDao;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 住院手术申请bo
 */
@Service
@Transactional(readOnly = false)
public class OperationHosBo {
    @Autowired
    private PatsInHospitalDao patsInHospitalDao;
    @Autowired
    private ScheduledOperationNameDao scheduledOperationNameDao;
    @Autowired
    private OperationScheduleDao operationScheduleDao;
    @Autowired
    private OrdersDao ordersDao;

    public String saveOperationIn(OperationSchedule operationSchedule){
        if(operationSchedule!=null) {
            if (operationSchedule.getIsNewRecord()) {
                String scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId());
                int sId=Integer.parseInt(scheduleId)+1;
                operationSchedule.setScheduleId(sId);
                operationSchedule.preInsert();
                operationSchedule.setAckIndicator(0);
                operationSchedule.setDoctorUser("当前医生");
                operationScheduleDao.insert(operationSchedule);
                if (operationSchedule.getScheduledOperationNameList() != null) {
                    List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();
                    for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                        ScheduledOperationName scheduledOperationName = new ScheduledOperationName();
                        scheduledOperationName = scheduledOperationNameList.get(i);
                        if (scheduledOperationName.getIsNewRecord()) {
                            scheduledOperationName.setOperationNo(i+1);
                            scheduledOperationName.preInsert();
                            scheduledOperationName.setScheduleId(operationSchedule.getId());
                            scheduledOperationNameDao.insert(scheduledOperationName);
                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);
                        }
                        Orders orders = new Orders();
                        orders.setPatientId(operationSchedule.getPatientId());
                        orders.setVisitId(operationSchedule.getVisitId());
                        Integer orderNo = ordersDao.getOrderNo(operationSchedule.getPatientId(), operationSchedule.getVisitId(), "");
                        if (orderNo != null) {
                            orders.setOrderNo(orderNo + 1);
                            orders.setOrderSubNo(orderNo + 1);
                        } else {
                            orders.setOrderNo(1);
                            orders.setOrderSubNo(1);
                        }
                        orders.setStartDateTime(operationSchedule.getReqDateTime());
                        orders.setOrderClass("C");
                        orders.setOrderText(scheduledOperationName.getOperation());
                        orders.setOrderCode(scheduledOperationName.getOperation());
                        orders.setRepeatIndicator("0"); // 临时医嘱标志
                        orders.setOrderStatus("6");//医嘱状态
                        orders.setFreqDetail("1");//执行时间详细描述
                        orders.setPerformSchedule(newDate());
                        orders.setOrderingDept(operationSchedule.getDeptStayed());
                        orders.setDoctor(operationSchedule.getDoctorUser());
                        //todo(userid)申请医生 ?
//                orders.setDoctorUser(Long.valueOf(1));
                        //doctor_user:11=['000LJS']
//                        orders.setEnterDateTime(bloodApply.getApplyDate());
                        //billing_attr:13=[3]
                        //drug_billing_attr:14=[3]
                        orders.setAppNo(operationSchedule.getId());
                        orders.preInsert();
                        ordersDao.insert(orders);
                    }
                }


            } else {
                operationSchedule.preUpdate();
                operationSchedule.setAckIndicator(0);
                operationSchedule.setDoctorUser("当前医生");
                operationScheduleDao.update(operationSchedule);
                if (operationSchedule.getScheduledOperationNameList() != null) {
                    List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();
                    for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                        ScheduledOperationName scheduledOperationName = new ScheduledOperationName();
                        scheduledOperationName = scheduledOperationNameList.get(i);
                        if (scheduledOperationName.getIsNewRecord()) {
                            scheduledOperationName.setOperationNo(i+1);
                            scheduledOperationName.preInsert();

                            scheduledOperationName.setScheduleId(operationSchedule.getId());
                            scheduledOperationNameDao.insert(scheduledOperationName);

                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);

                        }
                        Orders orders = new Orders();
                        orders.setPatientId(operationSchedule.getPatientId());
                        orders.setVisitId(operationSchedule.getVisitId());
                        Integer orderNo = ordersDao.getOrderNo(operationSchedule.getPatientId(), operationSchedule.getVisitId(), "");
                        if (orderNo != null) {
                            orders.setOrderNo(orderNo + 1);
                            orders.setOrderSubNo(orderNo + 1);
                        } else {
                            orders.setOrderNo(1);
                            orders.setOrderSubNo(1);
                        }
                        orders.setStartDateTime(operationSchedule.getReqDateTime());
                        orders.setOrderClass("C");
                        orders.setOrderText(scheduledOperationName.getOperation());
                        orders.setOrderCode(scheduledOperationName.getOperation());
                        orders.setRepeatIndicator("0"); // 临时医嘱标志
                        orders.setOrderStatus("6");//医嘱状态
                        orders.setFreqDetail("1");//执行时间详细描述
                        orders.setPerformSchedule(newDate());
                        orders.setOrderingDept(operationSchedule.getDeptStayed());
                        orders.setDoctor(operationSchedule.getDoctorUser());
                        //todo(userid)申请医生 ?
//                orders.setDoctorUser(Long.valueOf(1));
                        //doctor_user:11=['000LJS']
//                        orders.setEnterDateTime(bloodApply.getApplyDate());
                        //billing_attr:13=[3]
                        //drug_billing_attr:14=[3]
                        orders.setAppNo(operationSchedule.getId());
                        orders.preInsert();
                        ordersDao.insert(orders);
                    }
                }
            }

            return "1";


        }else{
            return "0";
        }
    }
    /**
     * 通过科室Code拿到医生所负责的病人
     * @param deptCode
     * @return
     */
    public List<PatsInHospital> getOperationin(String deptCode){
        return   patsInHospitalDao.getOperationin(deptCode);
    }

    /**
     * 找到病人本次住院最大的ScheduleId
     * @param patientId
     * @param visitId
     * @return
     */
    public String getScheduleId(String patientId,String visitId){
        String   scheduleId =operationScheduleDao.getScheduleId(patientId, visitId);
        if(scheduleId==null){
            scheduleId="0";
        }
        return scheduleId;
    }

    public String newDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }
}
