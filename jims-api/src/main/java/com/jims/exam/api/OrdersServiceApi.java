package com.jims.exam.api;

import com.jims.clinic.entity.OrdersCosts;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.Orders;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public interface OrdersServiceApi {
    /**
     * 保存住院医嘱
     * @param examAppoints
     * @return
     */
    public String saveOrders(ExamAppoints examAppoints);

    public String deleteOrders(String ids);

    /**
     * 最大OrderNo
     * @param
     * @parampatient_id
     * @paramvisit_Id
     * @author xueyx
     * @version 2016/5/12
     */
    public String findMaxOrderNo(Orders orders);

    /**
     * 构建最新OrderNo
     * @parampatient_id
     * @paramvisit_Id
     * @author xueyx
     * @version 2016/5/12
     */
    public Long creeatOrderNo(Orders orders);

    /**
     * 查询病人的医嘱列表
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> getPatientOrders(Orders orders);

    /**
     * 保存住院医嘱
     * @param ordersList
     * @author pq
     * @return
     */
    public String saveOrdersNew(List<Orders> ordersList);

    /**
     * 保存子医嘱
     * @param orders
     * @return
     * pq
     */
    public String saveSubOrder(Orders orders);

    /**
     * 下达医嘱
     * @param id
     * @return
     * pq
     */
   public  String issuedOrders(String id);

    /**
     * 删除医嘱
     * @param ids
     * @return
     * pq
     */
    public String deleteOrdersNew(String ids);

    /**
     * 获得最大的医嘱号
     * @param patientId
     * @param visitId
     * @return
     * pq
     */

   public  Integer getMaxOrderNo(String patientId,String visitId);


    /**
     * 获得最大的子医嘱号
     * @param patientId
     * @param visitId
     * @param orderNo
     * @return
     * pq
     */
    public Integer getOrderSubNo(String patientId,String visitId,Integer orderNo);

    /**
     * 通过主键拿到医嘱对象
     * @param id
     * @return
     * pq
     */
    public Orders get(String id);

    /**
     * 查询医嘱的子医嘱
     * @param orders
     * @author pq
     * @return
     */
    public  List<Orders> getSubOrders(Orders orders);

    /**
     * 查询医嘱的收费明细（药品的）
     * @param orderId
     * @author pq
     * @return
     */
    public  List<OrdersCosts> getById(String orderId);
}
