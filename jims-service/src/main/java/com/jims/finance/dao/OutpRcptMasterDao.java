/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.OutpRcptMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 门诊医疗收据记录DAO接口
 * @author zhaoning
 * @version 2016-05-26
 */
@MyBatisDao
public interface OutpRcptMasterDao extends CrudDao<OutpRcptMaster> {
    /**
     * 根据门诊号，查询出退费信息
     * @param clinicNo
     * @param orgId
     * @return
     * @author zhaoning
     */
    public List<OutpRcptMaster> getBackChargeInfo(@Param("clinicNo")String clinicNo,@Param("orgId")String orgId);
}