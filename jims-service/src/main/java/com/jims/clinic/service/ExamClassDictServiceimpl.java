/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.exam.api.ExamClassDictApi;
import com.jims.clinic.dao.ExamClassDictDao;
import com.jims.exam.entity.ExamClassDict;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;


/**
 * ExamClassDictService
 * @author zhangpeng
 * @version 2016-04-26
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamClassDictServiceimpl extends CrudImplService<ExamClassDictDao, ExamClassDict> implements ExamClassDictApi{

	@Autowired
	private ExamClassDictDao examClassDictDao;
	@Override
	public List<ExamClassDict> getEx() {
		return examClassDictDao.getEx();
	}

    /**
     *
     * 获取检查类别字典列表
     * @return 集合
     */
    public List<ExamClassDict> findAll() {
        return dao.findAllList(new ExamClassDict());
    }

    /**
     * 通过orgID获取检查类别列表
     * @param orgId 机构id
     * @return 集合
     */
    @Override
    public List<ExamClassDict> findListByOrgId(String orgId) {
        return dao.findListByOrgId(orgId);
    }
}