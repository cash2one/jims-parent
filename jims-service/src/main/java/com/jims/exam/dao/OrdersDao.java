/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.exam.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;

import java.util.Date;
import java.util.List;

/**
 * 住院医嘱DAO接口
 * @author zhangpeng
 * @version 2016-05-09
 */
@MyBatisDao
public interface OrdersDao extends CrudDao<Orders> {
    /**
     *
     * @param clinicId
     * @return
     */
    public String deleteOrders(String clinicId);

    /**
     * 获取医嘱最大序号
     * @return
     */
    public Integer getOrderNo(@Param("patientId")String patientId,@Param("visitId")String visitId,@Param("clinicId")String clinicId);

    /**
     * 获取子医嘱最大序号
     * @return
     */
    public Integer getOrderSubNo(@Param("patientId")String patientId,@Param("visitId")String visitId,@Param("orderNo")Integer orderNo);

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
    public int creeatOrderNo(Orders orders);

    /**
     * 查找病人的医嘱列表
     * @param orders
     * @return
     * pq
     */
    public List<Orders> getPatientOrders(Orders orders);

    /**
     * 下达医嘱
     * @param id
     * @return
     * pq
     */
    public int issuedOrders(@Param("id")String id);

    /**
     * 查找子医嘱
     * @param patientId
     * @param visitId
     * @param orderNo
     * @return
     * pq
     */
    public List<Orders> getSubOrders(@Param("patientId")String patientId,@Param("visitId")String visitId,@Param("orderNo")Integer orderNo);


    /**s
     * 拿到最大的医嘱号
     * @param patientId
     * @param visitId
     * @return
     * pq
     */
    public  Integer getMaxOrderNo(@Param("patientId")String patientId,@Param("visitId")String visitId);

    /**
     * 护理端 - 查询医嘱
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> getNurseOrders(Orders orders);


    /**
     * 护理端 - 转抄医嘱
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> ordersCopied(Orders orders);

    /**
     * 护士-取消入科，判断是否有下达医嘱
     * @param patientId
     * @param visitId
     * @param admissionDateTime
     * @author CTQ
     * @return
     */
    public Integer findOrderCount(@Param("patientId")String patientId,@Param("visitId")String visitId,@Param("admissionDateTime")Date admissionDateTime);
    /**
     * 护理端 - 转抄
     * @param orders
     * @author pq
     * @return
     */
    public  int operationCopied(Orders orders);

    /**
     * 护理端 - 医嘱校验
     * @param orders
     * @author pq
     * @return
     */
    public int proofOrders(Orders orders);

    /**
     * 护理端 - 医嘱执行
     * @param orders
     * @return
     */
    public int executeOrders(Orders orders);

}