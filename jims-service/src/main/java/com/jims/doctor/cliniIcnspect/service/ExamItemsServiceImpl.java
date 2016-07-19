/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.cliniIcnspect.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.exam.api.ExamItemsServiceApi;
import com.jims.doctor.cliniIcnspect.dao.ExamItemsDao;
import com.jims.exam.entity.ExamItems;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 检查项目记录Service
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")

public class ExamItemsServiceImpl extends CrudImplService<ExamItemsDao, ExamItems> implements ExamItemsServiceApi {
    @Autowired
    private ExamItemsDao examItemsDao;

    @Override
    public void saveExamItem(ExamItems examItems) {
         examItemsDao.saveExamItems(examItems);
    }

    @Override
    public List<ExamItems> loadExamItems(String examNo) {
        return examItemsDao.loadExamItems(examNo);
    }

    @Override
    public Integer deleteItems(String examNo) {
        return examItemsDao.deleteItems(examNo);
    }

    /**
     * 通过主记录id获取检查子项
     * @param appointsId
     * @return
     */
    @Override
    public List<ExamItems> getItemName(String appointsId) {
        return examItemsDao.getItemName(appointsId);
    }
}