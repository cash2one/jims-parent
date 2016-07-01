package com.jims.exam.bo;

import com.jims.clinic.dao.ExamAppointsDao;
import com.jims.clinic.dao.ExamItemsDao;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.dao.OrdersDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import com.jims.exam.entity.Orders;
import com.jims.patient.entity.PatVisit;
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
public class HospitalInspectBo extends CrudImplService<ExamAppointsDao, ExamAppoints> {
    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private PatVisitDao patVisitDao;

    public int saveHospitalInspect(ExamAppoints examAppoints) {
        int num = 0;
        PatVisit patVisit = patVisitDao.selectPatVisit(examAppoints.getVisitId());
        examAppoints.setCnsltState(0);
        examAppoints.preInsert();
        examAppoints.setChargeType(patVisit.getChargeType());
        num = examAppointsDao.insert(examAppoints);
        //设置就诊序号
        examAppoints.setVisitNo((int) Math.random() * 1000);
        List<ExamItems> examItemsList = examAppoints.getExamItemsList();
        for (int i = 0; i < examItemsList.size(); i++) {
            ExamItems examItems = examItemsList.get(i);
            examItems.setAppointsId(examAppoints.getId());
            examItems.preInsert();
            examItems.setPatientId(examAppoints.getPatientId());
            examItems.setVisitId(examAppoints.getVisitId());
            examItemsDao.saveExamItems(examItems);
            Orders orders = new Orders();
            orders.preInsert();
            orders.setPatientId(examAppoints.getPatientId());
            orders.setVisitId(examAppoints.getVisitId());
            orders.setAppNo(examItems.getId());
            Integer orderNo = ordersDao.getOrderNo(examAppoints.getPatientId(),examAppoints.getVisitId(),"");
            if(orderNo !=null){
                orders.setOrderNo(orderNo+1);
                orders.setOrderSubNo(orderNo+1);
            }else {
                orders.setOrderNo(1);
                orders.setOrderSubNo(1);
            }
            orders.setOrderClass("D");
            orders.setStartDateTime(examAppoints.getUpdateDate());
            orders.setRepeatIndicator("0"); // 临时医嘱标志
            orders.setOrderStatus("6");
            orders.setFreqDetail("1");
            orders.setPerformSchedule(newDate());
            orders.setOrderText(examItems.getExamItem());
            orders.setOrderCode(examItems.getExamItemCode());
            ordersDao.insert(orders);
        }
        return num;
    }

    public String newDate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("MM-dd");
        Date date=new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }

    /**
     * 删除预约记录
     *
     * @param ids
     * @return
     */
    public String deleteExamAppionts(String ids) {
        int num =0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                examItemsDao.deleteItems(id[j]);
                ExamAppoints examAppoints=examAppointsDao.get(id[j]);
                String visitId=examAppoints.getVisitId();
                ordersDao.deleteOrders(visitId);
                num = examAppointsDao.deleteExamAppionts(id[j]);

            }
        }catch(Exception e){
            return num+"";
        }
        return num+"";

    }
}
