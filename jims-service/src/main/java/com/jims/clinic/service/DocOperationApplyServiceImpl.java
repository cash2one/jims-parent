/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DocOperationApplyServiceApi;

import com.jims.clinic.dao.DocOperationApplyDao;
import com.jims.clinic.dao.DocOperationGradeDao;
import com.jims.clinic.entity.DocBloodApply;
import com.jims.clinic.entity.DocOperationApply;
import com.jims.clinic.entity.DocOperationGrade;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 门诊手术信息申请表Service
 * @author qlx
 * @version 2016-05-06
 */
@Service(version ="1.0.0")

public class DocOperationApplyServiceImpl extends CrudImplService<DocOperationApplyDao, DocOperationApply> implements DocOperationApplyServiceApi {
    @Autowired
    private DocOperationGradeDao docOperationGradeDao;
    //保存门诊手术信息

    public String saveDocOperationApply(DocOperationApply docOperationApply){
        String strState=super.save(docOperationApply);
        docOperationGradeDao.delDocOperationGrade(docOperationApply.getId());
        if (docOperationApply.getList().size()>0){
            for (DocOperationGrade docOperationGrade:docOperationApply.getList()){
                docOperationGrade.preInsert();
                docOperationGradeDao.insert(docOperationGrade);
            }
        }
        return strState;
    }
}