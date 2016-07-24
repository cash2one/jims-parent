package com.jims.exam.api;

import com.jims.common.vo.LoginInfo;
import com.jims.exam.entity.ExamAppoints;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 检查确认 API 接口
 * @author zhaoning
 */
public interface ExamConfirmServiceApi {
    /**
     * 根据病人ID 查询 确认列表(门诊||住院)
     * @param performedBy  执行科室
     * @return
     * @author zhaoning
     */
    public List<ExamAppoints> getExamAppointses(String performedBy,String outOrIn,String startTime,String endTime,String appointsDept,String patientName,LoginInfo loginInfo);

    /**
     * 确认检查(门诊||住院)
     * @param examAppoints
     * @return
     * @author  zhaoning
     */
    public String confrimExam(ExamAppoints examAppoints)throws Exception;


}
