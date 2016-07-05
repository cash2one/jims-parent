package com.jims.exam.api;

import com.jims.exam.entity.ExamClassDict;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface ExamClassDictApi {
    /**
     * 查询字段列表
     * @param page
     * @param examClassDict
     * @return
     */
    public Page<ExamClassDict> findPage(Page<ExamClassDict> page, ExamClassDict examClassDict);

    /**
     * 保存修改数据
     * @param examClassDict
     * @return
     */
    public String save(ExamClassDict examClassDict);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ExamClassDict get(String id);

    /**
     * 获得所有部门和所属部门科室
     * @return
     */
    public List<ExamClassDict> getEx(String orgId);
    /**
     * 查询全部数据
     * @return
     */
    public List<ExamClassDict> findAll();

    /**
     * 通过orgID获取检查类别列表
     * @param orgId 机构id
     * @return 集合
     */
    public List<ExamClassDict> findListByOrgId(String orgId);
}
