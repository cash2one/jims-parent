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
     * 方法 findCharge 的能描述
     * 查询收费结账的收据
     * @param
     * @reurn
     * @thows
     * @author pq
     * @date 2016/5/31 0031
     */
    public OutpRcptMaster findCharge(OutpRcptMaster outpRcptMaster);


    /**
     * 方法 updateAcctNo的功能描述
     * 结账确认的时候修改门诊医疗收据记录 的结账序号
     * @param outpRcptMaster
     * @return
     * @autor pq
     * @date 2016/6/1 0001
     */
    public int updateAcctNo(OutpRcptMaster outpRcptMaster);
}