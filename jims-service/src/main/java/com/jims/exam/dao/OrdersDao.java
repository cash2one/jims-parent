/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.exam.entity.Orders;
import org.apache.ibatis.annotations.Param;

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
    public Integer getOrderSubNo();

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
}