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
        examAppoints.setPatientId("2222");
        examAppoints.setVisitId(1);
        examAppoints.setVisitNo(22);
        examAppoints.setPatientLocalId("1");
        examAppoints.setChargeType("1");

        //设置就诊序号
        examAppoints.setVisitNo((int) Math.random() * 1000);
        List<ExamItems> examItemsList=examAppoints.getExamItemsList();
        for (int i = 0; i <examItemsList.size() ; i++) {
            ExamItems examItems=examItemsList.get(i);
            examItems.setAppointsId(examAppoints.getId());
            examItems.preInsert();
            examItemsDao.saveExamItems(examItems);
            Orders orders=new Orders();
            orders.preInsert();
            orders.setPatientId(examAppoints.getPatientId());
            orders.setVisitId((long) examAppoints.getVisitId());
            orders.setAppNo(examItems.getId());
            orders.setOrderNo((long)123456);
            orders.setOrderClass("1");
            orders.setOrderClass("D");
            orders.setOrderText(examItems.getExamItem());
            orders.setOrderCode(examItems.getExamItemCode());
            num=ordersDao.insert(orders);
        }
        num=examAppointsDao.insert(examAppoints);
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
                String clinicId=examAppoints.getClinicId();
                num = examAppointsDao.deleteExamAppionts(id[j]);
            }
        }catch(Exception e){
            return num+"";
        }
        return num+"";

    }


}