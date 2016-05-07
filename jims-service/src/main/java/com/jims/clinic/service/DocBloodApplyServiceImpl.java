/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DocBloodApplyServiceApi;
import com.jims.clinic.dao.DocBloodApplyDao;
import com.jims.clinic.entity.DocBloodApply;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊用血申请Service
 * @author qlx
 * @version 2016-05-06
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class DocBloodApplyServiceImpl extends CrudImplService<DocBloodApplyDao, DocBloodApply> implements DocBloodApplyServiceApi {
    public String saveDocBloodApply(DocBloodApply docBloodApply){
        return null;
    }
}