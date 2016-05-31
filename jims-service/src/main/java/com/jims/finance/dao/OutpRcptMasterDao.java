/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.OutpRcptMaster;

import java.util.List;

/**
 * 门诊医疗收据记录DAO接口
 * @author zhaoning
 * @version 2016-05-26
 */
@MyBatisDao
public interface OutpRcptMasterDao extends CrudDao<OutpRcptMaster> {

    /**
     * 方法 findCharge 的能描述
     * 查询收费结账的收据
     * @param
     * @reurn
     * @thows
     * @author pq
     * @date 2016/5/31 0031
     */
    public OutpRcptMaster findCharge(OutpRcptMaster outpRcptMaster);
}