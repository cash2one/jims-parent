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
            orders.setAppNo(bloodApply.getId());
//                orders.setOrderClass("C");
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");//医嘱状态
            orders.setFreqDetail("1");//执行时间详细描述
            orders.setPerformSchedule(newDate());
            orders.setOrderingDept(bloodApply.getDeptCode());//开医嘱科室
            orders.setDoctor(bloodApply.getPhysician());//开医嘱医生
            orders.setEnterDateTime(bloodApply.getApplyDate());//开医嘱时间

            //billing_attr:13=[3]
            //drug_billing_attr:14=[3]
            orders.setAppNo(bloodApply.getId());
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                if (bloodCapacity.getIsNewRecord()) {
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.insert(bloodCapacity);

                    orders.setOrderText(bloodCapacity.getBloodType());
                    orders.setOrderCode(bloodCapacity.getBloodType());
                    orders.preInsert();
                    ordersDao.insert(orders);
                } else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.update(bloodCapacity);

                    orders.setOrderText(bloodCapacity.getBloodType());
                    orders.setOrderCode(bloodCapacity.getBloodType());
                    orders.preUpdate();
                    ordersDao.update(orders);
                }
            }
            strState = bloodApplylDao.insert(bloodApply);

        } else {
            bloodApply.preUpdate();

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
            orders.setAppNo(bloodApply.getId());
//                orders.setOrderClass("C");
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");//医嘱状态
            orders.setFreqDetail("1");//执行时间详细描述
            orders.setPerformSchedule(newDate());
            orders.setOrderingDept(bloodApply.getDeptCode());//开医嘱科室
            orders.setDoctor(bloodApply.getPhysician());//开医嘱医生
            orders.setEnterDateTime(bloodApply.getApplyDate());//录入医嘱时间

            orders.setAppNo(bloodApply.getId());
            bloodCapacityDao.delBloodCapacity(bloodApply.getApplyNum());
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                if (bloodCapacity.getIsNewRecord()) {
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.insert(bloodCapacity);

                    orders.setOrderText(bloodCapacity.getBloodType());
                    orders.setOrderCode(bloodCapacity.getBloodType());
                    orders.preInsert();
                    ordersDao.insert(orders);
                } else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.update(bloodCapacity);

                    orders.setOrderText(bloodCapacity.getBloodType());
                    orders.setOrderCode(bloodCapacity.getBloodType());
                    orders.preUpdate();
                    ordersDao.update(orders);
                }
            }
            strState = bloodApplylDao.update(bloodApply);
        }
        return strState + "";
    }

    public String newDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }


    /**
     * 手术确认
     *
     * @param bloodApplies
     * @return
     * @author pq
     */
    public String confirmBlood(List<BloodApply> bloodApplies) {
        int num1 = 0;
        if (bloodApplies != null) {
            for (int i = 0; i < bloodApplies.size(); i++) {
                num1 = bloodApplylDao.confirmBlood(bloodApplies.get(i));
            }
        }
        return num1 + "";
    }

    /**
     * 删除住院用血记录
     * @param ids
     * @return
     */
    public String delHos(String ids){
        int num = 0;
        try {
            String[] id =ids.split(",");
            for(int i=0;i<id.length;i++){
                num = bloodApplylDao.deleteBloodApply(id[i]);
                bloodCapacityDao.deleteBloodCapacity(id[i]);
                ordersDao.delOrders(id[i]);
            }
        }catch (Exception e){
            return "0";
        }
        return num+"";
    }
}
