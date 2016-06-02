/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.OutpBillItemsServiceApi;
import com.jims.finance.bo.OutpBillItemsBo;
import com.jims.finance.dao.OutpBillItemsDao;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊病人诊疗收费项目明细Service
 * @author zhaoning
 * @version 2016-05-26
 */
@Service(version = "1.0.0")
public class OutpBillItemsService implements OutpBillItemsServiceApi {
  @Autowired
  private OutpBillItemsBo outpBillItemsBo;

    /**
     * 方法 findItems的功能描述
     * 门诊-收费结账-项目
     * @param outpRcptMaster
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public List<OutpBillItems> findItems(OutpRcptMaster outpRcptMaster){
       return  outpBillItemsBo.findItems(outpRcptMaster);
    }


}