package com.jims.clinic.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.bo.ExamRptPatternBo;
import com.jims.common.persistence.Page;
import com.jims.exam.api.ExamRptPatternApi;
import com.jims.exam.entity.ExamRptPattern;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * ExamRptPatternService
 * @author zhangpeng
 * @version 2016-04-27
 */
@Service(version = "1.0.0")
public class ExamRptPatternServiceIpml implements ExamRptPatternApi{

    @Autowired
    private ExamRptPatternBo bo;

    @Override
    public Page<ExamRptPattern> findPage(Page<ExamRptPattern> page, ExamRptPattern examRptPattern) {
        return bo.findPage(page,examRptPattern);
    }

    @Override
    public String save(ExamRptPattern examRptPattern) {
        try {
            bo.save(examRptPattern);
            return "1";
        } catch (RuntimeException e){}

        return "0";
    }

    @Override
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch (RuntimeException e){}

        return "0";
    }

    @Override
    public ExamRptPattern get(String id) {
        return bo.get(id);
    }

    @Override
    public List getExamRptPattern(String examSubClass) {
      return bo.getExamRptPattern(examSubClass);
    }
    /**
     *
     * 获取检查子类别字典列表
     * @return 集合
     */
    @Override
    public List<ExamRptPattern> findAll() {
        return bo.findAll();
    }

    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    @Override
    public List<ExamRptPattern> listByClass(String orgId, String className,String subClassName) {
        return bo.listByClass(orgId,className,subClassName);
    }
}