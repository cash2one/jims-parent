package com.jims.doctor.useBlood.bo;

import com.jims.clinic.dao.PatVisitDao;
import com.jims.common.vo.LoginInfo;
import com.jims.doctor.useBlood.dao.BloodApplylDao;
import com.jims.doctor.useBlood.dao.BloodCapacityDao;
import com.jims.blood.entity.BloodApply;
import com.jims.blood.entity.BloodCapacity;
import com.jims.common.utils.IdGen;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import com.jims.patient.entity.PatVisit;
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
    @Autowired
    private PatVisitDao patVisitDao;

    public String saveHosBloodApply(BloodApply bloodApply,LoginInfo loginInfo) {
        int strState = 0;
        PatVisit patVisit = patVisitDao.selectPatVisit(bloodApply.getVisitId(),bloodApply.getPatientId());
        if (bloodApply.getIsNewRecord()) {
            bloodApply.preInsert();
//            String applyNum = "IYX"+patVisit.getVisitNo()+(int)(Math.random()*9000);
//            bloodApply.setApplyNum(applyNum);
            bloodApply.setOrgId(loginInfo.getOrgId());
            bloodApply.setPhysician(loginInfo.getPersionId());//开单医生id
            bloodApply.setDeptCode(loginInfo.getDeptId());//病人科室
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
//                orders.setOrderClass("C");
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");//医嘱状态
            orders.setFreqDetail("1");//执行时间详细描述
            orders.setPerformSchedule(newDate());
            orders.setOrderingDept(bloodApply.getDeptCode());//开医嘱科室
            orders.setDoctor(bloodApply.getPhysician());//开医嘱医生
            orders.setEnterDateTime(bloodApply.getApplyDate());//开医嘱时间
            orders.setOrgId(loginInfo.getOrgId());//orgId
            orders.setAppNo(bloodApply.getId());
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                if (bloodCapacity.getIsNewRecord()) {
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodCapacity.getApplyNum());
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
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodCapacity.getApplyNum());
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
            orders.setOrgId(loginInfo.getOrgId());//orgId
            bloodCapacityDao.delBloodCapacity(bloodApply.getApplyNum());
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                if (bloodCapacity.getIsNewRecord()) {
                    bloodCapacity.preInsert();
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodCapacity.getApplyNum());
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
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodCapacity.getApplyNum());
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
            String[] id =ids.split(",");
            for(int i=0;i<id.length;i++){
                bloodCapacityDao.deleteBloodCapacity(id[i]);
                ordersDao.delOrders(id[i],"");
                num = bloodApplylDao.deleteBloodApply(id[i]);
            }
        return num+"";
    }
}
