package com.jims.clinic.bo;

import com.jims.clinic.dao.ExamRptPatternDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.entity.ExamRptPattern;
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
public class ExamRptPatternBo extends CrudImplService<ExamRptPatternDao, ExamRptPattern> {

    @Autowired
    private ExamRptPatternDao examRptPatternDao;

    public List getExamRptPattern(String examSubClass,String orgId) {
        return examRptPatternDao.getExamRptPattern(examSubClass,orgId);
    }
    /**
     *
     * 获取检查子类别字典列表
     * @return 集合
     */
    public List<ExamRptPattern> findAll() {
        return dao.findAllList(new ExamRptPattern());
    }

    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    public List<ExamRptPattern> listByClass(String orgId, String className,String subClassName) {
        return dao.listByClass(orgId,className,subClassName);
    }
}