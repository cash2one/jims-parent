package com.jims.clinic.api;

import com.jims.clinic.entity.ExamSubclassDict;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public interface ExamSubclassDictApi {
    /**
     * 查询字段列表
     *
     * @param page
     * @param examSubclassDict
     * @return
     */
    public Page<ExamSubclassDict> findPage(Page<ExamSubclassDict> page, ExamSubclassDict examSubclassDict);

    /**
     * 保存修改数据
     *
     * @param examSubclassDict
     * @return
     */
    public String save(ExamSubclassDict examSubclassDict);

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public ExamSubclassDict get(String id);

    /**
     * 联动查询，获取主类别
     * @return
     */
    public List getEx(String examClassName);


    /**
     * 通过orgID获取检查类别列表
     * @param orgId 机构id
     * @return 集合
     */
    public List<ExamSubclassDict> findListByOrgId(String orgId);
    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    public List<ExamSubclassDict> listByClass(String orgId, String className);
    /**
     * 查询全部数据
     * @return
     */
    public List<ExamSubclassDict> findAll();

}
