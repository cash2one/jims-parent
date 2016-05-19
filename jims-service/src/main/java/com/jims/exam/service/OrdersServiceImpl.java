/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.exam.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.ExamAppointsDao;
import com.jims.clinic.dao.ExamItemsDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.api.OrdersServiceApi;
import com.jims.exam.dao.OrdersDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import com.jims.exam.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * 住院医嘱Service
 * @author zhangpeng
 * @version 2016-05-09
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OrdersServiceImpl extends CrudImplService<OrdersDao, Orders> implements OrdersServiceApi{

    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OrdersDao ordersDao;


    @Override
    public String saveOrders(ExamAppoints examAppoints) {
        int num=0;
        examAppoints.setCnsltState(1);
        examAppoints.preInsert();
        examAppoints.setPatientId(((int)(Math.random() * 1000))+"");
        examAppoints.setVisitId(((int)(Math.random() * 1000))+"");
        examAppoints.setPatientLocalId("1");
        examAppoints.setChargeType("1");
        num=examAppointsDao.insert(examAppoints);
        //设置就诊序号
        examAppoints.setVisitNo((int) Math.random() * 1000);
        List<ExamItems> examItemsList=examAppoints.getExamItemsList();
        for (int i = 0; i <examItemsList.size() ; i++) {
            ExamItems examItems=examItemsList.get(i);
            examItems.setAppointsId(examAppoints.getId());
            examItems.preInsert();
            examItems.setPatientId(examAppoints.getPatientId());
            examItems.setVisitId(examAppoints.getVisitId());

            examItemsDao.saveExamItems(examItems);
            Orders orders=new Orders();
            orders.preInsert();
            orders.setPatientId(examAppoints.getPatientId());
            orders.setVisitId(examAppoints.getVisitId());
            orders.setAppNo(examItems.getId());
//            if(ordersDao.getOrderNo(orders.getPatientId(),orders.getVisitId())!=0){
//                orders.setOrderNo(ordersDao.getOrderNo(orders.getPatientId(),orders.getVisitId())+1);
                orders.setOrderNo(1);
                orders.setOrderSubNo(2);
                orders.setOrderClass("D");
                orders.setOrderText(examItems.getExamItem());
                orders.setStartDateTime(examAppoints.getUpdateDate());
                orders.setRepeatIndicator("1"); // 长期医嘱标志
                orders.setOrderClass("1");//医嘱类型
                orders.setOrderText(examItems.getExamItem());
                orders.setOrderCode(examItems.getExamItemCode());
                ordersDao.insert(orders);
//            }else {
//                orders.setOrderNo(1);
//                orders.setStartDateTime(examAppoints.getReqDateTime());
//                orders.setRepeatIndicator("1"); // 长期医嘱标志
//                orders.setOrderClass("1");//医嘱类型
//                orders.setOrderText(examItems.getExamItem());
//                ordersDao.insert(orders);
//            }
        }

        return num+"";
    }

    @Override
    public String deleteOrders(String ids) {
        int num =0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                examItemsDao.deleteItems(id[j]);
                ExamAppoints examAppoints=examAppointsDao.get(id[j]);
                String visitId=examAppoints.getVisitId();
                num = examAppointsDao.deleteExamAppionts(id[j]);
            }
        }catch(Exception e){
            return num+"";
        }
        return num+"";

    }
    /**
     * 构建最新OrderNo
     * @parampatient_id
     * @paramvisit_Id
     * @author xueyx
     * @version 2016/5/12
     */
    public Long creeatOrderNo(Orders orders) {
        String maxnoStr =findMaxOrderNo(orders);
        Long maxno=Long.valueOf(1);;
        if(maxnoStr!=null){
            maxno = Long.valueOf(Integer.valueOf(maxnoStr) + 1);
        }
        return maxno;
    }
    /**
     * 最大OrderNo
     * @param
     * @parampatient_id
     * @paramvisit_Id
     * @author xueyx
     * @version 2016/5/12
     */
    //@Override
    public String findMaxOrderNo(Orders orders){
//        dao.findMaxOrderNo(orders);
        return "";
    }

    /**
     * 查询病人的医嘱
     * @param orders
     * @return
     */
    public List<Orders> getPatientOrders(Orders orders){
      return   ordersDao.getPatientOrders(orders);
    }

}