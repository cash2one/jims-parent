package com.jims.clinic.api;

import com.jims.clinic.entity.ExamAppoints;
import com.jims.clinic.entity.ExamClassDict;
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

    public List<ExamClassDict> getEx();
}
