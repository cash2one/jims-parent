/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.bo.ExamAppointsBo;
import com.jims.common.persistence.Page;
import com.jims.exam.api.ExamAppointsServiceApi;
import com.jims.exam.bo.HospitalInspectBo;
import com.jims.exam.entity.ExamAppoints;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 检查预约记录Service
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
public class ExamAppointsServiceImpl  implements ExamAppointsServiceApi {

    @Autowired
    private ExamAppointsBo examAppointsBo;
    @Autowired
    private HospitalInspectBo hospitalInspectBo;

    @Override
    public Page<ExamAppoints> findPage(Page<ExamAppoints> page, ExamAppoints examAppoints) {
        return examAppointsBo.findPage(page,examAppoints);
    }

    @Override
    public String save(ExamAppoints examAppoints) {
        return examAppointsBo.save(examAppoints);
    }

    @Override
    public String delete(String ids) {
        return examAppointsBo.delete(ids);
    }

    @Override
    public ExamAppoints get(String id) {
        return examAppointsBo.get(id);
    }

    @Override
    public List<ExamAppoints> getExamAppionts(String patientId) {
        return examAppointsBo.getExamAppionts(patientId);
    }

    @Override
    public String deleteExamAppionts(String id) {
        return examAppointsBo.deleteExamAppionts(id);
    }

    @Override
    public Integer getMaxExamNo() {
        return examAppointsBo.getMaxExamNo();
    }

    @Override
    public int batchSave(ExamAppoints examAppoints) {
        return examAppointsBo.batchSave(examAppoints);
    }

    /**
     * 住院检查申请保存
     * @param examAppoints
     * @return
     */
    @Override
    public int saveHospitalInspect(ExamAppoints examAppoints) {
        int num =hospitalInspectBo.saveHospitalInspect(examAppoints);
        return num;
    }
}