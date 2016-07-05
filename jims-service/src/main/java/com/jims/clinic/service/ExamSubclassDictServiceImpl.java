package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.bo.ExamSubClassDictBo;
import com.jims.common.persistence.Page;
import com.jims.exam.api.ExamSubclassDictApi;
import com.jims.exam.entity.ExamSubclassDict;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * ExamSubclassDictService
 * @author zhangpeng
 * @version 2016-04-27
 */
@Service(version = "1.0.0")
public class ExamSubclassDictServiceImpl implements ExamSubclassDictApi{

    @Autowired
    private ExamSubClassDictBo bo;

    @Override
    public Page<ExamSubclassDict> findPage(Page<ExamSubclassDict> page, ExamSubclassDict examSubclassDict) {
        return bo.findPage(page,examSubclassDict);
    }

    @Override
    public String save(ExamSubclassDict examSubclassDict) {
        try {
            bo.save(examSubclassDict);
            return "1";
        } catch (Exception e) {
        }
        return "0";
    }

    @Override
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch (Exception e) {
        }
        return "0";
    }

    @Override
    public ExamSubclassDict get(String id) {
        return bo.get(id);
    }

    @Override
    public List getEx(String examClassName,String orgId) {
        return bo.getEx(examClassName,orgId);
    }

    /**
     *
     * 获取检查子类别字典列表
     * @return 集合
     */
    @Override
    public List<ExamSubclassDict> findAll() {
        return bo.findAll();
    }

    /**
     * 通过orgID获取检查子类别列表
     * @param orgId 机构id
     * @return 集合
     */
    @Override
    public List<ExamSubclassDict> findListByOrgId(String orgId) {
        return bo.findListByOrgId(orgId);
    }
    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    @Override
    public List<ExamSubclassDict> listByClass(String orgId, String className) {
        return bo.listByClass(orgId,className);
    }
}