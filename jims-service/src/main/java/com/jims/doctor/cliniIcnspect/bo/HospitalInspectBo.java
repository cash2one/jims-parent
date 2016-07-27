package com.jims.doctor.cliniIcnspect.bo;

import com.jims.common.vo.LoginInfo;
import com.jims.doctor.cliniIcnspect.dao.ExamAppointsDao;
import com.jims.doctor.cliniIcnspect.dao.ExamItemsDao;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.common.service.impl.CrudImplService;

import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;

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
 * Created by Administrator on 2016/7/1.
 * 住院检查申请BO
 */
@Service
@Transactional(readOnly = false)
public class HospitalInspectBo extends CrudImplService<ExamAppointsDao, ExamAppoints> {
    @Autowired
    private ExamAppointsDao examAppointsDao ;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private PatVisitDao patVisitDao;

    /**
     * 保存住院检查记录
     * @param examAppoints
     * @return
     */
    public int saveHospitalInspect(ExamAppoints examAppoints,LoginInfo loginInfo) {
        int num = 0;
        PatVisit patVisit = patVisitDao.selectPatVisit(examAppoints.getVisitId(),examAppoints.getPatientId());
        examAppoints.setCnsltState(0);
        examAppoints.preInsert();
        examAppoints.setInOrOut("1");//住院标示
        examAppoints.setRegPrnFlag(0);//确认标示
        examAppoints.setChargeType(patVisit.getChargeType());
        examAppoints.setVisitNo(patVisit.getVisitNo());
        //申请序号
//        String examNo="JC"+patVisit.getVisitNo()+(int)(Math.random()*9000);
//        examAppoints.setExamNo(examNo);
        examAppoints.setReqPhysician(loginInfo.getPersionId());
        examAppoints.setReqDept(loginInfo.getDeptId());
        examAppoints.setDoctorUser(loginInfo.getUserName());
        examAppoints.setReqDateTime(new Date());
        examAppoints.setOrgId(loginInfo.getOrgId());
        num = examAppointsDao.insert(examAppoints);
        List<ExamItems> examItemsList = examAppoints.getExamItemsList();
        for (int i = 0; i < examItemsList.size(); i++) {
            ExamItems examItems = examItemsList.get(i);
            examItems.setAppointsId(examAppoints.getId());
            examItems.preInsert();
            examItems.setPatientId(examAppoints.getPatientId());
            examItems.setVisitId(examAppoints.getVisitId());
            examItems.setOrgId(examAppoints.getOrgId());
            examItems.setExamNo(examAppoints.getExamNo());
            examItemsDao.saveExamItems(examItems);
            Orders orders = new Orders();
            orders.preInsert();
            orders.setOrgId(examAppoints.getOrgId());
            orders.setPatientId(examAppoints.getPatientId());
            orders.setVisitId(examAppoints.getVisitId());
            orders.setAppNo(examAppoints.getId());
            orders.setDoctor(examAppoints.getReqPhysician());
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
            orders.setOrderingDept(examAppoints.getReqDept());//开医嘱科室
            orders.setEnterDateTime(examAppoints.getReqDateTime());//开医嘱录入日期及时间
            orders.setOrderText(examItems.getExamItem());
            orders.setOrderCode(examItems.getExamItemCode());
            ordersDao.insert(orders);
        }
        return num;
    }

    public String newDate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
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
    public String delectHosExamAppionts(String ids) {

        int num =0;
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                ordersDao.delOrders(id[j],"");
                examItemsDao.deleteItems(id[j]);
                num = examAppointsDao.deleteExamAppionts(id[j]);
            }
        return num+"";

    }
}
