/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DocOperationGradeServiceApi;
import com.jims.clinic.dao.DocOperationGradeDao;
import com.jims.clinic.entity.DocOperationGrade;
import com.jims.common.persistence.Page;
import com.jims.common.service.CrudService;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 门诊手术等级表Service
 * @author qlx
 * @version 2016-05-06
 */
@Service(version ="1.0.0")

public class DocOperationGradeServiceImpl extends CrudImplService<DocOperationGradeDao, DocOperationGrade> implements DocOperationGradeServiceApi {
    @Autowired
    private DocOperationGradeDao docOperationGradeDao;
    public List<DocOperationGrade> getDocOperationGradeList(String operationId){
        return docOperationGradeDao.getDocOperationGradeList(operationId);
    }
}