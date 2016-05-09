/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamRptPatternApi;
import com.jims.clinic.dao.ExamRptPatternDao;
import com.jims.clinic.entity.ExamRptPattern;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * ExamRptPatternService
 * @author zhangpeng
 * @version 2016-04-27
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamRptPatternServiceIpml extends CrudImplService<ExamRptPatternDao, ExamRptPattern> implements ExamRptPatternApi{

    @Autowired
    private ExamRptPatternDao examRptPatternDao;
    @Override
    public List getExamRptPattern(String examSubClass) {
      return examRptPatternDao.getExamRptPattern(examSubClass);
    }
    /**
     *
     * 获取检查子类别字典列表
     * @return 集合
     */
    @Override
    public List<ExamRptPattern> findAll() {
        return dao.findAllList(new ExamRptPattern());
    }

    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    @Override
    public List<ExamRptPattern> listByClass(String orgId, String className,String subClassName) {
        return dao.listByClass(orgId,className,subClassName);
    }
}