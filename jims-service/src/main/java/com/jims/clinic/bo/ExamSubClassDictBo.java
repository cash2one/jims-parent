package com.jims.clinic.bo;

import com.jims.clinic.dao.ExamSubclassDictDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.entity.ExamSubclassDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查维护BO层
 * @author lgx
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = false)
public class ExamSubClassDictBo extends CrudImplService<ExamSubclassDictDao,ExamSubclassDict> {

    @Autowired
    private ExamSubclassDictDao examSubclassDictDao;

    public List getEx(String examClassName,String orgId) {
        return examSubclassDictDao.getEx(examClassName,orgId);
    }

    /**
     *
     * 获取检查子类别字典列表
     * @return 集合
     */
    public List<ExamSubclassDict> findAll() {
        return dao.findAllList(new ExamSubclassDict());
    }

    /**
     * 通过orgID获取检查子类别列表
     * @param orgId 机构id
     * @return 集合
     */
    public List<ExamSubclassDict> findListByOrgId(String orgId) {
        return dao.findListByOrgId(orgId);
    }
    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    public List<ExamSubclassDict> listByClass(String orgId, String className) {
        return dao.listByClass(orgId,className);
    }
}