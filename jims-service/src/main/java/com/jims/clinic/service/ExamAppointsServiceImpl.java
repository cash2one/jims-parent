/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamAppointsServiceApi;
import com.jims.clinic.dao.ExamAppointsDao;
import com.jims.clinic.dao.ExamClassDictDao;
import com.jims.clinic.entity.ExamAppoints;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查预约记录Service
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamAppointsServiceImpl extends CrudImplService<ExamAppointsDao, ExamAppoints> implements ExamAppointsServiceApi {

    @Autowired
    private ExamAppointsDao examAppointsDao;

    /**
     * 查询预约主记录
     *
     * @param patientId
     * @return
     */
    @Override
    public List<ExamAppoints> getExamAppionts(String patientId) {
        return examAppointsDao.getExamAppionts(patientId);
    }

    /**
     * 保存检查预约记录
     *
     * @param examAppoints
     * @return
     */
    @Override
    public Integer saveExamAppionts(ExamAppoints examAppoints) {
        return examAppointsDao.saveExamAppionts(examAppoints);
    }

    /**
     * 删除预约记录
     *
     * @param examNo
     * @return
     */
    @Override
    public Integer deleteExamAppionts(String examNo) {
        return examAppointsDao.deleteExamAppionts(examNo);
    }

    /**
     * 获得最大的检查申请号
     *
     * @return
     */
    @Override
    public Integer getMaxExamNo() {
        return examAppointsDao.getMaxExamNo();
    }
}