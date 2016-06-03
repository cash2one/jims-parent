/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 门诊病人诊疗收费项目明细DAO接口
 * @author zhaoning
 * @version 2016-05-26
 */
@MyBatisDao
public interface OutpBillItemsDao extends CrudDao<OutpBillItems> {
    /**
     * 根据
     * @param rcptNo
     * @return
     * @author zhaoning
     */
  public List<OutpBillItems> getBackChargeItems(@Param("rcptNo")String rcptNo);


    /**
     * 方法 findItems的功能描述
     * 门诊-收费结账
     * @param outpRcptMaster
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public List<OutpBillItems> findItems(OutpRcptMaster outpRcptMaster);
}