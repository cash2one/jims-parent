package com.jims.doctor.lab.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import com.jims.doctor.lab.dao.LabTestItemsDao;
import com.jims.doctor.lab.dao.LabTestMasterDao;
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

    /**
     * 住院保存
     * 整个主表、字表list
     *
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */

    public String saveAllIn(LabTestMaster labTestMaster) {
        int num;
        //本次住院标识对门诊病人为空
        //patientId病人标识号页面有公共值
        //labTestMaster.setPatientId("");
        //结果状态
        labTestMaster.setResultStatus("0");
        //申请序号
        labTestMaster.setTestNo(creatTestNo());
        labTestMaster.setBillingIndicator(0);//计价标志
        labTestMaster.setPrintIndicator(0);///打印标志
        labTestMaster.setRequestedDateTime(new Date());
        num = labTestMasterDao.insert(labTestMaster);
        List<LabTestItems> list = labTestMaster.getList();
        for (int i = 0; i < list.size(); i++) {
            LabTestItems labTestItems = list.get(i);
                /*检验项目*/
            labTestItems.setItemNo(i + 1);
            labTestItems.setTestNo(labTestMaster.getTestNo());
            labTestItems.preInsert();
            labTestItems.setLabMaster(labTestMaster.getId());
            labTestItemsDao.insert(labTestItems);
            //Orders
            Orders orders = new Orders();
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
            orders.setAppNo(labTestMaster.getId());
            orders.setOrgId(labTestMaster.getOrgId());
            orders.setDoctor(labTestMaster.getOrderingProvider());//开医嘱医生
            orders.setOrderingDept(labTestMaster.getOrderingDept());//开医嘱科室
            orders.setEnterDateTime(labTestMaster.getRequestedDateTime());//开医嘱录入日期及时间
            orders.preInsert();
            ordersDao.insert(orders);
        }
        return num + "";
    }

    public String deleteLabTestMasterHos(String ids) {
        int num = 0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                LabTestMaster labTestMaster = labTestMasterDao.get(id[j]);
                num = labTestMasterDao.deleteLabTestMaster(id[j]);
                labTestItemsDao.deleteItmes(id[j]);
                String visitId = labTestMaster.getVisitId();
                ordersDao.delOrders(visitId);
            }
        } catch (Exception e) {
            return num + "";
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
