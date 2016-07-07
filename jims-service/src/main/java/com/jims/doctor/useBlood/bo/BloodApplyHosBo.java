package com.jims.doctor.useBlood.bo;

import com.jims.doctor.useBlood.dao.BloodApplylDao;
import com.jims.doctor.useBlood.dao.BloodCapacityDao;
import com.jims.blood.entity.BloodApply;
import com.jims.blood.entity.BloodCapacity;
import com.jims.common.utils.IdGen;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 * 住院用血申请BO
 */
@Service
@Transactional(readOnly = false)
public class BloodApplyHosBo {
    @Autowired
    private BloodApplylDao bloodApplylDao;
    @Autowired
    private BloodCapacityDao bloodCapacityDao;
    @Autowired
    private OrdersDao ordersDao;

    public String saveHosBloodApply(BloodApply bloodApply) {
        int strState = 0;
        if (bloodApply.getIsNewRecord()) {
            bloodApply.preInsert();
            bloodApply.setApplyNum(IdGen.uuid());
            strState = bloodApplylDao.insert(bloodApply);
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                if (bloodCapacity.getIsNewRecord()) {
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.insert(bloodCapacity);
                } else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.update(bloodCapacity);
                }
                Orders orders = new Orders();
                orders.setPatientId(bloodApply.getPatientId());
                orders.setVisitId(bloodApply.getVisitId());
                Integer orderNo = ordersDao.getOrderNo(bloodApply.getPatientId(), bloodApply.getVisitId(), "");
                if (orderNo != null) {
                    orders.setOrderNo(orderNo + 1);
                    orders.setOrderSubNo(orderNo + 1);
                } else {
                    orders.setOrderNo(1);
                    orders.setOrderSubNo(1);
                }
                orders.setStartDateTime(bloodApply.getApplyDate());
                orders.setOrderClass("C");
                orders.setOrderText(bloodCapacity.getBloodType());
                orders.setOrderCode(bloodCapacity.getBloodType());
                orders.setRepeatIndicator("0"); // 临时医嘱标志
                orders.setOrderStatus("6");//医嘱状态
                orders.setFreqDetail("1");//执行时间详细描述
                orders.setPerformSchedule(newDate());
                orders.setOrderingDept(bloodApply.getDeptCode());
                orders.setDoctor(bloodApply.getDoctor());
                //todo(userid)申请医生 ?
//                orders.setDoctorUser(Long.valueOf(1));
                //doctor_user:11=['000LJS']
                orders.setEnterDateTime(bloodApply.getApplyDate());
                //billing_attr:13=[3]
                //drug_billing_attr:14=[3]
                orders.setAppNo(bloodApply.getId());
                orders.preInsert();
                ordersDao.insert(orders);
            }

        } else {
            bloodApply.preUpdate();
            strState = bloodApplylDao.update(bloodApply);
            bloodCapacityDao.delBloodCapacity(bloodApply.getApplyNum());
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                if (bloodCapacity.getIsNewRecord()) {
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.insert(bloodCapacity);
                } else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.update(bloodCapacity);
                }
            }
        }
        return strState + "";
    }

    public String newDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }
}
