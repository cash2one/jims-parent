package com.jims.orders.dao;


import com.jims.orders.entity.OrdersCosts;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医嘱计价项目DAO接口
 * @author pq
 * @version 2016-05-18
 */
@MyBatisDao
public interface OrdersCostsDao extends CrudDao<OrdersCosts> {
    /**
     * 通过医嘱ID拿到医嘱计价
     * @author pq
     * @return
     */
   public List<OrdersCosts> getByOrderId(@Param("ordersId")String ordersId);





}