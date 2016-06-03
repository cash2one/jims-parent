/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DocBloodApplyServiceApi;
import com.jims.clinic.dao.DocBloodApplyDao;
import com.jims.clinic.dao.DocBloodCapacityDao;
import com.jims.clinic.dao.DocOperationGradeDao;
import com.jims.clinic.entity.DocBloodApply;
import com.jims.clinic.entity.DocBloodCapacity;
import com.jims.clinic.entity.DocOperationGrade;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 门诊用血申请Service
 * @author qlx
 * @version 2016-05-06
 */
@Service(version ="1.0.0")

public class DocBloodApplyServiceImpl extends CrudImplService<DocBloodApplyDao, DocBloodApply> implements DocBloodApplyServiceApi {
    @Autowired
    private DocBloodCapacityDao docBloodCapacityDao;


    public String saveDocBloodApply(DocBloodApply docBloodApply){
        String strState = super.save(docBloodApply);
        docBloodCapacityDao.delBloodCapacity(docBloodApply.getApplyNum());
        if (docBloodApply.getList().size() > 0) {
            for (DocBloodCapacity docBloodCapacity:docBloodApply.getList()) {
                docBloodCapacity.preInsert();
                docBloodCapacityDao.insert(docBloodCapacity);
            }
        }
        return strState;
    }
}