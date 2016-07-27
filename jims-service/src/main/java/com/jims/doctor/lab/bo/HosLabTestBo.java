package com.jims.doctor.lab.bo;

import com.jims.clinic.dao.PatVisitDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.vo.LoginInfo;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import com.jims.doctor.lab.dao.LabTestItemsDao;
import com.jims.doctor.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import com.jims.patient.entity.PatVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 * 住院检验申请BO
 */
@Service
@Transactional(readOnly = false)
public class HosLabTestBo extends CrudImplService<LabTestMasterDao, LabTestMaster> {

    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private LabTestMasterDao labTestMasterDao;
    @Autowired
    private PatVisitDao patVisitDao;

    /**
     * 住院保存
     * 整个主表、字表list
     *
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */

    public String saveAllIn(LabTestMaster labTestMaster,LoginInfo loginInfo) {
        int num;
        PatVisit patVisit = patVisitDao.selectPatVisit(labTestMaster.getVisitId(),labTestMaster.getPatientId());
        //申请序号
        labTestMaster.setTestNo(creatTestNo());
        labTestMaster.setBillingIndicator(0);//计价标志
        labTestMaster.setPrintIndicator(0);///打印标志
        labTestMaster.setOrgId(loginInfo.getOrgId());//orgId
        //申请序号
//        String textNo="JY"+patVisit.getVisitNo()+(int)(Math.random()*9000);
//        labTestMaster.setTestNo(textNo);
        labTestMaster.setOrderingDept(loginInfo.getDeptId());//申请科室
        labTestMaster.setOrderingProvider(loginInfo.getPersionId());//送检医生
        labTestMaster.setRequestedDateTime(new Date());//申请时间
        labTestMaster.setStatus(labTestMaster.LAB_STATUS_APPLY);//申请状态
        labTestMaster.setResultStatus(labTestMaster.LAB_RESULTSTATUS_APPLY);//结果状态
        labTestMaster.setInOrOutFlag(labTestMaster.LAB_STATUS_APPLY);//住院标志

        labTestMaster.preInsert();
        num = labTestMasterDao.insert(labTestMaster);
        List<LabTestItems> list = labTestMaster.getList();
        for (int i = 0; i < list.size(); i++) {
            LabTestItems labTestItems = list.get(i);
                /*检验项目*/
            labTestItems.setItemNo(i + 1);
            labTestItems.setBillingIndicator(labTestItems.LAB_BILLINGINDICATOR_APPLY);
            labTestItems.setLabMaster(labTestMaster.getId());
            labTestItems.setTestNo(labTestMaster.getTestNo());
            labTestItems.setOrgId(labTestMaster.getOrgId());
            labTestItems.preInsert();
            labTestItemsDao.insert(labTestItems);
            //Orders
            Orders orders = new Orders();
            orders.preInsert();
            orders.setAppNo(labTestMaster.getId());
            orders.setOrgId(labTestMaster.getOrgId());
            orders.setPatientId(labTestMaster.getPatientId());
            orders.setVisitId(labTestMaster.getVisitId().toString());
            Integer orderNo = ordersDao.getOrderNo(labTestMaster.getPatientId(), labTestMaster.getVisitId(), "");
            if (orderNo != null) {
                orders.setOrderNo(orderNo + 1);
                orders.setOrderSubNo(orderNo + 1);
            } else {
                orders.setOrderNo(1);
                orders.setOrderSubNo(1);
            }
            orders.setStartDateTime(labTestMaster.getRequestedDateTime());
            orders.setOrderClass("C");
            orders.setOrderText(labTestItems.getItemName());
            orders.setOrderCode(labTestItems.getItemCode());
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");//医嘱状态
            orders.setFreqDetail("1");//执行时间详细描述
            orders.setPerformSchedule(newDate());
            orders.setDoctor(labTestMaster.getOrderingProvider());//开医嘱医生
            orders.setOrderingDept(labTestMaster.getOrderingDept());//开医嘱科室
            orders.setEnterDateTime(labTestMaster.getRequestedDateTime());//开医嘱录入日期及时间

            ordersDao.insert(orders);
        }
        return num + "";
    }

    public String deleteLabTestMasterHos(String ids) {
        int num = 0;
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                labTestItemsDao.deleteItmes(id[j]);
                ordersDao.delOrders(id[j],"");
                num = labTestMasterDao.deleteLabTestMaster(id[j]);
            }
        return num + "";

    }


    /**
     * 生成申请序号
     *
     * @param主表 当前日期
     * @author xueyx
     * @version 2016/5/09
     */
    public String creatTestNo() {
        String no = dao.creatTestNo();
        Date dt = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddS");
        String d1 = format.format(dt);
        String result = "";
        if (no == null) {
            return "000000";
        }else {
            return d1;
        }
//            if (d1.equals(no.substring(0, 6))) {
//                int temp = Integer.valueOf(no.substring(6));
//                temp = temp + 1;
//                result = String.format("%4d", temp).replace(" ", "0");
//                if (result.length() > 4) {
//                    result = d1.concat("0000");
//                } else {
//                    result = d1.concat(result);
//                }
//            } else {
//                result = d1.concat("0001");
//            }
//            return result;
//        }
//        return d1;
    }

    public String newDate() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }

}
