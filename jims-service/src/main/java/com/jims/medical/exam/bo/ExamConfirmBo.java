package com.jims.medical.exam.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.cliniIcnspect.dao.ExamAppointsDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamMaster;
import com.jims.medical.exam.dao.ExamMasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 检查确认 Bo
 * @author zhaoning
 */
@Service
@Transactional(readOnly = false)
public class ExamConfirmBo extends CrudImplService<ExamMasterDao,ExamMaster> {
    @Autowired
    private ExamMasterDao examMasterDao;
    @Autowired
    private ExamAppointsDao examAppointsDao;
    /**
     * 检查确认列表
     * @param performedBy 执行科室
     * @return
     * @author zhaoning
     */
    public List<ExamAppoints> getExamAppointses(String performedBy){
     return examMasterDao.getExamAppointses(performedBy);
    }

    /**
     * 检查确认
     * @param examAppoints
     * @return
     * @author zhaoning
     */
    public String confrimExam(ExamAppoints examAppoints){

        ExamMaster examMaster = new ExamMaster();
        int num=0;
        Integer regPrnFlag=examAppoints.getRegPrnFlag();
        if(regPrnFlag!=null && regPrnFlag==1){//已经确认
          num=2;
        }else if(examAppoints!=null && examAppoints.getId()!=null){
            //更新 examAppoints
            examAppointsDao.updateAppoints(examAppoints.getId());

            examMaster.setExamNo(examAppoints.getExamNo());
            examMaster.setLocalIdClass(examAppoints.getLocalIdClass());
            examMaster.setPatientId(examAppoints.getPatientId());
            examMaster.setName(examAppoints.getName());
            examMaster.setSex(examAppoints.getSex());
            examMaster.setDateOfBirth(examAppoints.getDateOfBirth());
            examMaster.setExamClass(examAppoints.getExamClass());
            examMaster.setExamSubClass(examAppoints.getExamSubClass());
            examMaster.setClinDiag(examAppoints.getClinDiag());
            examMaster.setPhysSign(examAppoints.getPhysSign());
            examMaster.setRelevantDiag(examAppoints.getRelevantDiag());
            examMaster.setRelevantLabTest(examAppoints.getRelevantLabTest());
            examMaster.setClinDiag(examAppoints.getClinDiag());
            examMaster.setExamMode(examAppoints.getExamMode());
            examMaster.setExamGroup(examAppoints.getExamGroup());
            examMaster.setDevice(examAppoints.getDevice());
            examMaster.setPerformedBy(examAppoints.getPerformedBy());
            examMaster.setPatientSource(examAppoints.getPatientSource());
            examMaster.setFacility(examAppoints.getFacility());
            examMaster.setReqDateTime(examAppoints.getReqDateTime());
            examMaster.setReqDept(examAppoints.getReqDept());
            examMaster.setReqMemo(examAppoints.getReqMemo());
            examMaster.setScheduledDateTime(examAppoints.getScheduledDate());
            examMaster.setNotice(examAppoints.getNotice());
            examMaster.setCosts(examAppoints.getCosts());
            examMaster.setCharges(examAppoints.getCharges());
            examMaster.setChargeType(examAppoints.getChargeType());
            examMaster.setIdentity(examAppoints.getIdentity());
            if (examMaster.getIsNewRecord()){
                examMaster.preInsert();
                num=dao.insert(examMaster);
            }else{
                examMaster.preUpdate();
                num=dao.update(examMaster);
            }
        }
        return num+"";
    }
}
