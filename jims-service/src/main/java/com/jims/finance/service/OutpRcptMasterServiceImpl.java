/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.OutpRcptMasterServiceApi;
import com.jims.finance.bo.OutpRcptMasterBo;
import com.jims.finance.dao.OutpRcptMasterDao;
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


}