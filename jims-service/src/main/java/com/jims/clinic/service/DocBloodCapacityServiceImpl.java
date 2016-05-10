/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DocBloodCapacityServiceApi;
import com.jims.clinic.dao.DocBloodCapacityDao;
import com.jims.clinic.entity.DocBloodCapacity;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊用血量申请Service
 * @author qlx
 * @version 2016-05-06
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class DocBloodCapacityServiceImpl extends CrudImplService<DocBloodCapacityDao, DocBloodCapacity> implements DocBloodCapacityServiceApi {
   @Autowired
   private DocBloodCapacityDao docBloodCapacityDao;
    public List<DocBloodCapacity> getDocBloodCapacityList(String applyNum){
        return docBloodCapacityDao.getDocOperationGradeList(applyNum);
    }
}