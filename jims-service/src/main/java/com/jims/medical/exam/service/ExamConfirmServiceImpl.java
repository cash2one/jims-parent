package com.jims.medical.exam.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.vo.LoginInfo;
import com.jims.exam.api.ExamConfirmServiceApi;
import com.jims.exam.entity.ExamAppoints;
import com.jims.medical.exam.bo.ExamConfirmBo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 检查确认service
 * @author zhaoning
 */
@Service(version = "1.0.0")
public class ExamConfirmServiceImpl implements ExamConfirmServiceApi {
    @Autowired
    private ExamConfirmBo examConfirmBo;
    /**
     * 加载 检查确认列表
     * @param performedBy 执行科室
     * @return
     * @author zhaoning
     */
    @Override
    public List<ExamAppoints> getExamAppointses(String performedBy,String outOrIn,String startTime,String endTime,String appointsDept,String patientName,LoginInfo loginInfo) {
        return examConfirmBo.getExamAppointses(performedBy,outOrIn,startTime,endTime,appointsDept,patientName,loginInfo);
    }

    /**
     * 检查确认
     * @param examAppoints
     * @return
     * @author zhaoning
     */
    @Override
    public String confrimExam(ExamAppoints examAppoints)throws Exception {
        return examConfirmBo.confrimExam(examAppoints);
    }
}
