package com.jims.nurse.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.nurse.entity.OrdersGroupRec;
import org.apache.ibatis.annotations.Param;

/**
 * 管床医生记录DAO接口
 * @author CTQ
 * @version 2016-06-06
 */
@MyBatisDao
public interface OrdersGroupRecDao extends CrudDao<OrdersGroupRec> {

    /**
     * 根据病人ID删除管床医生记录
     * @param patientId
     * @return
     */
    public int deleteByParentId(@Param("patientId")String patientId);

    /**
     * 根据病人ID 查询 对象
     * @param patientId
     * @return
     * @author zhaoning
     */
    public OrdersGroupRec getOrdersByPat(@Param("patientId")String patientId);

    /**
     * 新建病历 更新 管床医生
     * @author zhaoning
     */
    public void updateByMrNew(OrdersGroupRec ordersGroupRec);
	
}