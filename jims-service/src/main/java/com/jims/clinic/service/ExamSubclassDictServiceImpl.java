/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamSubclassDictApi;
import com.jims.clinic.dao.ExamSubclassDictDao;
import com.jims.clinic.entity.ExamSubclassDict;

import com.jims.common.service.impl.CrudImplService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ExamSubclassDictService
 * @author zhangpeng
 * @version 2016-04-27
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamSubclassDictServiceImpl extends CrudImplService<ExamSubclassDictDao,ExamSubclassDict> implements ExamSubclassDictApi{

    @Autowired
    private ExamSubclassDictDao examSubclassDictDao;
    @Override
    public List getEx(String examClassName) {
        return examSubclassDictDao.getEx(examClassName);
    }
}