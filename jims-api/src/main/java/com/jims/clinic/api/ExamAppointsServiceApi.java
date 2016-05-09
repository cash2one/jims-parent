package com.jims.clinic.api;

import com.jims.clinic.entity.ExamAppoints;
import com.jims.common.persistence.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/25.
 * 检查预约记录Api接口
 * @author zhaoning
 * @version 2016-04-25
 */
public interface ExamAppointsServiceApi {
    /**
     * 查询字段列表
     * @param page
     * @param examAppoints
     * @return
     */
    public Page<ExamAppoints> findPage(Page<ExamAppoints> page, ExamAppoints examAppoints);

    /**
     * 保存修改数据
     * @param examAppoints
     * @return
     */
    public String save(ExamAppoints examAppoints);

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
    public ExamAppoints get(String id);

    /**
     * 查询检查预约记录
     * @param patientId
     * @return
     */
    public List<ExamAppoints> getExamAppionts(String patientId);

    /**
     * 保存预约记录
     * @param examAppoints
     */
    public  void saveExamAppionts(ExamAppoints examAppoints);

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
    public int batchSave(ExamAppoints examAppoints);
}

