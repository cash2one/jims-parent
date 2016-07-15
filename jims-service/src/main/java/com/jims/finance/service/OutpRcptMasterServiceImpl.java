/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.OutpRcptMasterServiceApi;
import com.jims.finance.bo.OutpRcptMasterBo;
import com.jims.finance.dao.OutpBillItemsDao;
import com.jims.finance.dao.OutpRcptMasterDao;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊医疗收据记录Service
 * @author zhaoning
 * @version 2016-05-26
 */
@Service(version = "1.0.0")
public class OutpRcptMasterServiceImpl implements OutpRcptMasterServiceApi {
  @Autowired
  private OutpRcptMasterBo outpRcptMasterBo;
  @Autowired
  private OutpRcptMasterDao outpRcptMasterDao;
  @Autowired
  private OutpOrdersCostsDao outpOrdersCostsDao;
  @Autowired
  private OutpBillItemsDao outpBillItemsDao;
    /**
     * 方法 findCharge 的能描述
     * 查询收费结账的收据
     * @param
     * @reurn
     * @thows
     * @author pq
     * @date 2016/5/31 0031
     */
    public OutpRcptMaster findCharge(OutpRcptMaster outpRcptMaster){
       return outpRcptMasterBo.findCharge(outpRcptMaster);
    }
 /* *//**
   * 确认收费信息
   * @param ids
   * @return
   * @author zhaoning
   *//*

  public String confirmPay(String ids) {

        *//*调用存储过程*//*
    ids="6f09b8c7726c4a0db03063f6728c8f08,";
    outpOrdersCostsDao.confirmPay(ids);
    return null;
  }*/

  /**
   * 根据门诊号，获取，退费信息
   * @param clinicNo
   * @param orgId
   * @return
   * @author zhaoning
   */

  public List<OutpRcptMaster> getBackChargeInfo(String clinicNo, String orgId) {
    return outpRcptMasterDao.getBackChargeInfo(clinicNo,orgId);
  }

  /**
   * 根据收据号查询 门诊收费项目
   * @param rcptNo
   * @return
   * @author zhaoning
   */

  public List<OutpBillItems> getBackChargeItems(String rcptNo) {
    return outpBillItemsDao.getBackChargeItems(rcptNo) ;
  }

}