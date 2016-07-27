package com.jims.doctor.operation.bo;

import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.vo.LoginInfo;
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

    public String saveOperationIn(OperationSchedule operationSchedule,LoginInfo loginInfo) {
        int  num=0;
        if (operationSchedule.getIsNewRecord()) {
            int scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId(), "");
            int sId = scheduleId + 1;
            operationSchedule.setScheduleId(sId);
            operationSchedule.preInsert();
            operationSchedule.setAckIndicator(0);
            operationSchedule.setEnteredBy(loginInfo.getPersionId());
            operationSchedule.setDoctorUser(loginInfo.getUserName());
            operationSchedule.setOperatingDept(loginInfo.getDeptCode());
            operationSchedule.setOrgId(loginInfo.getOrgId());

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
            orders.setOrderClass("C");
            orders.setAppNo(operationSchedule.getId());
            orders.setOrgId(operationSchedule.getOrgId());
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");//医嘱状态
            orders.setFreqDetail("1");//执行时间详细描述
            orders.setPerformSchedule(newDate());
            orders.setOrderingDept(operationSchedule.getDeptStayed());
            orders.setDoctor(operationSchedule.getDoctorUser());//开医嘱医生
            orders.setStartDateTime(operationSchedule.getReqDateTime());
            orders.setOrderingDept(operationSchedule.getOperatingDept());//开医嘱科室
            orders.setEnterDateTime(operationSchedule.getReqDateTime());//开医嘱录入日期及时间
            orders.setAppNo(operationSchedule.getId());
            List<ScheduledOperationName> scheduledOperationNameList = operationSchedule.getScheduledOperationNameList();
            for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                ScheduledOperationName scheduledOperationName = new ScheduledOperationName();
                scheduledOperationName = scheduledOperationNameList.get(i);
                if (scheduledOperationName.getIsNewRecord()) {
                    scheduledOperationName.setOperationNo(i + 1);
                    scheduledOperationName.preInsert();
                    scheduledOperationName.setScheduleId(operationSchedule.getId());
                    scheduledOperationNameDao.insert(scheduledOperationName);
                    orders.setOrderText(scheduledOperationName.getOperation());
                    orders.setOrderCode(scheduledOperationName.getOperation());
                    orders.preInsert();
                    ordersDao.insert(orders);
                } else {
                    scheduledOperationName.preUpdate();
                    scheduledOperationNameDao.update(scheduledOperationName);
                    orders.setOrderText(scheduledOperationName.getOperation());
                    orders.setOrderCode(scheduledOperationName.getOperation());
                    orders.preUpdate();
                    ordersDao.update(orders);
                }
            }
            num = operationScheduleDao.insert(operationSchedule);
        }else {
            int scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId(), "");
            int sId = scheduleId + 1;
            operationSchedule.setScheduleId(sId);
            operationSchedule.preUpdate();
            operationSchedule.setAckIndicator(0);

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
            orders.setOrderClass("C");
            orders.setAppNo(operationSchedule.getId());
            orders.setOrgId(operationSchedule.getOrgId());
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");//医嘱状态
            orders.setFreqDetail("1");//执行时间详细描述
            orders.setPerformSchedule(newDate());
            orders.setOrderingDept(operationSchedule.getDeptStayed());
            orders.setDoctor(operationSchedule.getDoctorUser());//开医嘱医生
            orders.setStartDateTime(operationSchedule.getReqDateTime());
            orders.setOrderingDept(operationSchedule.getOperatingDept());//开医嘱科室
            orders.setEnterDateTime(operationSchedule.getReqDateTime());//开医嘱录入日期及时间
            orders.setAppNo(operationSchedule.getId());
            List<ScheduledOperationName> scheduledOperationNameList = operationSchedule.getScheduledOperationNameList();
            for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                ScheduledOperationName scheduledOperationName = new ScheduledOperationName();
                scheduledOperationName = scheduledOperationNameList.get(i);
                if (scheduledOperationName.getIsNewRecord()) {
                    scheduledOperationName.setOperationNo(i + 1);
                    scheduledOperationName.preInsert();
                    scheduledOperationName.setScheduleId(operationSchedule.getId());
                    scheduledOperationNameDao.insert(scheduledOperationName);
                    orders.setOrderText(scheduledOperationName.getOperation());
                    orders.setOrderCode(scheduledOperationName.getOperation());
                    orders.preInsert();
                    ordersDao.insert(orders);
                } else {
                    scheduledOperationName.preUpdate();
                    scheduledOperationNameDao.update(scheduledOperationName);
                    orders.setOrderText(scheduledOperationName.getOperation());
                    orders.setOrderCode(scheduledOperationName.getOperation());
                    orders.preUpdate();
                    ordersDao.update(orders);
                }
            }
            num = operationScheduleDao.update(operationSchedule);
        }
        return num+"";
    }

    /**
     * 通过科室Code拿到医生所负责的病人
     *
     * @param deptCode
     * @return
     */
    public List<PatsInHospital> getOperationin(String deptCode) {
        return patsInHospitalDao.getOperationin(deptCode);
    }

    /**
     * 找到病人本次住院最大的ScheduleId
     *
     * @param patientId
     * @param visitId
     * @return
     */
    public Integer getScheduleId(String patientId, String visitId, String clinicId) {
        Integer scheduleId = operationScheduleDao.getScheduleId(patientId, visitId, "");
        if (scheduleId == null) {
            scheduleId = 0;
        }
        return scheduleId;
    }

    /**
     * 通过clinicId拿到手术安排
     *
     * @param patientId
     * @param visitId
     * @return
     */
    public List<OperationSchedule> getSchedule(String patientId, String visitId) {
        List<OperationSchedule> operationScheduleList = operationScheduleDao.getScheduleList(patientId, visitId,"");
        return operationScheduleList;
    }

    public String newDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }

    /**
     * 删除住院手术
     * @param ids
     * @return
     */
    public String deleteHos(String ids){
        int num = 0;
        String[] id= ids.split(",");
        try {
            for(int i=0;i<id.length;i++){
                num = operationScheduleDao.deleteOperation(id[i]);
                scheduledOperationNameDao.deleteSchedule(id[i]);
                ordersDao.delOrders(id[i],"");
            }
        }catch (Exception e){
            return "0";
        }
        return num+"";
        }
}
