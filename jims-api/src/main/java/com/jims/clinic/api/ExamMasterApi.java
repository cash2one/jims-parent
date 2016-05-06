package com.jims.clinic.api;

import com.jims.clinic.entity.ExamMaster;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public interface ExamMasterApi {
    /**
     * 查询字段列表
     * @param page
     * @param examMaster
     * @return
     */
    public Page<ExamMaster> findPage(Page<ExamMaster> page, ExamMaster examMaster);

    /**
     * 保存修改数据
     * @param examMaster
     * @return
     */
    public String save(ExamMaster examMaster);

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
    public ExamMaster get(String id);

    /**
     * 查询检查预约记录
     * @param patientId
     * @return
     */
    public List<ExamMaster> getExamAppionts(String patientId);

    /**
     * 保存预约记录
     * @param examMaster
     */
    public  void saveExamAppionts(ExamMaster examMaster);

    /**
     * 删除预约记录
     * @param id
     * @return
     */

    public Integer deleteExamAppionts( String id);

    /**
     * 获得最大的申请序号
     * @return
     */
    public Integer getMaxExamNo();
    /**
     * 检查申请保存
     * @param
     * @return
     */
    public int batchSave(ExamMaster examMaster);
}
