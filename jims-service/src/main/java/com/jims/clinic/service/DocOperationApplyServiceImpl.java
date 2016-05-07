/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DocOperationApplyServiceApi;

import com.jims.clinic.dao.DocOperationApplyDao;
import com.jims.clinic.entity.DocBloodApply;
import com.jims.clinic.entity.DocOperationApply;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊手术信息申请表Service
 * @author qlx
 * @version 2016-05-06
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class DocOperationApplyServiceImpl extends CrudImplService<DocOperationApplyDao, DocOperationApply> implements DocOperationApplyServiceApi {
    //保存门诊手术信息
    public String saveDocOperationApply(DocOperationApply docOperationApply){
        return null;
    }
}