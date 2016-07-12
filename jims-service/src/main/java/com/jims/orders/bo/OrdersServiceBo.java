/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.orders.bo;

import com.jims.doctor.cliniIcnspect.dao.ExamAppointsDao;
import com.jims.doctor.cliniIcnspect.dao.ExamItemsDao;
import com.jims.common.persistence.Page;
import com.jims.orders.dao.OrdersCostsDao;
import com.jims.orders.entity.OrdersCosts;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 住院医嘱Bo
 * @author zhangyao
 * @version 2016-07-01
 */
@Service
@Transactional(readOnly = false)
public class OrdersServiceBo extends CrudImplService<OrdersDao, Orders>{

    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private OrdersCostsDao ordersCostsDao;


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
       dao.findMaxOrderNo(orders);
        return "";
    }

    /**
     * 查询病人的医嘱
     * @param orders
     * @return
     * pq
     */
    public List<Orders> getPatientOrders(Orders orders){
      return   ordersDao.getPatientOrders(orders);
    }

    /**
     * 保存医嘱
     * @param ordersList
     * @return
     * pq
     */
    public String saveOrdersNew(List<Orders> ordersList){
        int num = 0;
        try {
            if(ordersList!=null){
                for(int i=0;i<ordersList.size();i++){
                Orders orders=ordersList.get(i);
                    if (orders.getIsNewRecord()) {
                        orders.preInsert();
                        orders.setOrderStatus("5");//医生保存
                       if(orders.getOrdersCostses()!=null){
                           List<OrdersCosts> ordersCostsList=orders.getOrdersCostses();
                           for(int j=0;j<ordersCostsList.size();j++) {
                               OrdersCosts ordersCosts = ordersCostsList.get(j);
                               if (ordersCosts.getIsNewRecord()) {
                                   ordersCosts.setPatientId(orders.getPatientId());
                                   ordersCosts.setVisitId(orders.getPatientId());
                                   ordersCosts.setOrderId(orders.getId());
                                   ordersCosts.setOrderNo(orders.getOrderNo());
                                   ordersCosts.setOrderSubNo(orders.getOrderSubNo());
                                   ordersCosts.preInsert();
                                   ordersCostsDao.insert(ordersCosts);
                               } else {
                                   ordersCostsDao.update(ordersCosts);
                               }
                           }
                       }
                        num = ordersDao.insert(orders);
                    }else{
                        if(orders.getOrdersCostses()!=null){
                            List<OrdersCosts> ordersCostsList=orders.getOrdersCostses();
                            for(int j=0;j<ordersCostsList.size();j++) {
                                OrdersCosts ordersCosts = ordersCostsList.get(j);
                                if (ordersCosts.getIsNewRecord()) {
                                    ordersCosts.setPatientId(orders.getPatientId());
                                    ordersCosts.setVisitId(orders.getPatientId());
                                    ordersCosts.setOrderId(orders.getId());
                                    ordersCosts.setOrderNo(orders.getOrderNo());
                                    ordersCosts.setOrderSubNo(orders.getOrderSubNo());
                                    ordersCosts.preInsert();
                                    ordersCostsDao.insert(ordersCosts);
                                } else {
                                    ordersCostsDao.update(ordersCosts);
                                }
                            }
                        }
                        num = ordersDao.update(orders);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(num);
    }

    /**
     * 保存子医嘱
     * @param orders
     * @return
     * pq
     */
    public String saveSubOrder(Orders orders){
        if (orders.getIsNewRecord()) {
            orders.preInsert();
            String patientId=orders.getPatientId();
            String visitId = orders.getVisitId();
            ordersDao.insert(orders);
            if(orders.getOrdersCostses()!=null){
                List<OrdersCosts> ordersCostsList=orders.getOrdersCostses();
                for(int j=0;j<ordersCostsList.size();j++) {
                    OrdersCosts ordersCosts = ordersCostsList.get(j);
                    if (orders.getIsNewRecord()) {
                        ordersCosts.preInsert();
                        ordersCosts.setPatientId(orders.getPatientId());
                        ordersCosts.setVisitId(orders.getPatientId());
                        ordersCosts.setOrderId(orders.getId());
                        ordersCosts.setOrderNo(orders.getOrderNo());
                        ordersCosts.setOrderSubNo(orders.getOrderSubNo());
                        ordersCostsDao.insert(ordersCosts);
                    } else {
                        ordersCostsDao.update(ordersCosts);
                    }
                } }else{
                return "error";
            }

        }else{
            ordersDao.update(orders);
        }
        return "success";
    }

    /**
     * 下达医嘱
     * @param id
     * @return
     * pq
     */
    public String issuedOrders(String id){
        int num=ordersDao.issuedOrders(id);

        return  String.valueOf(num);
    }

    /**
     * 删除医嘱
     * @param ids
     * @return
     * pq
     */
    public String deleteOrdersNew(String ids){
        Orders orders= ordersDao.get(ids);
        int num=0;
        if(orders!=null){
            List<Orders> ordersSub= ordersDao.getSubOrders(orders.getPatientId(), orders.getVisitId(), orders.getOrderNo());
            if(ordersSub!=null && ordersSub.size()>0){
                for(int i=0;i<ordersSub.size();i++){
                    Orders orders1=ordersSub.get(i);
                    ordersDao.delete(orders1);
                    num=  ordersDao.delete(orders);
                }
            }else{
                num=   ordersDao.delete(orders);
            }
        }
        return String.valueOf(num);

    }

    /**
     * 获取最大的医嘱号
     * @param patientId
     * @param visitId
     * @return
     * pq
     */
    public  Integer getMaxOrderNo(String patientId,String visitId){
       return ordersDao.getMaxOrderNo(patientId,visitId);
    }

    /**
     * 拿到最大的子医嘱号
     * @param patientId
     * @param visitId
     * @param orderNo
     * @return
     * pq
     */
    public  Integer getOrderSubNo(String patientId,String visitId,Integer orderNo){
      return  ordersDao.getOrderSubNo(patientId,visitId,orderNo);
    }

    /**
     * 查询医嘱的子医嘱
     * @param orders
     * @author pq
     * @return
     */
    public  List<Orders> getSubOrders(Orders orders){
        return ordersDao.getSubOrders(orders.getPatientId(), orders.getVisitId(),orders.getOrderNo());
    }

    /**
     * 通过医嘱ID拿到医嘱计价
     * @author pq
     * @return
     */
    public List<OrdersCosts> getById(String ordersId){
       return ordersCostsDao.getByOrderId(ordersId);
    }


    /**
     * 查询非药品的计价
     * @param itemCode
     * @author pq
     * @return
     */
    public  List<BaseDto>  getClinicPrice(String itemCode){
     return ordersDao.getClinicPrice(itemCode);
    }


    /**
     * 跟住住院人标识获取医嘱费用
     * @param visitId
     * @return
     */
    public List<OrdersCosts> getOrdersCost(String visitId) {
        OrdersCosts ordersCosts=new OrdersCosts();
        ordersCosts.setVisitId(visitId);
        Page<OrdersCosts> page=new Page<OrdersCosts>();
        page.setOrderBy("order_no");
        ordersCosts.setPage(page);
        List<OrdersCosts> ordersList=ordersCostsDao.findList(ordersCosts);
        return ordersList;
    }

    /**
     * 停止医嘱(长期医嘱，停止时间是空)
     * @param orders
     * @author pq
     * @return
     */
    public String stopOrders(Orders orders){
       int num = 0;
        num = ordersDao.stopOrders(orders);
        return num+"";
    }

    /**
     * 作废医嘱
     * @param orders
     * @author pq
     * @return
     */
    public String cancelOrders(Orders orders){
        int num = 0;
        num = ordersDao.cancelOrders(orders);
        return num+"";
    }
}